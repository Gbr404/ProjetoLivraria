package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Venda;
import com.curso.domains.dtos.VendaDTO;
import com.curso.repositories.VendaRepository;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepo;

    public List<VendaDTO> findAll() {
        return vendaRepo.findAll().stream().map(obj -> new VendaDTO(obj)).collect(Collectors.toList());
    }

    public Venda findbyId(UUID id) {
        Optional<Venda> obj = vendaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Venda create(VendaDTO obDto) {
        obDto.setId(null);
        Venda newObj = new Venda(obDto);
        return vendaRepo.save(newObj);
    }

    public Venda update(UUID id, VendaDTO objDto) {
        objDto.setId(id);
        Venda oldObj = findbyId(id);
        oldObj = new Venda(objDto);
        return vendaRepo.save(oldObj);
    }

    public void delete(UUID id) {
        vendaRepo.deleteById(id);
    }
}
