package SisMed.login;

import SisMed.model.Paciente;
import SisMed.service.UsuarioService;

public class PacienteLoginStrategy implements LoginStrategy {
    private UsuarioService usuarioService;

    public PacienteLoginStrategy(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean login(String userName, String senha) {
        return usuarioService.loginUsuario(userName, senha);
    }
}