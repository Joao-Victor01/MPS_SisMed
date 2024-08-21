package SisMed.exception;

import SisMed.model.Pacientes;
import SisMed.model.Usuarios;

public class PacienteExistenteException extends RuntimeException{
    private Pacientes pacienteExistente;

    public PacienteExistenteException(String mensagem, Pacientes pacienteExistente){
        super(mensagem);
        this.pacienteExistente = pacienteExistente;
    }

    public Pacientes getPacienteExistente() {
        return pacienteExistente;
    }
}
