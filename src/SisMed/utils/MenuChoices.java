package SisMed.utils;

import SisMed.controller.AdminsController;
import SisMed.controller.MedicosController;
import SisMed.controller.PacientesController;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuChoices {

    private PacientesController pacientesController;
    private MedicosController medicosController;
    private AdminsController adminsController;
    private Scanner scanner = new Scanner(System.in);

    public MenuChoices(AdminsController adminsController, MedicosController medicosController, PacientesController pacientesController){
        this.pacientesController = pacientesController;
        this.adminsController = adminsController;
        this.medicosController = medicosController;
    }

    public void cadastrarPaciente() {
        System.out.println("Escolha uma opção:");
        System.out.println("\t1.Cadastrar localmente;\n\t2.Cadastrar no Banco de Dados;");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if(opcao == 1){
            System.out.println("Opção escolhida: cadastro local!\nPreencha os campos a seguir:\n");
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

            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            pacientesController.cadastrarPaciente(nome, cpf, endereco, sexo, dataNascimento, login, senha);

        } else if (opcao == 2) {
            System.out.println("Opção escolhida: cadastro na base de dados!\nPreencha os campos a seguir:\n");
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

            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            pacientesController.cadastrarPacienteDb(nome, cpf, endereco, sexo, dataNascimento, login, senha);


        } else{
            System.out.println("Opção Inválida.");
            cadastrarPaciente();
        }

    }

    public void cadastrarMedico() {
        System.out.println("Escolha uma opção:");
        System.out.println("\t1.Cadastrar localmente;\n\t2.Cadastrar no Banco de Dados;");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if(opcao == 1){
            System.out.println("Opção escolhida: cadastro local!\nPreencha os campos a seguir:\n");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            Long cpf = scanner.nextLong();
            scanner.nextLine();

            System.out.print("CRM: ");
            String crm = scanner.nextLine();

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Sexo: ");
            String sexo = scanner.nextLine();

            System.out.print("Data de Nascimento (yyyy-MM-dd): ");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

            System.out.print("Especializações: ");
            String especializacoes = scanner.nextLine();

            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            medicosController.cadastrarMedico(nome, cpf, crm, especializacoes, endereco, sexo, dataNascimento, login, senha);

        } else if (opcao == 2) {
            System.out.println("Opção escolhida: cadastro na base de dados!\nPreencha os campos a seguir:\n");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            Long cpf = scanner.nextLong();
            scanner.nextLine();

            System.out.print("CRM: ");
            String crm = scanner.nextLine();

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Sexo: ");
            String sexo = scanner.nextLine();

            System.out.print("Data de Nascimento (yyyy-MM-dd): ");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

            System.out.print("Especializações: ");
            String especializacoes = scanner.nextLine();

            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            medicosController.cadastrarMedicoDb(nome, cpf, crm, especializacoes, endereco, sexo, dataNascimento, login, senha);

        } else{
            System.out.println("Opção Inválida.");
            cadastrarMedico();
        }
    }

    public void cadastrarAdmin() {
        System.out.println("Escolha uma opção:");
        System.out.println("\t1.Cadastrar localmente;\n\t2.Cadastrar no Banco de Dados;");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if(opcao == 1){
            System.out.println("Opção escolhida: cadastro local!\nPreencha os campos a seguir:\n");

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

            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();


            adminsController.cadastrarAdmin(nome, cpf, endereco, sexo, dataNascimento, login, senha);

        } else if (opcao == 2) {
            System.out.println("Opção escolhida: cadastro na base de dados!\nPreencha os campos a seguir:\n");

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

            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            adminsController.cadastrarAdminDb(nome,cpf, endereco, sexo, dataNascimento, login, senha);

        } else{
            System.out.println("Opção Inválida.");
            cadastrarMedico();
        }
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
