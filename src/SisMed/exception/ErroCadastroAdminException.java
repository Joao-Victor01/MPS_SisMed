package SisMed.exception;

public class ErroCadastroAdminException extends RuntimeException{
    public ErroCadastroAdminException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
