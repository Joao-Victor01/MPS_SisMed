package SisMed.view;

import SisMed.controller.AdminsController;
import SisMed.controller.MedicosController;
import SisMed.controller.PacientesController;
import SisMed.utils.MenuChoices;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuSistema {

    private MenuChoices menuChoices;
    private Scanner scanner = new Scanner(System.in);

    public MenuSistema(MenuChoices menuChoices) {
        this.menuChoices = menuChoices;
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("===== Sistema de Gestão Médica =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar novo Paciente");
            System.out.println("2. Cadastrar novo Médico");
            System.out.println("3. Cadastrar novo Administrador");
            System.out.println("4. Listar todos os Usuários");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuChoices.cadastrarPaciente();
                    break;
                case 2:
                    menuChoices.cadastrarMedico();
                    break;
                case 3:
                    menuChoices.cadastrarAdmin();
                    break;
                case 4:
                    menuChoices.listarUsuarios();
                    break;
                case 5:
                    System.out.println("Saindo do sistema.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


}
