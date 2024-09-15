package com.curso.domains.dtos;

import java.util.UUID;

import com.curso.domains.Estado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EstadoDTO {
    private UUID id;

    @NotNull(message = "O campo Nome é requerido")
    @NotBlank(message = "O campo Nome não pode ser vazio")
    private String nome;
    @NotNull(message = "O campo Sigla é requerido")
    @NotBlank(message = "O campo Sigla não pode ser vazio")
    private String sigla;

    public EstadoDTO(Estado obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.sigla = obj.getSigla();
    }

    public EstadoDTO(UUID id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public EstadoDTO() {
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
