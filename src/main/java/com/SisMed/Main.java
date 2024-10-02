package com.SisMed;

import com.SisMed.controller.*;
import com.SisMed.interfaces.LoginFacadeInterfaceImpl;
import com.SisMed.repository.*;
import com.SisMed.service.AdminsService;
import com.SisMed.service.ConsultasService;
import com.SisMed.service.MedicosService;
import com.SisMed.service.PacientesService;
import com.SisMed.utils.MenuChoices;
import com.SisMed.utils.MenuChoicesConsultas;
import com.SisMed.utils.MenuLogin;
import com.SisMed.utils.MenuLoginEfetuado;
import com.SisMed.view.MenuSistema;

import java.sql.Connection;

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
        MenuChoicesConsultas menuChoicesConsultas = new MenuChoicesConsultas(consultasController);
        MenuLoginEfetuado menuLoginEfetuado = new MenuLoginEfetuado(medicosService, pacientesService, adminsService, menuChoicesConsultas);
        MenuLogin menuLogin = new MenuLogin(usuarioController, menuLoginEfetuado);
        MenuChoices menuChoices = new MenuChoices(adminsController, medicosController, pacientesController, consultasController);
        MenuSistema menuSistema = new MenuSistema(menuChoices, menuLogin);

        menuSistema.exibirMenu();

        db.disconnect();
    }
}
