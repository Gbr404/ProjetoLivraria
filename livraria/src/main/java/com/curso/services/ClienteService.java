package com.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domains.Cliente;
import com.curso.domains.dtos.ClienteDTO;
import com.curso.repositories.ClienteRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public List<ClienteDTO> findAll(){
        return clienteRepo.findAll().stream().map(obj->new ClienteDTO(obj)).collect(Collectors.toList());
    }

    public Cliente findbyId(UUID id){
        Optional<Cliente> obj = clienteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Cliente findbyCpf(String cpf){
        Optional<Cliente> obj = clienteRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! cpf: " + cpf));
    }

    public Cliente findbyEmail(String email){
        Optional<Cliente> obj = clienteRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! email: " + email));
    }

    public Cliente create(ClienteDTO obDto){
        obDto.setId(null);
        ValidaPorCPFeEmail(obDto);
        Cliente newObj = new Cliente(obDto);
        return clienteRepo.save(newObj);
    }

    public Cliente update(UUID id, ClienteDTO objDto){
        objDto.setId(id);
        Cliente oldObj = findbyId(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Cliente(objDto);
        return clienteRepo.save(oldObj);
    }

    public void delete(UUID id){
        Cliente obj = findbyId(id);
        if(obj.getVendas().size()>0){
            throw new DataIntegrityViolationException("Usuario não pode ser deletado pois possui ordens de serviço!");
        }
        clienteRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(ClienteDTO objDto){
        Optional<Cliente> obj = clienteRepo.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        Optional<Cliente> obj2 = clienteRepo.findByEmail(objDto.getEmail());
        if(obj2.isPresent() && obj2.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
