package com.curso.domains.dtos;

import java.util.UUID;

import com.curso.domains.Livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LivroDTO {
    private UUID id;

    @NotNull(message = "O campo Titulo é requerido")
    @NotBlank(message = "O campo Titulo não pode ser vazio")
    private String titulo;
    @NotNull(message = "O campo Codigo de Barras é requerido")
    @NotBlank(message = "O campo Codigo de Barras não pode ser vazio")
    private String cod_barras;
    @NotNull(message = "O campo Autor é requerido")
    @NotBlank(message = "O campo Autor não pode ser vazio")
    private String autor;
    @NotNull(message = "O campo Editora é requerido")
    @NotBlank(message = "O campo Editora não pode ser vazio")
    private String editora;
    @NotNull(message = "O campo Edição é requerido")
    @NotBlank(message = "O campo Edição não pode ser vazio")
    private String edicao;

    public LivroDTO() {
    }

    public LivroDTO(Livro obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.cod_barras = obj.getCod_barras();
        this.autor = obj.getAutor();
        this.editora = obj.getEditora();
        this.edicao = obj.getEdicao();
    }

    public LivroDTO(UUID id, String titulo, String cod_barras, String autor, String editora, String edicao) {
        this.id = id;
        this.titulo = titulo;
        this.cod_barras = cod_barras;
        this.autor = autor;
        this.editora = editora;
        this.edicao = edicao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

}
