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

import com.curso.domains.Cliente;
import com.curso.domains.dtos.ClienteDTO;
import com.curso.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente", description = "API para gerenciamento do Cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService; 
    
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }
    
    @Operation(summary = "Listar todos os Cliente", description = "Retorna uma lista com todos os Cliente.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable UUID id) {
        Cliente obj = this.clienteService.findbyId(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @Operation(summary = "Buscar Cliente por CPF", description = "Retorna um cliente com base no CPF fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostAuthorize(value = "hasRole('ADMIN') or returnObject.body.email == authentication.name")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> findByCpf(@PathVariable String cpf) {
        Cliente obj = this.clienteService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }
    
    @Operation(summary = "Buscar Cliente por Email", description = "Retorna um cliente com base no Email fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostAuthorize(value = "hasRole('ADMIN') or returnObject.body.email == authentication.name")
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteDTO> findByEmail(@PathVariable String email) {
        Cliente obj = this.clienteService.findbyEmail(email);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @Operation(summary = "Criar um novo Cliente", description = "Cria um novo cliente com base nos dados fornecidos.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping    
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto) {
        Cliente newObj = this.clienteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable UUID id, @Valid @RequestBody ClienteDTO objDto) {
        Cliente Obj = this.clienteService.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(Obj));
    }

    @Operation(summary = "Deletar cliente", description = "Remove um cliente existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable UUID id) {
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
