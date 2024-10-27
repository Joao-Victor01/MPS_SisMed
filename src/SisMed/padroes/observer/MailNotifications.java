package SisMed.padroes.observer;

import java.util.ArrayList;
import java.util.List;

public class MailNotifications {
    private List<EmailObserver> observadores = new ArrayList<>();

    public void adicionarObservador(EmailObserver observador) {
        observadores.add(observador);
    }

    public void removerObservador(EmailObserver observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String tipo, ArrayList<String> dados) {
        for (EmailObserver observador : observadores) {
            observador.enviarEmail(tipo, dados);
        }
    }
    public void cadastrarUsuario(ArrayList<String> dados) {
        System.out.println("\t\tBuild do email ... \n\n\nCaro, "+ dados.get(0) +".\n\n Somos da SisMed e agradecemos por confiar sua saúde em nossas mãos. Este e-mail confirma a conclusão do seu cadastro.\n(Não responder este email)\n\n\n\tSismed© copyrigth 2024.");
        notificarObservadores("Cadastro", dados);
    }

    public void agendarConsulta(ArrayList<String> dados) {
        System.out.println("\t\tBuild do email ... \n\n\nOlá.\n\n Sua consulta acaba de ser confirmada para o próximo dia "+ dados.get(1) +".\n Somos gratos pela preferência.\n(Não responder este email)\n\n\n\tSismed© copyrigth 2024.");
        notificarObservadores("Consulta", dados);
    }
}
