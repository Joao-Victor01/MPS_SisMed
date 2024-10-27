package SisMed.padroes.memento;

import SisMed.model.Usuario;

import java.time.LocalDate;

public class UsuarioMemento {
    private final String nome;
    private final long cpf;
    private final String endereco;
    private final String sexo;
    private final LocalDate dataNascimento;
    private final String userName;
    private final String senha;

    public UsuarioMemento(Usuario usuario) {
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.endereco = usuario.getEndereco();
        this.sexo = usuario.getSexo();
        this.dataNascimento = usuario.getDataNascimento();
        this.userName = usuario.getUserName();
        this.senha = usuario.getSenha();
    }

    public String getNome() { return nome; }
    public long getCpf() { return cpf; }
    public String getEndereco() { return endereco; }
    public String getSexo() { return sexo; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getUserName() { return userName; }
    public String getSenha() { return senha; }
}