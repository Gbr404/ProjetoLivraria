package com.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.domains.Estado;
import com.curso.domains.dtos.EstadoDTO;
import com.curso.services.EstadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/estado")
@Tag(name = "Estado", description = "API para gerenciamento de estado")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Operation(summary = "Listar todos os Estado", description = "Retorna uma lista com todos os estados.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        return ResponseEntity.ok().body(estadoService.findAll());
    }

    @Operation(summary = "Buscar estado por ID", description = "Retorna um estado com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostAuthorize(value = "hasRole('ADMIN') or returnObject.body.email == authentication.name")
    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> findById(@PathVariable UUID id) {
        Estado obj = this.estadoService.findbyId(id);
        return ResponseEntity.ok().body(new EstadoDTO(obj));
    }

    @Operation(summary = "Criar um novo estado", description = "Cria um novo estado com base nos dados fornecidos.")
    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping
    public ResponseEntity<EstadoDTO> create(@Valid @RequestBody EstadoDTO objDto) {
        Estado newObj = this.estadoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar estado", description = "Atualiza os dados de um estado existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> update(@PathVariable UUID id, @Valid @RequestBody EstadoDTO objDto) {
        Estado Obj = this.estadoService.update(id, objDto);
        return ResponseEntity.ok().body(new EstadoDTO(Obj));
    }

    @Operation(summary = "Deletar estado", description = "Remove um estado existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> delete(@PathVariable UUID id) {
        this.estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
