package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Itemvenda;
import com.curso.domains.dtos.ItemvendaDTO;
import com.curso.repositories.ItemvendaRepository;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ItemvendaService {

    @Autowired
    private ItemvendaRepository itemvendaRepo;

    public List<ItemvendaDTO> findAll() {
        return itemvendaRepo.findAll().stream().map(obj -> new ItemvendaDTO(obj)).collect(Collectors.toList());
    }
    
    public Itemvenda findbyId(UUID id) {
        Optional<Itemvenda> obj = itemvendaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Itemvenda create(ItemvendaDTO obDto) {
        Itemvenda newObj = new Itemvenda(obDto);
        return itemvendaRepo.save(newObj);
    }

     public Itemvenda update(UUID id, ItemvendaDTO objDto) {
        objDto.setId(id);
        Itemvenda oldObj = findbyId(id);
        oldObj = new Itemvenda(objDto);
        return itemvendaRepo.save(oldObj);
    }

    public void delete(UUID id) {
        itemvendaRepo.deleteById(id);
    }
    
}
