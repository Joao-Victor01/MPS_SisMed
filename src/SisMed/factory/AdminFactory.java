package SisMed.factory;

import SisMed.model.Admin;
import SisMed.model.Usuario;

public class AdminFactory implements UsuarioFactory {
    @Override
    public Usuario criarUsuario() {
        return new Admin();
    }
}