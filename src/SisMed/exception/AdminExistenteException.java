package SisMed.exception;

import SisMed.model.Admins;

public class AdminExistenteException extends RuntimeException{
    private Admins adminExistente ;

    public AdminExistenteException(String mensagem, Admins admin){
        super(mensagem);
        this.adminExistente = admin;
    }

    public Admins getAdminExistente() {
        return adminExistente;
    }
}
