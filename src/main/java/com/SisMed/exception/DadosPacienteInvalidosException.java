package com.SisMed.exception;

public class DadosPacienteInvalidosException extends RuntimeException {
    public DadosPacienteInvalidosException(String mensagem) {
        super(mensagem);
    }
}