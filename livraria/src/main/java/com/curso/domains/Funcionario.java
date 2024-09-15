package com.curso.domains;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.curso.domains.dtos.FuncionarioDTO;
import com.curso.domains.enums.PersonType;
import com.curso.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Venda> vendas = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_contrato = LocalDate.now();
    private String num_clt;

    private Integer status;

    public Funcionario() {
        super();
        addPersonType(PersonType.FUNCIONARIO);
    }

    public Funcionario(UUID id, String nome, String rg, String cpf, String endereco, String end_num, String end_bairro,
            String end_cep, String email, String password, String num_clt, Cidade cidade, Status status) {
        super(id, nome, rg, cpf, endereco, end_num, end_bairro, end_cep, email, password, cidade);
        this.num_clt = num_clt;
        this.status = status.getId();
        addPersonType(PersonType.FUNCIONARIO);
    }

    public Funcionario(FuncionarioDTO obj) {
        super(obj.getId(), obj.getNome(), obj.getRg(), obj.getCpf(), obj.getEndereco(), obj.getEnd_num(),
                obj.getEnd_bairro(), obj.getEnd_cep(), obj.getEmail(), obj.getPassword(),
                obj.getCidade());
        this.num_clt = obj.getNum_clt();
        this.status = obj.getStatus();
        addPersonType(PersonType.FUNCIONARIO);
    }

    public LocalDate getData_contrato() {
        return data_contrato;
    }

    public void setData_contrato(LocalDate data_contrato) {
        this.data_contrato = data_contrato;
    }

    public String getNum_clt() {
        return num_clt;
    }

    public void setNum_clt(String num_clt) {
        this.num_clt = num_clt;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
