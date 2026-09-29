package com.lunaltas.ListaUsuarios.model;

public class User {

    private Long id;
    private String name;
    private String arroba;
    private String cpf;
    private String email;
    private Integer idade;

    public User() {
    }

    public User(Long id, String name, String arroba, String cpf, String email, Integer idade) {
        this.id = id;
        this.name = name;
        this.arroba = arroba;
        this.cpf = cpf;
        this.email = email;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArroba() {
        return arroba;
    }

    public void setArroba(String arroba) {
        this.arroba = arroba;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}