package SisMed.menus;

import SisMed.controller.UsuarioController;

import java.util.Scanner;

public class MenuLogin {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController usuarioController;
    private MenuLoginEfetuado menuLoginEfetuado;

    public MenuLogin(UsuarioController usuarioController, MenuLoginEfetuado menuLoginEfetuado) {
        this.usuarioController = usuarioController;
        this.menuLoginEfetuado = menuLoginEfetuado;
    }

    public void login() {
        System.out.println("Escolha uma opção:");
        System.out.println("\t1. Login Médico;\n\t2. Login Paciente;\n\t3. Login Administrador");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome de Usuário: ");
        String userName = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (usuarioController.loginUsuario(opcao, userName, senha)) {
            System.out.println("Login efetuado com sucesso!");
            menuLoginEfetuado.boasVindas(userName);
        } else {
            System.out.println("Login falhou! Verifique suas credenciais.");
        }
    }

    public void loginOAuth() {

        System.out.println("Digite o nome de usuário:");
        String userName = scanner.nextLine();
        System.out.println("Digite a senha:");
        String senha = scanner.nextLine();

        boolean loginSucesso = usuarioController.loginUsuario(4, userName, senha);

        //apenas uma simulação de um login com o google/facebook para demonstrar o adapter
        if (loginSucesso) {
            System.out.println("Login OAuth realizado com sucesso!");
            System.exit(0);
        } else {
            System.out.println("Falha no login. Tente novamente.");
            System.exit(0);
        }
    }

}
