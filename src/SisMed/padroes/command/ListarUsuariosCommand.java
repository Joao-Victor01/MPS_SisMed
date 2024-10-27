package SisMed.padroes.command;

import SisMed.service.UsuarioService;

public class ListarUsuariosCommand implements Command {
    private UsuarioService usuarioService;

    public ListarUsuariosCommand(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void execute() {
        usuarioService.listarUsuarios();
    }
}
