package SisMed.padroes.command;

import SisMed.model.Usuario;
import SisMed.padroes.observer.EmailCadastroObserver;
import SisMed.padroes.observer.EmailObserver;
import SisMed.padroes.observer.MailNotifications;
import SisMed.service.UsuarioService;

import java.util.ArrayList;

// Comando para cadastrar usuário
public class CadastrarUsuarioCommand implements Command {
    private UsuarioService usuarioService;
    private Usuario usuario;
    MailNotifications sistema = new MailNotifications();

    EmailObserver emailCadastro = new EmailCadastroObserver();

    public CadastrarUsuarioCommand(UsuarioService usuarioService, Usuario usuario) {
        this.usuarioService = usuarioService;
        this.usuario = usuario;
        sendMail(usuario.getUserName());
    }

    @Override
    public void execute() {
        usuarioService.cadastrarUsuario(usuario); // Usando o service diretamente
    }

    public void sendMail(String userName){
        sistema.adicionarObservador(emailCadastro);
        ArrayList<String> userNameList = new ArrayList<>();
        userNameList.add(userName);
        sistema.cadastrarUsuario(userNameList);
    }

}
