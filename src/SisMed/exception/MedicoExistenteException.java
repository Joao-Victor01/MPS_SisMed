package SisMed.exception;

import SisMed.model.Medicos;

public class MedicoExistenteException extends RuntimeException {
    private Medicos medicoExistente;

    public MedicoExistenteException(String mensagem, Medicos medico){
        super(mensagem);
        this.medicoExistente = medico;
    }

    public Medicos getMedicoExistente() {
        return medicoExistente;
    }
}
