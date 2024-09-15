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

import com.curso.domains.Itemvenda;
import com.curso.domains.dtos.ItemvendaDTO;
import com.curso.services.ItemvendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/item_venda")
public class ItemvendaResource {
    @Autowired
    private ItemvendaService itemvendaService;

    @GetMapping
    public ResponseEntity<List<ItemvendaDTO>> findAll() {
        return ResponseEntity.ok().body(itemvendaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemvendaDTO> findById(@PathVariable UUID id) {
        Itemvenda obj = this.itemvendaService.findbyId(id);
        return ResponseEntity.ok().body(new ItemvendaDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ItemvendaDTO> create(@Valid @RequestBody ItemvendaDTO objDto) {
        Itemvenda newObj = this.itemvendaService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemvendaDTO> update(@PathVariable UUID id, @Valid @RequestBody ItemvendaDTO objDto) {
        Itemvenda Obj = this.itemvendaService.update(id, objDto);
        return ResponseEntity.ok().body(new ItemvendaDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ItemvendaDTO> delete(@PathVariable UUID id) {
        this.itemvendaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
