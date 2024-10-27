package SisMed.view;

import SisMed.menus.MenuAcoes;
import SisMed.menus.MenuLogin;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuInicial {

    private MenuAcoes menuAcoes;
    private MenuLogin menuLogin;
    private Scanner scanner = new Scanner(System.in);

    public MenuInicial(MenuAcoes menuAcoes, MenuLogin menuLogin) {
        this.menuAcoes = menuAcoes;
        this.menuLogin = menuLogin;
    }

    public void exibirMenu() throws SQLException {
        while (true) {
            System.out.println("===== Sistema de Gestão Médica =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar novo Usuário");
            System.out.println("2. Atualizar dados do Usuário");
            System.out.println("3. Listar todos os Usuários");
            System.out.println("4. Fazer login");
            System.out.println("5. Login via OAuth (Google/Facebook)");
            System.out.println("6. Sair");
            System.out.println();

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAcoes.cadastrarUsuario();
                    break;
                case 2:
                    menuAcoes.atualizarUsuario();
                    break;
                case 3:
                    menuAcoes.listarUsuarios();
                    break;
                case 4:
                    menuLogin.login();
                    break;
                case 5:
                    menuLogin.loginOAuth();
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