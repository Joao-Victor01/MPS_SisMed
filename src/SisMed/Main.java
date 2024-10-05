package SisMed;

import SisMed.controller.*;
import SisMed.interfaces.LoginFacadeInterfaceImpl;
import SisMed.interfaces.RepositoryFactoryImpl;
import SisMed.repository.*;
import SisMed.service.AdminsService;
import SisMed.service.ConsultasService;
import SisMed.service.MedicosService;
import SisMed.service.PacientesService;
import SisMed.utils.MenuChoices;
import SisMed.utils.MenuChoicesConsultas;
import SisMed.utils.MenuLogin;
import SisMed.utils.MenuLoginEfetuado;
import SisMed.view.MenuSistema;
import SisMed.repository.PacientesRepository;

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
        db.criarTabelaConsultas();

        // Repositórios
        RepositoryFactoryImpl repositoryFactory = new RepositoryFactoryImpl();
        ConsultasRepository consultasRepository = new ConsultasRepository();

        // Serviços
        PacientesService pacientesService = new PacientesService(repositoryFactory, connection);
        MedicosService medicosService = new MedicosService(repositoryFactory, connection);
        AdminsService adminsService = new AdminsService(repositoryFactory, connection);
        ConsultasService consultasService = new ConsultasService(consultasRepository, connection);

        // Interfaces
        LoginFacadeInterfaceImpl loginFacade = new LoginFacadeInterfaceImpl(medicosService, pacientesService, adminsService);

        // Controllers
        PacientesController pacientesController = new PacientesController(pacientesService);
        MedicosController medicosController = new MedicosController(medicosService);
        AdminsController adminsController = new AdminsController(adminsService);
        UsuarioController usuarioController = new UsuarioController(loginFacade);
        ConsultasController consultasController = new ConsultasController(consultasService);

        // Menus
        MenuChoicesConsultas menuChoicesConsultas = new MenuChoicesConsultas(consultasController);
        MenuLoginEfetuado menuLoginEfetuado = new MenuLoginEfetuado(medicosService, pacientesService, adminsService, menuChoicesConsultas);
        MenuLogin menuLogin = new MenuLogin(usuarioController, menuLoginEfetuado);
        MenuChoices menuChoices = new MenuChoices(adminsController, medicosController, pacientesController, consultasController);
        MenuSistema menuSistema = new MenuSistema(menuChoices, menuLogin);

        menuSistema.exibirMenu();

        db.disconnect();
    }
}
