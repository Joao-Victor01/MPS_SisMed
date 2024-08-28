package SisMed;

import SisMed.controller.AdminsController;
import SisMed.controller.MedicosController;
import SisMed.controller.PacientesController;
import SisMed.repository.AdminsRepository;
import SisMed.repository.H2Database;
import SisMed.repository.MedicosRepository;
import SisMed.repository.PacientesRepository;
import SisMed.service.AdminsService;
import SisMed.service.MedicosService;
import SisMed.service.PacientesService;
import SisMed.utils.MenuChoices;
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

            PacientesRepository pacientesRepository = new PacientesRepository();
            MedicosRepository medicosRepository = new MedicosRepository();
            AdminsRepository adminsRepository = new AdminsRepository();

            PacientesService pacientesService = new PacientesService(pacientesRepository, connection);
            MedicosService medicosService = new MedicosService(medicosRepository, connection);
            AdminsService adminsService = new AdminsService(adminsRepository, connection);

            PacientesController pacientesController = new PacientesController(pacientesService);
            MedicosController medicosController = new MedicosController(medicosService);
            AdminsController adminsController = new AdminsController(adminsService);

            MenuChoices menuChoices = new MenuChoices(adminsController, medicosController, pacientesController);

            MenuSistema menu = new MenuSistema(menuChoices);
            menu.exibirMenu();

            db.disconnect();
        }
}