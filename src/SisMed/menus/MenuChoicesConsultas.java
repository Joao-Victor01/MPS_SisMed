package SisMed.menus;

import SisMed.controller.ConsultasController;
import SisMed.service.relatorios.RelatorioHTML;
import SisMed.service.relatorios.RelatorioPDF;
import SisMed.service.relatorios.RelatorioService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuChoicesConsultas {
    private ConsultasController consultasController;
    private Scanner scanner = new Scanner(System.in);

    public MenuChoicesConsultas(ConsultasController consultasController) {
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

        consultasController.cadastrarConsulta(cpfPaciente, cpfMedico, dataConsulta, descricao);
    }

    public void listarConsultas() {
        consultasController.listarConsultas();
    }

    public void atualizarConsulta() {
        System.out.print("ID da Consulta a ser atualizada: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Nova data da consulta (yyyy-MM-dd): ");
        LocalDate novaData = LocalDate.parse(scanner.nextLine());

        System.out.print("Nova descrição: ");
        String novaDescricao = scanner.nextLine();

        consultasController.atualizarConsulta(idConsulta, novaData, novaDescricao);
    }

    public void removerConsulta() {
        System.out.print("ID da Consulta a ser removida: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();

        consultasController.removerConsulta(idConsulta);
    }

    public void gerarRelatorio() throws SQLException {
        RelatorioPDF relatorioPDF = new RelatorioPDF();
        relatorioPDF.formatarRelatorio();
    }
    public void gerarRelatorioHtml() throws SQLException {
        RelatorioHTML relatorioHTML = new RelatorioHTML();
        relatorioHTML.formatarRelatorio();
    }

}
