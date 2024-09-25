package SisMed.utils;

import SisMed.model.Admins;
import SisMed.model.Medicos;
import SisMed.model.Pacientes;
import SisMed.service.AdminsService;
import SisMed.service.MedicosService;
import SisMed.service.PacientesService;

public class MenuLoginEfetuado {

    private MedicosService medicosService;
    private PacientesService pacientesService;
    private AdminsService adminsService;

    public MenuLoginEfetuado(MedicosService medicosService, PacientesService pacientesService, AdminsService adminsService) {
        this.medicosService = medicosService;
        this.pacientesService = pacientesService;
        this.adminsService = adminsService;
    }

    public void boasVindasMedico(String userName) {
        Medicos medico = medicosService.filtrarMedicosUserName(userName);
        if (medico.getUserType() == 1) {
            System.out.println("Olá, " + medico.getNome() + "!\nO que deseja fazer?");
            System.out.println("1. Visualizar agenda;");
            // TODO implementar outras opções do médico
        } else {
            System.out.println("Tipo de usuário não correspondente! Reiniciando sistema...");
        }
    }

    public void boasVindasPaciente(String userName) {
        Pacientes paciente = pacientesService.filtrarPacienteUserName(userName);
        if (paciente.getUserType() == 2) {
            System.out.println("Olá, " + paciente.getNome() + "!\nO que deseja fazer?");
            System.out.println("1. Agendar consulta;");
            // TODO implementar outras opções do paciente
        } else {
            System.out.println("Tipo de usuário não correspondente! Reiniciando sistema...");
        }
    }

    public void boasVindasAdmin(String userName) {
        Admins admin = adminsService.filtrarAdminUserName(userName);
        if (admin.getUserType() == 3) {
            System.out.println("Olá, " + admin.getNome() + "!\nO que deseja fazer?");
            System.out.println("1. Gerenciar sistema;");
            // TODO implementar outras opções do admin
        } else {
            System.out.println("Tipo de usuário não correspondente! Reiniciando sistema...");
        }
    }
}
