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
import SisMed.view.MenuSistema;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

        public static void main(String[] args) {
            // Conectar ao banco de dados
            H2Database db = new H2Database();
            db.connect();
            Connection connection = db.getConnection();
            db.criarTabelaPacientes();

            // Criar repositórios
            PacientesRepository pacientesRepository = new PacientesRepository();
            MedicosRepository medicosRepository = new MedicosRepository();
            AdminsRepository adminsRepository = new AdminsRepository();

            // Criar serviços
            PacientesService pacientesService = new PacientesService(pacientesRepository, connection, db);
            MedicosService medicosService = new MedicosService(medicosRepository);
            AdminsService adminsService = new AdminsService(adminsRepository);

            // Criar controladores
            PacientesController pacientesController = new PacientesController(pacientesService);
            MedicosController medicosController = new MedicosController(medicosService);
            AdminsController adminsController = new AdminsController(adminsService);

            // Exibir o menu
            MenuSistema menu = new MenuSistema(pacientesController, medicosController, adminsController);
            menu.exibirMenu();

            // Desconectar do banco de dados

            db.disconnect();
        }
}