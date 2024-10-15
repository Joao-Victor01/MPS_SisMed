package SisMed.command;

import SisMed.model.Usuario;
import SisMed.service.UsuarioService;

// Comando para cadastrar usuário
public class CadastrarUsuarioCommand implements Command {
    private UsuarioService usuarioService;
    private Usuario usuario;

    public CadastrarUsuarioCommand(UsuarioService usuarioService, Usuario usuario) {
        this.usuarioService = usuarioService;
        this.usuario = usuario;
    }

    @Override
    public void execute() {
        usuarioService.cadastrarUsuario(usuario); // Usando o service diretamente
    }
}
