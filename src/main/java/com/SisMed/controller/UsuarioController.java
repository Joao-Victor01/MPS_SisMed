package com.SisMed.controller;

import com.SisMed.interfaces.LoginFacadeInterface;

public class UsuarioController {
    private LoginFacadeInterface loginFacadeInterface;

    public UsuarioController(LoginFacadeInterface loginFacadeInterface) {
        this.loginFacadeInterface = loginFacadeInterface;
    }

    public boolean loginUsuario(Integer userType, String userName, String senha) {
        if (loginFacadeInterface.login(userType, userName, senha)) {
            System.out.println("Login efetuado!");
            return true;
        } else {
            System.out.println("Login falhou! Verifique suas credenciais.");
            return false;
        }
    }
}
