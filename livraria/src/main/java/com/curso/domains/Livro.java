package com.curso.domains;

import java.util.UUID;

import com.curso.domains.dtos.LivroDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;
    private String cod_barras;
    private String autor;
    private String editora;
    private String edicao;

    public Livro() {
    }

    public Livro(UUID id, String titulo, String cod_barras, String autor, String editora, String edicao) {
        this.id = id;
        this.titulo = titulo;
        this.cod_barras = cod_barras;
        this.autor = autor;
        this.editora = editora;
        this.edicao = edicao;
    }

    public Livro(LivroDTO obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.cod_barras = obj.getCod_barras();
        this.autor = obj.getAutor();
        this.editora = obj.getEditora();
        this.edicao = obj.getEdicao();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Livro other = (Livro) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
