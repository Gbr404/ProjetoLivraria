package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Cidade;
import com.curso.domains.dtos.CidadeDTO;
import com.curso.repositories.CidadeRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepo;

    public List<CidadeDTO> findAll() {
        return cidadeRepo.findAll().stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
    }

    public Cidade findbyId(UUID id) {
        Optional<Cidade> obj = cidadeRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }
    
    public Cidade create(CidadeDTO obDto) {
        obDto.setId(null);
        Cidade newObj = new Cidade(obDto);
        return cidadeRepo.save(newObj);
    }

    public Cidade update(UUID id, CidadeDTO objDto) {
        objDto.setId(id);
        Cidade oldObj = findbyId(id);
        oldObj = new Cidade(objDto);
        return cidadeRepo.save(oldObj);
    }

    public void delete(UUID id) {
        Cidade obj = findbyId(id);
        if (obj.getPessoas().size() > 0) {
            throw new DataIntegrityViolationException("Cidade não pode ser deletado pois esta sendo usada nos cadastros!");
        }
        cidadeRepo.deleteById(id);
    }    
}
