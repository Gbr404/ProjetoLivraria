package com.curso.domains;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.curso.domains.dtos.VendaDTO;
import com.curso.domains.enums.OrdemStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_venda = LocalDate.now();
    private String observacao;
    private Double valor_total;
    private Set<Integer> ordemstatus = new HashSet<>();

    @ManyToOne // relacao muitos para um
    @JoinColumn(name = "idcliente") // refere-se ao id de technician
    private Cliente cliente;

    @ManyToOne // relacao muitos para um
    @JoinColumn(name = "idfuncionario") // refere-se ao id de technician
    private Funcionario funcionario;

    public Venda() {
        addOrdemstatus(OrdemStatus.ABERTO);
    }

    public Venda(UUID id, String observacao, Double valor_total,
            Cliente cliente, Funcionario funcionario) {
        this.id = id;
        this.observacao = observacao;
        this.valor_total = valor_total;
        this.cliente = cliente;
        this.funcionario = funcionario;
        addOrdemstatus(OrdemStatus.ABERTO);
    }

    public Venda(VendaDTO objDto) {
        this.id = objDto.getId();
        this.observacao = objDto.getObservacao();
        this.valor_total = objDto.getValor_total();
        this.cliente = objDto.getCliente();
        this.funcionario = objDto.getFuncionario();
        addOrdemstatus(OrdemStatus.ABERTO);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getData_venda() {
        return data_venda;
    }

    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Set<OrdemStatus> getOrdemstatus() {
        return ordemstatus.stream().map(x -> OrdemStatus.toEnum(x)).collect(Collectors.toSet());
    }

    public void addOrdemstatus(OrdemStatus ordemstatus) {
        this.ordemstatus.add(ordemstatus.getId());
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
        Venda other = (Venda) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
