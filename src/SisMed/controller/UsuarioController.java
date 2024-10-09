package SisMed.controller;

import SisMed.database.H2Database;
import SisMed.model.Usuario;
import SisMed.service.UsuarioService;
import SisMed.interfaces.LoginFacadeInterface;

public class UsuarioController {
    private UsuarioService usuarioService;
    private LoginFacadeInterface loginFacade;
    private H2Database h2Database;

    public UsuarioController(UsuarioService usuarioService, LoginFacadeInterface loginFacade, H2Database h2Database) {
        this.usuarioService = usuarioService;
        this.loginFacade = loginFacade;
        this.h2Database = h2Database;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
    }

    public boolean loginUsuario(int tipoUsuario, String userName, String senha) {
        this.h2Database.registrarAcesso(tipoUsuario, userName);
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
