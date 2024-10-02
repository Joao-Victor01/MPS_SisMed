package com.SisMed.utils;

import com.SisMed.controller.UsuarioController;

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
        System.out.println("\t1.Login Médico;\n\t2.Login Paciente;\n\t3.Login Administrador\n\t4.Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 4) {
            System.out.println("Saindo...");
            System.exit(0);
        }

        System.out.print("Nome de Usuário: ");
        String userName = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean resultadoLogin = usuarioController.loginUsuario(opcao, userName, senha);

        if (resultadoLogin) {
            if (opcao == 1) {
                menuLoginEfetuado.boasVindasMedico(userName);
            } else if (opcao == 2) {
                menuLoginEfetuado.boasVindasPaciente(userName);
            } else if (opcao == 3) {
                menuLoginEfetuado.boasVindasAdmin(userName);
            } else {
                System.out.println("Credenciais inválidas. Tente novamente.");
                login();
            }
        }
    }
}
