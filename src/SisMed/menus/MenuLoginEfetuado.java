package SisMed.menus;

import SisMed.model.Admin;
import SisMed.model.Medico;
import SisMed.model.Paciente;
import SisMed.model.Usuario;
import SisMed.service.UsuarioService;

import java.util.Scanner;

public class MenuLoginEfetuado {
    private UsuarioService usuarioService;
    private Scanner scanner = new Scanner(System.in);
    private MenuChoicesConsultas menuChoicesConsultas;

    public MenuLoginEfetuado(UsuarioService usuarioService, MenuChoicesConsultas menuChoicesConsultas) {
        this.usuarioService = usuarioService;
        this.menuChoicesConsultas = menuChoicesConsultas;
    }

    public void boasVindas(String userName) {
        Usuario usuario = usuarioService.filtrarUsuarioPorUserName(userName);
        if (usuario != null) {
            System.out.println("Olá, " + usuario.getNome() + "!\nO que deseja fazer?");
            exibirMenuPorTipoUsuario(usuario);
        } else {
            System.out.println("Usuário não encontrado! Reiniciando sistema...");
        }
    }

    private void exibirMenuPorTipoUsuario(Usuario usuario) {
        if (usuario instanceof Medico) {
            System.out.println("1. Visualizar agenda;");
            // TODO: implementar outras opções do médico
        } else if (usuario instanceof Paciente) {
            System.out.println("1. Marcar consulta;");
            System.out.println("2. Desmarcar consulta;");
            System.out.println("3. Ver minhas consultas marcadas;");
            System.out.println("4. Atualizar consulta;");
            System.out.println("5. Sair;");

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
                    menuChoicesConsultas.atualizarConsulta();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } else if (usuario instanceof Admin) {
            System.out.println("1. Desmarcar consulta;");
            System.out.println("2. Marcar consulta;");
            System.out.println("3. Listar todas as consultas;");
            System.out.println("4. Atualizar consulta;");
            System.out.println("5. Sair;");

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
                    menuChoicesConsultas.listarConsultas();
                    break;
                case 4:
                    menuChoicesConsultas.atualizarConsulta();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } else {
            System.out.println("Tipo de usuário não correspondente! Reiniciando sistema...");
        }
    }
}
