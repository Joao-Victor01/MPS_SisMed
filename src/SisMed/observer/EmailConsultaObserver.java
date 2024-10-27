package SisMed.observer;

import java.util.ArrayList;

public class EmailConsultaObserver implements EmailObserver {
    @Override
    public void enviarEmail(String tipo, ArrayList<String> dados) {
        System.out.println("Enviando e-mail de Consulta para: " + dados.get(0));
        // Lógica para enviar o e-mail de cadastro
    }
}
