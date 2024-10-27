package SisMed.controller;

import SisMed.database.H2Database;
import SisMed.model.Usuario;
import SisMed.service.UsuarioService;
import SisMed.interfaces.LoginFacadeInterface;

public class UsuarioController {
    private UsuarioService usuarioService;
    private LoginFacadeInterface loginFacade;

    public UsuarioController(UsuarioService usuarioService, LoginFacadeInterface loginFacade) {
        this.usuarioService = usuarioService;
        this.loginFacade = loginFacade;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
    }

    public boolean loginUsuario(int tipoUsuario, String userName, String senha) {
        return loginFacade.login(tipoUsuario, userName, senha);
    }

    public void listarUsuarios() {

        if (usuarioService.listarUsuarios().isEmpty()){
            System.out.println("Nenhum usuário cadastrado!");
        }else {
            usuarioService.listarUsuarios().forEach(usuario -> {
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("Tipo de Usuário: " + usuario.getTipoUsuario());
                System.out.println("-----------------------------------");
            });
        }
    }

}
