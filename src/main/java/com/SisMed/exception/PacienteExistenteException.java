package com.SisMed.exception;

import com.SisMed.model.Pacientes;

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
