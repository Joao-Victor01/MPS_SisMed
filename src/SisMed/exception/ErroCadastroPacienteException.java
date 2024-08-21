package SisMed.exception;

public class ErroCadastroPacienteException extends RuntimeException {
    public ErroCadastroPacienteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}