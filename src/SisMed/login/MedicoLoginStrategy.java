package SisMed.login;

import SisMed.service.UsuarioService;

public class MedicoLoginStrategy implements LoginStrategy {
    private UsuarioService usuarioService;

    public MedicoLoginStrategy(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean login(String userName, String senha) {
        return usuarioService.loginUsuario(userName, senha);
    }
}
