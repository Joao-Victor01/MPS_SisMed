package SisMed;

import SisMed.controller.AdminsController;
import SisMed.controller.MedicosController;
import SisMed.controller.PacientesController;
import SisMed.repository.AdminsRepository;
import SisMed.repository.MedicosRepository;
import SisMed.repository.PacientesRepository;
import SisMed.service.AdminsService;
import SisMed.service.MedicosService;
import SisMed.service.PacientesService;
import SisMed.view.MenuSistema;

public class Main {
    public static void main(String[] args) {
        PacientesRepository pacientesRepository = new PacientesRepository();
        PacientesService pacientesService = new PacientesService(pacientesRepository);
        PacientesController pacientesController = new PacientesController(pacientesService);

        MedicosRepository medicosRepository = new MedicosRepository();
        MedicosService medicosService = new MedicosService(medicosRepository);
        MedicosController medicosController = new MedicosController(medicosService);

        AdminsRepository adminsRepository = new AdminsRepository();
        AdminsService adminsService = new AdminsService(adminsRepository);
        AdminsController adminsController = new AdminsController(adminsService);

        MenuSistema menu = new MenuSistema(pacientesController, medicosController, adminsController);
        menu.exibirMenu();
    }
}