package SisMed.utils;

import SisMed.model.Admins;
import SisMed.model.Medicos;
import SisMed.model.Pacientes;
import SisMed.service.AdminsService;
import SisMed.service.MedicosService;
import SisMed.service.PacientesService;

import java.util.Scanner;

public class MenuLoginEfetuado {

    private MedicosService medicosService;
    private PacientesService pacientesService;
    private AdminsService adminsService;
    private Scanner scanner = new Scanner(System.in);
    private MenuChoicesConsultas menuChoicesConsultas;

    public MenuLoginEfetuado(MedicosService medicosService, PacientesService pacientesService, AdminsService adminsService, MenuChoicesConsultas menuChoicesConsultas) {
        this.medicosService = medicosService;
        this.pacientesService = pacientesService;
        this.adminsService = adminsService;
        this.menuChoicesConsultas = menuChoicesConsultas;
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
            System.out.println("1. Marcar consulta;");
            System.out.println("2. Desmarcar consulta;");
            System.out.println("3. Ver minhas consultas marcadas;");
            System.out.println("4. Sair;");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuChoicesConsultas.cadastrarConsulta();
                    break;
                case 2:
                    menuChoicesConsultas.removerConsulta();
                    break;
                case 3:
                    menuChoicesConsultas.listarConsultas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } else {
            System.out.println("Tipo de usuário não correspondente! Reiniciando sistema...");
        }
    }

    public void boasVindasAdmin(String userName) {
        Admins admin = adminsService.filtrarAdminUserName(userName);
        if (admin.getUserType() == 3) {
            System.out.println("Olá, " + admin.getNome() + "!\nO que deseja fazer?");
            System.out.println("1. Desmarcar consulta;");
            System.out.println("2. Marcar consulta;");
            System.out.println("3. Listar todas as consultas;");
            System.out.println("4. Sair;");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuChoicesConsultas.removerConsulta();
                    break;
                case 2:
                    menuChoicesConsultas.cadastrarConsulta();
                    break;
                case 3:
                    menuChoicesConsultas.listarTodasConsultas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            // TODO implementar outras opções do admin
        } else {
            System.out.println("Tipo de usuário não correspondente! Reiniciando sistema...");
        }
    }
}
