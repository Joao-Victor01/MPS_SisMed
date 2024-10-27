package SisMed.observer;

import java.util.ArrayList;

public class EmailCadastroObserver implements EmailObserver{
    @Override
    public void enviarEmail(String tipo, ArrayList<String> dados) {
        System.out.println("Enviando e-mail de Cadastro para: " + dados.get(0));
    }
}
