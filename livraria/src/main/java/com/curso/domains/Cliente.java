package com.curso.domains;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.curso.domains.dtos.ClienteDTO;
import com.curso.domains.enums.PersonType;
import com.curso.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_cad = LocalDate.now();

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Cliente() {
        super();
        addPersonType(PersonType.CLIENTE);
    }

    public Cliente(UUID id, String nome, String rg, String cpf, String endereco, String end_num, String end_bairro,
            String end_cep, String email, String password, Cidade cidade, Status status) {
        super(id, nome, rg, cpf, endereco, end_num, end_bairro, end_cep, email, password, cidade);
        this.status = status.getId();
        addPersonType(PersonType.CLIENTE);
    }

    public Cliente(ClienteDTO obj) {
        super(obj.getId(), obj.getNome(), obj.getRg(), obj.getCpf(), obj.getEndereco(), obj.getEnd_num(),
                obj.getEnd_bairro(), obj.getEnd_cep(), obj.getEmail(), obj.getPassword(), obj.getCidade());
        this.status = obj.getStatus();
        addPersonType(PersonType.CLIENTE);
    }

    public LocalDate getData_cad() {
        return data_cad;
    }

    public void setData_cad(LocalDate data_cad) {
        this.data_cad = data_cad;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

}
