package SisMed;

import SisMed.controller.*;
import SisMed.interfaces.LoginFacadeInterfaceImpl;
import SisMed.repository.*;
import SisMed.service.AdminsService;
import SisMed.service.ConsultasService;
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
        db.criarTabelaConsultas();

        // Repositórios
        PacientesRepository pacientesRepository = new PacientesRepository();
        MedicosRepository medicosRepository = new MedicosRepository();
        AdminsRepository adminsRepository = new AdminsRepository();
        ConsultasRepository consultasRepository = new ConsultasRepository();

        // Serviços
        PacientesService pacientesService = new PacientesService(pacientesRepository, connection);
        MedicosService medicosService = new MedicosService(medicosRepository, connection);
        AdminsService adminsService = new AdminsService(adminsRepository, connection);
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
        MenuLoginEfetuado menuLoginEfetuado = new MenuLoginEfetuado(medicosService, pacientesService, adminsService);
        MenuLogin menuLogin = new MenuLogin(usuarioController, menuLoginEfetuado);
        MenuChoices menuChoices = new MenuChoices(adminsController, medicosController, pacientesController, consultasController);
        MenuSistema menuSistema = new MenuSistema(menuChoices, menuLogin);

        menuSistema.exibirMenu();

        db.disconnect();
    }
}
