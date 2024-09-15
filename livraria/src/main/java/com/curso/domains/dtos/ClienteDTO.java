package com.curso.domains.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.curso.domains.Cidade;
import com.curso.domains.Cliente;
import com.curso.domains.enums.PersonType;
import com.curso.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClienteDTO {
    private UUID id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode ser vazio")
    private String nome;
    @NotNull(message = "O campo RG não pode ser nulo")
    @NotBlank(message = "O campo RG não pode ser vazio")
    private String rg;

    @NotNull(message = "O campo CPF não pode ser nulo")
    @CPF
    private String cpf;

    @NotNull(message = "O campo Endereço não pode ser nulo")
    @NotBlank(message = "O campo Endereço não pode ser vazio")
    private String endereco;
    @NotNull(message = "O campo Número Endereço não pode ser nulo")
    @NotBlank(message = "O campo Número Endereço não pode ser vazio")
    private String end_num;
    @NotNull(message = "O campo Bairro Endereço não pode ser nulo")
    @NotBlank(message = "O campo Bairro Endereço não pode ser vazio")
    private String end_bairro;
    @NotNull(message = "O campo CEP Endereço não pode ser nulo")
    @NotBlank(message = "O campo CEP Endereço não pode ser vazio")
    private String end_cep;
    @NotNull(message = "O campo E-mail não pode ser nulo")
    @NotBlank(message = "O campo E-mail não pode ser vazio")
    private String email;
    @NotNull(message = "O campo Senha não pode ser nulo")
    @NotBlank(message = "O campo Senha não pode ser vazio")
    private String password;
    private LocalDate createdAt = LocalDate.now();
    private Set<Integer> personType = new HashSet<>();
    private Cidade cidade;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_cad = LocalDate.now();
    private Integer status;

    public ClienteDTO() {
        addPersonType(PersonType.CLIENTE);
    }

    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.rg = obj.getRg();
        this.cpf = obj.getCpf();
        this.endereco = obj.getEndereco();
        this.end_num = obj.getEnd_num();
        this.end_bairro = obj.getEnd_bairro();
        this.end_cep = obj.getEnd_cep();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.createdAt = obj.getCreatedAt();
        this.personType = obj.getPersonType().stream().map(x -> x.getId()).collect(Collectors.toSet());
        this.data_cad = obj.getData_cad();
        this.status = obj.getStatus();
        addPersonType(PersonType.CLIENTE);
    }

    public ClienteDTO(UUID id, String nome, String rg, String cpf, String endereco, String end_num, String end_bairro,
            String end_cep, String email, String password, LocalDate createdAt, LocalDate data_cad, Status status) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.end_num = end_num;
        this.end_bairro = end_bairro;
        this.end_cep = end_cep;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.data_cad = data_cad;
        this.status = status.getId();
        addPersonType(PersonType.CLIENTE);
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnd_num() {
        return end_num;
    }

    public void setEnd_num(String end_num) {
        this.end_num = end_num;
    }

    public String getEnd_bairro() {
        return end_bairro;
    }

    public void setEnd_bairro(String end_bairro) {
        this.end_bairro = end_bairro;
    }

    public String getEnd_cep() {
        return end_cep;
    }

    public void setEnd_cep(String end_cep) {
        this.end_cep = end_cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getData_cad() {
        return data_cad;
    }

    public void setData_cad(LocalDate data_cad) {
        this.data_cad = data_cad;
    }

    public Set<PersonType> getPersonType() {
        return personType.stream().map(x -> PersonType.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPersonType(PersonType personType) {
        this.personType.add(personType.getId());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



}
