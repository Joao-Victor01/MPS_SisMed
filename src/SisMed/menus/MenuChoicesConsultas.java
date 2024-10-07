package SisMed.menus;

import SisMed.controller.ConsultasController;


import java.time.LocalDate;
import java.util.Scanner;

public class MenuChoicesConsultas {

    private ConsultasController consultasController;
    private Scanner scanner = new Scanner(System.in);

    public MenuChoicesConsultas(ConsultasController consultasController){

        this.consultasController = consultasController;
    }
    public void cadastrarConsulta() {

        System.out.print("Cpf do Paciente: ");
        Long cpfPaciente = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Cpf do Médico: ");
        Long cpfMedico = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Data da consulta (yyyy-MM-dd): ");
        LocalDate dataConsulta = LocalDate.parse(scanner.nextLine());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        consultasController.cadastrarConsultaDb(cpfPaciente, cpfMedico, dataConsulta, descricao);

    }

    public void listarConsultas() {
            consultasController.listarConsultas();

    }

    public void listarTodasConsultas() {
        consultasController.listarConsultas();
    }

    public void atualizarConsulta() {
        System.out.println("Escolha uma opção:");
        System.out.println("\t1.Atualizar localmente;\n\t2.Atualizar no Banco de Dados;");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da Consulta a ser atualizada: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Nova data da consulta (yyyy-MM-dd): ");
        LocalDate novaData = LocalDate.parse(scanner.nextLine());

        System.out.print("Nova descrição: ");
        String novaDescricao = scanner.nextLine();

        if(opcao == 1){
            consultasController.atualizarConsulta(idConsulta, novaData, novaDescricao);
        } else if(opcao == 2) {
            consultasController.atualizarConsultaDb(idConsulta, novaData, novaDescricao);
        } else{
            System.out.println("Opção Inválida.");
            atualizarConsulta();
        }
    }

    public void removerConsulta() {
        System.out.println("Escolha uma opção:");
        System.out.println("\t1.Remover localmente;\n\t2.Remover no Banco de Dados;");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da Consulta a ser removida: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();

        if(opcao == 1){
            consultasController.removerConsulta(idConsulta);
        } else if(opcao == 2) {
            consultasController.removerConsultaDb(idConsulta);
        } else{
            System.out.println("Opção Inválida.");
            removerConsulta();
        }
    }
}
