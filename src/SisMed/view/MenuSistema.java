package SisMed.view;

import SisMed.utils.MenuChoices;
import SisMed.utils.MenuLogin;

import java.util.Scanner;

public class MenuSistema {

    private MenuChoices menuChoices;
    private MenuLogin menuLogin;
    private Scanner scanner = new Scanner(System.in);

    public MenuSistema(MenuChoices menuChoices, MenuLogin menuLogin) {
        this.menuChoices = menuChoices;
        this.menuLogin = menuLogin;
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("===== Sistema de Gestão Médica =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar novo Paciente");
            System.out.println("2. Cadastrar novo Médico");
            System.out.println("3. Cadastrar novo Administrador");
            System.out.println("4. Listar todos os Usuários");
            System.out.println("5. Fazer login");
            System.out.println("6. Sair");

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
                    menuLogin.login();
                    break;
                case 6:
                    System.out.println("Saindo do sistema.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
