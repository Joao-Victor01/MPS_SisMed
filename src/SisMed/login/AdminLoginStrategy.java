package SisMed.login;

import SisMed.model.Admin;
import SisMed.service.UsuarioService;

public class AdminLoginStrategy implements LoginStrategy {
    private UsuarioService usuarioService;

    public AdminLoginStrategy(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean login(String userName, String senha) {
        return usuarioService.loginUsuario(userName, senha);
    }
}