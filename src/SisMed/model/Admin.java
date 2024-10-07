package SisMed.model;

import SisMed.interfaces.AdminInterface;

public class Admin extends Usuario implements AdminInterface {
    @Override
    public Integer getTipoUsuario() {
        return 3;
    }
}


