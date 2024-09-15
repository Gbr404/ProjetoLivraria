package com.curso.domains;

import java.util.UUID;

import com.curso.domains.dtos.ItemvendaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itemvenda")
public class Itemvenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne // relacao muitos para um
    @JoinColumn(name = "idlivro") // refere-se ao id de technician
    private Livro livro;

    @ManyToOne // relacao muitos para um
    @JoinColumn(name = "idvenda") // refere-se ao id de technician
    private Venda venda;

    private Integer qtd;
    private Double vlr_unitario;
    private Double total;

    public Itemvenda() {
    }

    public Itemvenda(UUID id, Livro livro, Venda venda, Integer qtd, Double vlr_unitario, Double total) {
        this.id = id;
        this.livro = livro;
        this.venda = venda;
        this.qtd = qtd;
        this.vlr_unitario = vlr_unitario;
        this.total = total;
    }

    public Itemvenda(ItemvendaDTO obj) {
        this.id = obj.getId();
        this.livro = obj.getLivro();
        this.venda = obj.getVenda();
        this.qtd = obj.getQtd();
        this.vlr_unitario = obj.getVlr_unitario();
        this.total = obj.getTotal();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((livro == null) ? 0 : livro.hashCode());
        result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
        Itemvenda other = (Itemvenda) obj;
        if (livro == null) {
            if (other.livro != null)
                return false;
        } else if (!livro.equals(other.livro))
            return false;
        if (venda == null) {
            if (other.venda != null)
                return false;
        } else if (!venda.equals(other.venda))
            return false;
        return true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
