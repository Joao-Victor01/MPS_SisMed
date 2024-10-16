package SisMed.model;

import SisMed.memento.UsuarioMemento;

import java.time.LocalDate;

public abstract class Usuario {
    private String nome;
    private Long cpf;
    private String endereco;
    private String sexo;
    private LocalDate dataNascimento;
    private String userName;
    private String senha;

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public abstract Integer getTipoUsuario();

    public UsuarioMemento saveToMemento() {
        return new UsuarioMemento(this);
    }

    public void restoreFromMemento(UsuarioMemento memento) {
        this.nome = memento.getNome();
        this.cpf = memento.getCpf();
        this.endereco = memento.getEndereco();
        this.sexo = memento.getSexo();
        this.dataNascimento = memento.getDataNascimento();
        this.userName = memento.getUserName();
        this.senha = memento.getSenha();
    }
}
