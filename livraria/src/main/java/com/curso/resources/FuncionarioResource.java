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

import com.curso.domains.Funcionario;
import com.curso.domains.dtos.FuncionarioDTO;
import com.curso.services.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioResource {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        return ResponseEntity.ok().body(funcionarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable UUID id) {
        Funcionario obj = this.funcionarioService.findbyId(id);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<FuncionarioDTO> findByCpf(@PathVariable String cpf) {
        Funcionario obj = this.funcionarioService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<FuncionarioDTO> findByEmail(@PathVariable String email) {
        Funcionario obj = this.funcionarioService.findbyEmail(email);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO objDto) {
        Funcionario newObj = this.funcionarioService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable UUID id, @Valid @RequestBody FuncionarioDTO objDto) {
        Funcionario Obj = this.funcionarioService.update(id, objDto);
        return ResponseEntity.ok().body(new FuncionarioDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> delete(@PathVariable UUID id) {
        this.funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
