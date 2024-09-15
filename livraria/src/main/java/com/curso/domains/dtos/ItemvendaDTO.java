package com.curso.domains.dtos;

import com.curso.domains.Itemvenda;
import com.curso.domains.Livro;
import com.curso.domains.Venda;

import java.util.UUID;

public class ItemvendaDTO {
    private UUID id;
    private Integer qtd;
    private Double vlr_unitario;
    private Double total;
    private Livro livro;
    private Venda venda;

    public ItemvendaDTO() {
    }

    public ItemvendaDTO(Itemvenda obj) {
        this.id = obj.getId();
        this.qtd = obj.getQtd();
        this.vlr_unitario = obj.getVlr_unitario();
        this.total = obj.getTotal();
        this.livro = obj.getLivro();
        this.venda = obj.getVenda();
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getVlr_unitario() {
        return vlr_unitario;
    }

    public void setVlr_unitario(Double vlr_unitario) {
        this.vlr_unitario = vlr_unitario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
