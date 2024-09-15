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

import com.curso.domains.Cidade;
import com.curso.domains.dtos.CidadeDTO;
import com.curso.services.CidadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService; 

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> findAll(){
        return ResponseEntity.ok().body(cidadeService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> findById(@PathVariable UUID id) {
        Cidade obj = this.cidadeService.findbyId(id);
        return ResponseEntity.ok().body(new CidadeDTO(obj));
    }

    @PostMapping    
    public ResponseEntity<CidadeDTO> create(@Valid @RequestBody CidadeDTO objDto) {
        Cidade newObj = this.cidadeService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> update(@PathVariable UUID id, @Valid @RequestBody CidadeDTO objDto) {
        Cidade Obj = this.cidadeService.update(id, objDto);
        return ResponseEntity.ok().body(new CidadeDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> delete(@PathVariable UUID id) {
        this.cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
