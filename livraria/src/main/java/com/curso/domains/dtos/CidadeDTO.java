package com.curso.domains.dtos;

import java.util.UUID;

import com.curso.domains.Cidade;
import com.curso.domains.Estado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CidadeDTO {
    private UUID id;
    @NotNull(message = "O campo Nome é requerido")
    @NotBlank(message = "O campo Nome não pode ser vazio")
    private String nome;
    @NotNull(message = "O campo Estado é requerido")
    @NotBlank(message = "O campo Estado não pode ser vazio")
    private Estado estado;
    private Integer status;

    public CidadeDTO() {
    }

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado();
        this.status = cidade.getStatus();
    }

    public CidadeDTO(UUID id, String nome, Estado estado, Integer status) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
