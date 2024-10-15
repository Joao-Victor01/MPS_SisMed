package SisMed.menus;

import SisMed.command.AtualizarConsultaCommand;
import SisMed.command.CadastrarConsultaCommand;
import SisMed.controller.ConsultasController;
import SisMed.controller.RelatorioController;
import SisMed.service.ConsultasService;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuChoicesConsultas {
    private ConsultasController consultasController;
    private ConsultasService consultasService;
    private RelatorioController relatorioController;
    private Scanner scanner = new Scanner(System.in);

    public MenuChoicesConsultas(ConsultasController consultasController, RelatorioController relatorioController,
                                ConsultasService consultasService) {
        this.consultasController = consultasController;
        this.relatorioController = relatorioController;
        this.consultasService = consultasService;
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

        CadastrarConsultaCommand cadastrarConsultaCommand = new CadastrarConsultaCommand(consultasService, cpfPaciente, cpfMedico, dataConsulta, descricao);
        cadastrarConsultaCommand.execute();

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

        AtualizarConsultaCommand atualizarConsultaCommand = new AtualizarConsultaCommand(consultasService, idConsulta, novaData, novaDescricao);
        atualizarConsultaCommand.execute();

    }

    public void removerConsulta() {
        System.out.print("ID da Consulta a ser removida: ");
        Long idConsulta = scanner.nextLong();
        scanner.nextLine();

        consultasController.removerConsulta(idConsulta);
    }

    public void gerarRelatorioPdf(){
       relatorioController.relatorioPDF();
    }

    public void gerarRelatorioHtml(){
       relatorioController.relatorioHTML();
    }

}
