package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Livro;
import com.curso.domains.dtos.LivroDTO;
import com.curso.repositories.LivroRepository;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepo;

    public List<LivroDTO> findAll() {
        return livroRepo.findAll().stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
    }

    public Livro findbyId(UUID id) {
        Optional<Livro> obj = livroRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Livro create(LivroDTO obDto) {
        obDto.setId(null);
        Livro newObj = new Livro(obDto);
        return livroRepo.save(newObj);
    }

    public Livro update(UUID id, LivroDTO objDto) {
        objDto.setId(id);
        Livro oldObj = findbyId(id);
        oldObj = new Livro(objDto);
        return livroRepo.save(oldObj);
    }

    public void delete(UUID id) {
        livroRepo.deleteById(id);
    }
}
