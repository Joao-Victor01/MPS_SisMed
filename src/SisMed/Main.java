package SisMed;

import SisMed.controller.UsuarioController;
import SisMed.database.DatabaseConnectionManager;
import SisMed.interfaces.LoginFacadeInterfaceImpl;
import SisMed.login.AdminLoginStrategy;
import SisMed.login.MedicoLoginStrategy;
import SisMed.login.PacienteLoginStrategy;
import SisMed.repository.UsuarioRepository;
import SisMed.repository.UsuarioRepositoryInterface;
import SisMed.service.UsuarioService;
import SisMed.menus.MenuAcoes;
import SisMed.menus.MenuLoginEfetuado;

public class Main {

    public static void main(String[] args) {
        // 1. Inicializar o DatabaseConnectionManager
        DatabaseConnectionManager dbManager = new DatabaseConnectionManager("jdbc:h2:mem:SisMedDB", "user", "");

        // 2. Criar a UsuarioRepository usando o DatabaseConnectionManager
        UsuarioRepositoryInterface usuarioRepository = new UsuarioRepository(dbManager);

        // 3. Criar a UsuarioService usando a UsuarioRepository
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        // 4. Criar a LoginFacadeInterfaceImpl e registrar as estratégias de login
        LoginFacadeInterfaceImpl loginFacade = new LoginFacadeInterfaceImpl();
        loginFacade.registrarLoginStrategy(1, new MedicoLoginStrategy(usuarioService)); // Passando UsuarioService
        loginFacade.registrarLoginStrategy(2, new PacienteLoginStrategy(usuarioService)); // Passando UsuarioService
        loginFacade.registrarLoginStrategy(3, new AdminLoginStrategy(usuarioService)); // Passando UsuarioService

        // 5. Criar a UsuarioController usando a UsuarioService e a LoginFacade
        UsuarioController usuarioController = new UsuarioController(usuarioService, loginFacade);

        // 6. Inicializar outros componentes conforme necessário (por exemplo, menus)
        MenuAcoes menuAcoes = new MenuAcoes(usuarioController);
        MenuLoginEfetuado menuLoginEfetuado = new MenuLoginEfetuado(usuarioController);

        // Aqui você pode adicionar a lógica para exibir o menu ou iniciar o sistema
        menuAcoes.exibirMenu(); // Supondo que você tenha um método para exibir o menu

        // Desconectar do banco de dados ao final
        dbManager.closeConnection(dbManager.getConnection());
    }
}
