package SisMed.view;

import SisMed.menus.MenuAcoes;
import SisMed.menus.MenuLogin;

import java.util.Scanner;

public class MenuInicial {

    private MenuAcoes menuAcoes;
    private MenuLogin menuLogin;
    private Scanner scanner = new Scanner(System.in);

    public MenuInicial(MenuAcoes menuAcoes, MenuLogin menuLogin) {
        this.menuAcoes = menuAcoes;
        this.menuLogin = menuLogin;
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("===== Sistema de Gestão Médica =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar novo Usuário");
            System.out.println("2. Listar todos os Usuários");
            System.out.println("3. Fazer login");
            System.out.println("4. Sair");
            System.out.println();

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAcoes.cadastrarUsuario();
                    break;
                case 2:
                    menuAcoes.listarUsuarios();
                    break;
                case 3:
                    menuLogin.login();
                    break;
                case 4:
                    System.out.println("Saindo do sistema.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
