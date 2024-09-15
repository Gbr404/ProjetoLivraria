package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Estado;
import com.curso.domains.dtos.EstadoDTO;
import com.curso.repositories.EstadoRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepo;

    public List<EstadoDTO> findAll() {
        return estadoRepo.findAll().stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
    }

    public Estado findbyId(UUID id) {
        Optional<Estado> obj = estadoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Estado create(EstadoDTO obDto) {
        obDto.setId(null);
        Estado newObj = new Estado(obDto);
        return estadoRepo.save(newObj);
    }

    public Estado update(UUID id, EstadoDTO objDto) {
        objDto.setId(id);
        Estado oldObj = findbyId(id);
        oldObj = new Estado(objDto);
        return estadoRepo.save(oldObj);
    }

    public void delete(UUID id) {
        Estado obj = findbyId(id);
        if (obj.getCidades().size() > 0) {
            throw new DataIntegrityViolationException("Cidade não pode ser deletado pois possui cadastros!");
        }
        estadoRepo.deleteById(id);
    }

}
