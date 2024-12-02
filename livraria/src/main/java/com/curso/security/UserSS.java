package com.curso.security;

import com.curso.domains.Pessoa; // ou o nome correto da classe que representa os usuários no seu domínio
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(Pessoa pessoa) {
        this.username = pessoa.getEmail(); 
        this.password = pessoa.getPassword(); 
        this.authorities = pessoa.getPersonType().stream()
                .map(tipo -> new SimpleGrantedAuthority(tipo.getPersonType()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Lógica de expiração de conta, se necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Lógica de bloqueio de conta, se necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Lógica de expiração de credenciais, se necessário
    }

    @Override
    public boolean isEnabled() {
        return true; // Lógica de habilitação de conta, se necessário
    }
}
