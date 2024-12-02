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

import com.curso.domains.Livro;
import com.curso.domains.dtos.LivroDTO;
import com.curso.services.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/livro")
@Tag(name = "Livro", description = "API para gerenciamento de livros")
public class LivroResource {

    @Autowired
    private LivroService livroService;

    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista com todos os livros.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll() {
        return ResponseEntity.ok().body(livroService.findAll());
    }

    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostAuthorize(value = "hasRole('ADMIN') or returnObject.body.email == authentication.name")
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable UUID id) {
        Livro obj = this.livroService.findbyId(id);
        return ResponseEntity.ok().body(new LivroDTO(obj));
    }

    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro com base nos dados fornecidos.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO objDto) {
        Livro newObj = this.livroService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar livro", description = "Atualiza os dados de um livro existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable UUID id, @Valid @RequestBody LivroDTO objDto) {
        Livro Obj = this.livroService.update(id, objDto);
        return ResponseEntity.ok().body(new LivroDTO(Obj));
    }

    @Operation(summary = "Deletar livro", description = "Remove um livro existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> delete(@PathVariable UUID id) {
        this.livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
