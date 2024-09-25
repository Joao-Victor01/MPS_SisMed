package SisMed;

import SisMed.controller.AdminsController;
import SisMed.controller.MedicosController;
import SisMed.controller.PacientesController;
import SisMed.controller.UsuarioController;
import SisMed.interfaces.LoginFacadeInterfaceImpl;
import SisMed.repository.AdminsRepository;
import SisMed.repository.H2Database;
import SisMed.repository.MedicosRepository;
import SisMed.repository.PacientesRepository;
import SisMed.service.AdminsService;
import SisMed.service.MedicosService;
import SisMed.service.PacientesService;
import SisMed.utils.MenuChoices;
import SisMed.utils.MenuLogin;
import SisMed.utils.MenuLoginEfetuado;
import SisMed.view.MenuSistema;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        H2Database db = new H2Database();
        db.connect();
        Connection connection = db.getConnection();
        db.criarTabelaPacientes();
        db.criarTabelaMedicos();
        db.criarTabelaAdmins();

        // Repositórios
        PacientesRepository pacientesRepository = new PacientesRepository();
        MedicosRepository medicosRepository = new MedicosRepository();
        AdminsRepository adminsRepository = new AdminsRepository();

        // Serviços
        PacientesService pacientesService = new PacientesService(pacientesRepository, connection);
        MedicosService medicosService = new MedicosService(medicosRepository, connection);
        AdminsService adminsService = new AdminsService(adminsRepository, connection);

        // Controllers
        PacientesController pacientesController = new PacientesController(pacientesService);
        MedicosController medicosController = new MedicosController(medicosService);
        AdminsController adminsController = new AdminsController(adminsService);

        // LoginFacadeInterfaceImpl para centralizar o login
        LoginFacadeInterfaceImpl loginFacade = new LoginFacadeInterfaceImpl(medicosService, pacientesService, adminsService);

        // UsuarioController precisa da interface LoginFacadeInterface
        UsuarioController usuarioController = new UsuarioController(loginFacade);

        MenuLoginEfetuado menuLoginEfetuado = new MenuLoginEfetuado(medicosService, pacientesService, adminsService);
        MenuLogin menuLogin = new MenuLogin(usuarioController, menuLoginEfetuado);

        // Instanciando o menu de escolhas (cadastros, listagens, etc.)
        MenuChoices menuChoices = new MenuChoices(adminsController, medicosController, pacientesController);

        // Instanciando o menu principal
        MenuSistema menuSistema = new MenuSistema(menuChoices, menuLogin);

        // Sistema
        menuSistema.exibirMenu();

        db.disconnect();
    }
}
