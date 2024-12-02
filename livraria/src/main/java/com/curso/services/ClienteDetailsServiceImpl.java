package com.curso.services;

import com.curso.repositories.PessoaRepository; 
import com.curso.domains.Pessoa;  
import com.curso.security.UserSS;  
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteDetailsServiceImpl implements UserDetailsService {

    private final PessoaRepository pessoaRepository;

    // Construtor que recebe o repositório da Pessoa
    public ClienteDetailsServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmail(username);
        
        if (pessoa.isEmpty()) {
            throw new UsernameNotFoundException("Cliente não encontrado: " + username);
        }
        
        return new UserSS(pessoa.orElseThrow());
    }
}

