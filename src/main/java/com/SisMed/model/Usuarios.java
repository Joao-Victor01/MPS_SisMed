package com.SisMed.model;

import java.time.LocalDate;

public class Usuarios {
    private String nome;
    private Long cpf;
    private String endereco;
    private String sexo;
    private LocalDate dataNascimento;
    private String userName;
    private String senha;
    private Integer userType;


    // Getters e Setters
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUserName() {
        return userName;
    }

    public void createUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser vazio.");
        }
        if (userName.length() > 12) {
            throw new IllegalArgumentException("Nome de usuário não pode ter mais que 12 caracteres.");
        }
        if (userName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Nome de usuário não pode conter números.");
        }
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.length() < 8 || senha.length() > 128) {
            throw new IllegalArgumentException("Senha deve ter entre 8 e 128 caracteres.");
        }
        if (!senha.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um número.");
        }
        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula.");
        }
        if (!senha.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra minúscula.");
        }
        if (!senha.matches(".*[!@#$%^&*()-+=].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere especial.");
        }
        if (senha.contains(" ")) {
            throw new IllegalArgumentException("Senha não pode conter espaços em branco.");
        }
        this.senha = senha;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
