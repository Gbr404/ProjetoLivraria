package com.curso.domains.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.curso.domains.Cliente;
import com.curso.domains.Funcionario;
import com.curso.domains.Venda;
import com.curso.domains.enums.OrdemStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VendaDTO {
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_venda = LocalDate.now();
    private String observacao;

    @NotNull(message = "O campo Valor Total é requerido")
    @NotBlank(message = "O campo Valor Total não pode ser vazio")
    private Double valor_total;
    @NotNull(message = "O campo Cliente é requerido")
    @NotBlank(message = "O campo Cliente não pode ser vazio")
    private Cliente cliente;
    @NotNull(message = "O campo Funcionario é requerido")
    @NotBlank(message = "O campo Funcionario não pode ser vazio")
    private Funcionario funcionario;
    private Set<Integer> ordemstatus = new HashSet<>();

    public VendaDTO() {
        addOrdemstatus(OrdemStatus.ABERTO);
    }

    public VendaDTO(Venda obj) {
        this.id = obj.getId();
        this.observacao = obj.getObservacao();
        this.valor_total = obj.getValor_total();
        this.cliente = obj.getCliente();
        this.funcionario = obj.getFuncionario();
        addOrdemstatus(OrdemStatus.ABERTO);
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

    public Set<OrdemStatus> getOrdemstatus() {
        return ordemstatus.stream().map(x -> OrdemStatus.toEnum(x)).collect(Collectors.toSet());
    }

    public void addOrdemstatus(OrdemStatus ordemStatus) {
        this.ordemstatus.add(ordemStatus.getId());
    }

}
