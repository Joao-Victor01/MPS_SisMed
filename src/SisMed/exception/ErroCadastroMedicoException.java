package SisMed.exception;

public class ErroCadastroMedicoException extends RuntimeException{
    public ErroCadastroMedicoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
