package SisMed.view;

import SisMed.controller.AdminsController;
import SisMed.controller.MedicosController;
import SisMed.controller.PacientesController;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuSistema {

    private PacientesController pacientesController;
    private MedicosController medicosController;

    private AdminsController adminsController;
    private Scanner scanner = new Scanner(System.in);

    public MenuSistema(PacientesController pacientesController, MedicosController medicosController, AdminsController adminsController) {
        this.pacientesController = pacientesController;
        this.medicosController = medicosController;
        this.adminsController = adminsController;
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
                    cadastrarPaciente();
                    break;
                case 2:
                    cadastrarMedico();
                    break;
                case 3:
                    cadastrarAdmin();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    System.out.println("Saindo do sistema.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        Long cpf = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Data de Nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

        pacientesController.cadastrarPaciente(nome, cpf, endereco, sexo, dataNascimento);

        System.out.println("Paciente cadastrado com sucesso!");
    }

    private void cadastrarMedico() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        Long cpf = scanner.nextLong();
        scanner.nextLine();

        System.out.print("CRM: ");
        String crm = scanner.nextLine();

        System.out.print("Especializações: ");
        String especializacoes = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Data de Nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

        medicosController.cadastrarMedico(nome,cpf, crm,
                especializacoes, endereco, sexo, dataNascimento);

        System.out.println("Médico cadastrado com sucesso!");
    }

    private void cadastrarAdmin() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        Long cpf = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Data de Nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

        adminsController.cadastrarAdmin(nome, cpf, endereco, sexo, dataNascimento);

        System.out.println("Administrador cadastrado com sucesso!");
    }
    public void listarUsuarios(){

        System.out.println("Pacientes cadastrados: \n");
        pacientesController.listarPacientes();
        System.out.println("---------------------- \n");

        System.out.println("Médicos cadastrados: \n");
        medicosController.listarMedicos();
        System.out.println("---------------------- \n");

        System.out.println("Administradores cadastrados: \n");
        adminsController.listarAdmins();
        System.out.println("---------------------- \n");

    }
}
