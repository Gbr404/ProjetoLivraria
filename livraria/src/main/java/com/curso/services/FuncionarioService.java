package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Funcionario;
import com.curso.domains.dtos.FuncionarioDTO;
import com.curso.repositories.FuncionarioRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    public List<FuncionarioDTO> findAll() {
        return funcionarioRepo.findAll().stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());
    }

    public Funcionario findbyId(UUID id) {
        Optional<Funcionario> obj = funcionarioRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Funcionario findbyCpf(String cpf) {
        Optional<Funcionario> obj = funcionarioRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! cpf: " + cpf));
    }

    public Funcionario findbyEmail(String email) {
        Optional<Funcionario> obj = funcionarioRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! email: " + email));
    }

    public Funcionario create(FuncionarioDTO obDto) {
        obDto.setId(null);
        ValidaPorCPFeEmail(obDto);
        Funcionario newObj = new Funcionario(obDto);
        return funcionarioRepo.save(newObj);
    }

    public Funcionario update(UUID id, FuncionarioDTO objDto) {
        objDto.setId(id);
        Funcionario oldObj = findbyId(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Funcionario(objDto);
        return funcionarioRepo.save(oldObj);
    }

    public void delete(UUID id) {
        Funcionario obj = findbyId(id);
        if (obj.getVendas().size() > 0) {
            throw new DataIntegrityViolationException("Usuario não pode ser deletado pois possui ordens de serviço!");
        }
        funcionarioRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(FuncionarioDTO objDto) {
        Optional<Funcionario> obj = funcionarioRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        Optional<Funcionario> obj2 = funcionarioRepo.findByEmail(objDto.getEmail());
        if (obj2.isPresent() && obj2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }

}
