package SisMed.exception;

import SisMed.model.Consultas;

public class ConsultaExistenteException extends RuntimeException {

    private Consultas consultaExistente;

    public ConsultaExistenteException(String mensagem, Consultas consulta){
        super(mensagem);
        this.consultaExistente = consulta;
    }

    public Consultas getConsultaExistente() {
        return consultaExistente;
    }

}
