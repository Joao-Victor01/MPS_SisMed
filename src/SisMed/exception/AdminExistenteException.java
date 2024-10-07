package SisMed.exception;

import SisMed.model.Admin;

public class AdminExistenteException extends RuntimeException{
    private Admin adminExistente ;

    public AdminExistenteException(String mensagem, Admin admin){
        super(mensagem);
        this.adminExistente = admin;
    }

    public Admin getAdminExistente() {
        return adminExistente;
    }
}
