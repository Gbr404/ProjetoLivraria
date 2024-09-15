package com.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService; 
    
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable UUID id) {
        Cliente obj = this.clienteService.findbyId(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> findByCpf(@PathVariable String cpf) {
        Cliente obj = this.clienteService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteDTO> findByEmail(@PathVariable String email) {
        Cliente obj = this.clienteService.findbyEmail(email);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @PostMapping    
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto) {
        Cliente newObj = this.clienteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable UUID id, @Valid @RequestBody ClienteDTO objDto) {
        Cliente Obj = this.clienteService.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable UUID id) {
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
