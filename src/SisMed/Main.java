package SisMed;

import SisMed.controller.ConsultasController;
import SisMed.controller.RelatorioController;
import SisMed.controller.UsuarioController;
import SisMed.database.DatabaseConnectionManager;
import SisMed.database.H2Database;
import SisMed.interfaces.LoginFacadeInterfaceImpl;
import SisMed.login.AdminLoginStrategy;
import SisMed.login.MedicoLoginStrategy;
import SisMed.login.PacienteLoginStrategy;
import SisMed.menus.MenuAcoes;
import SisMed.menus.MenuChoicesConsultas;
import SisMed.menus.MenuLogin;
import SisMed.repository.ConsultasRepository;
import SisMed.repository.RelatorioRepository;
import SisMed.repository.UsuarioRepository;
import SisMed.service.ConsultasService;
import SisMed.service.RelatorioService;
import SisMed.service.UsuarioService;
import SisMed.menus.MenuLoginEfetuado;
import SisMed.view.MenuInicial;
import SisMed.adapter.OAuthService;
import SisMed.adapter.OAuthAdapter;
import org.h2.tools.Server;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Inicia o console web H2
        Server h2Server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();

        // Mensagem para acessar o console
        System.out.println("Console web do H2 iniciado em: http://localhost:8082");


        // 1. Inicializar o DatabaseConnectionManager
        DatabaseConnectionManager dbManager = new DatabaseConnectionManager("jdbc:h2:mem:SisMedDB", "user", "root");
        H2Database database = new H2Database();
        database.connect();
        database.criarTabelaUsuarios();
        database.criarTabelaConsultas();
        database.criarTabelaAccessUsers();

        // 2. Criar as Repositories usando o DatabaseConnectionManager
        UsuarioRepository usuarioRepository = new UsuarioRepository(dbManager);
        ConsultasRepository consultasRepository = new ConsultasRepository(dbManager);
        RelatorioRepository relatorioRepository =  new RelatorioRepository(dbManager);

        // 3. Criar a UsuarioService e ConsultasService
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        ConsultasService consultasService = new ConsultasService(consultasRepository);
        RelatorioService relatorioService = new RelatorioService(relatorioRepository);

        // 4. Criar a LoginFacadeInterfaceImpl e registrar as estratégias de login
        LoginFacadeInterfaceImpl loginFacade = new LoginFacadeInterfaceImpl();
        loginFacade.registrarLoginStrategy(1, new MedicoLoginStrategy(usuarioService));
        loginFacade.registrarLoginStrategy(2, new PacienteLoginStrategy(usuarioService));
        loginFacade.registrarLoginStrategy(3, new AdminLoginStrategy(usuarioService));

        //Simulacao de login OAuth
        OAuthService oauthService = new OAuthService();
        loginFacade.registrarLoginStrategy(4, new OAuthAdapter(oauthService));

        // 5. Criar a UsuarioController e ConsultasController
        UsuarioController usuarioController = new UsuarioController(usuarioService, loginFacade);
        ConsultasController consultasController = new ConsultasController(consultasService);
        RelatorioController relatorioController = new RelatorioController(relatorioService);

        // 6. Inicializar Menus
        MenuChoicesConsultas menuChoicesConsultas = new MenuChoicesConsultas(consultasController, relatorioController, consultasService);
        MenuAcoes menuAcoes = new MenuAcoes(usuarioService);
        MenuLoginEfetuado menuLoginEfetuado = new MenuLoginEfetuado(usuarioService, menuChoicesConsultas);
        MenuLogin menuLogin = new MenuLogin(usuarioController, menuLoginEfetuado, relatorioController);
        MenuInicial menuInicial = new MenuInicial(menuAcoes, menuLogin);

        // 7. Exibir o menu principal
        menuInicial.exibirMenu();

        // Desconectar do banco de dados ao final
        dbManager.closeConnection(dbManager.getConnection());
    }
}
