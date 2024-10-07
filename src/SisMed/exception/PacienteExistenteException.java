package SisMed.exception;

import SisMed.model.Paciente;

public class PacienteExistenteException extends RuntimeException{
    private Paciente pacienteExistente;

    public PacienteExistenteException(String mensagem, Paciente pacienteExistente){
        super(mensagem);
        this.pacienteExistente = pacienteExistente;
    }

    public Paciente getPacienteExistente() {
        return pacienteExistente;
    }
}
