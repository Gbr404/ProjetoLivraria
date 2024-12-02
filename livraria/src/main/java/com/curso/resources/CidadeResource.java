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

import com.curso.domains.Cidade;
import com.curso.domains.dtos.CidadeDTO;
import com.curso.services.CidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cidade")
@Tag(name = "Cidade", description = "API para gerenciamento do cadastro de Cidades")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService; 

    @Operation(summary = "Listar todas cidade", description = "Retorna uma lista com todas cidade.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> findAll(){
        return ResponseEntity.ok().body(cidadeService.findAll());
    }
    
    @Operation(summary = "Consulta cidade por id", description = "Retorna uma cidade pelo id.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostAuthorize(value = "hasRole('ADMIN') or returnObject.body.email == authentication.name")
    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> findById(@PathVariable UUID id) {
        Cidade obj = this.cidadeService.findbyId(id);
        return ResponseEntity.ok().body(new CidadeDTO(obj));
    }

    @Operation(summary = "Criar uma nova cidade", description = "Cria uma nova cidade com base nos dados fornecidos.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping    
    public ResponseEntity<CidadeDTO> create(@Valid @RequestBody CidadeDTO objDto) {
        Cidade newObj = this.cidadeService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @Operation(summary = "Atualizar cidade", description = "Atualiza os dados de uma cidade existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> update(@PathVariable UUID id, @Valid @RequestBody CidadeDTO objDto) {
        Cidade Obj = this.cidadeService.update(id, objDto);
        return ResponseEntity.ok().body(new CidadeDTO(Obj));
    }

    @Operation(summary = "Deletar cidade", description = "Remove uma cidade existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> delete(@PathVariable UUID id) {
        this.cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
