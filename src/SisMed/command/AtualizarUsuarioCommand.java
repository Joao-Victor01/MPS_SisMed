package SisMed.command;

import SisMed.service.UsuarioService;

import java.time.LocalDate;

public class AtualizarUsuarioCommand implements Command {
    private UsuarioService usuarioService;
    private String userName;
    private LocalDate novaDataNasc;
    private String novoNome;
    private String novoEndereco;
    private String novaSenha;

    public AtualizarUsuarioCommand(UsuarioService usuarioService, String userName, LocalDate novaDataNasc,
                                   String novoNome, String novoEndereco, String novaSenha) {
        this.usuarioService = usuarioService;
        this.userName = userName;
        this.novaDataNasc = novaDataNasc;
        this.novoNome = novoNome;
        this.novoEndereco = novoEndereco;
        this.novaSenha = novaSenha;
    }

    @Override
    public void execute() {
        usuarioService.atualizarDadosUsuario(userName, novaDataNasc, novoNome, novoEndereco, novaSenha);
    }
}