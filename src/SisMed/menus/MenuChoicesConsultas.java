package SisMed.menus;

import SisMed.padroes.command.AtualizarConsultaCommand;
import SisMed.padroes.command.CadastrarConsultaCommand;
import SisMed.controller.ConsultasController;
import SisMed.controller.RelatorioController;
import SisMed.padroes.observer.EmailConsultaObserver;
import SisMed.padroes.observer.EmailObserver;
import SisMed.padroes.observer.MailNotifications;
import SisMed.service.ConsultasService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuChoicesConsultas {
    private ConsultasController consultasController;
    private ConsultasService consultasService;
    private RelatorioController relatorioController;
    private Scanner scanner = new Scanner(System.in);
    MailNotifications sistema = new MailNotifications();

    EmailObserver emailConsulta = new EmailConsultaObserver();
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
        sendMail(cpfPaciente.toString(), dataConsulta.toString());
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
        consultasController.cancelarConsulta(idConsulta);
        consultasController.removerConsulta(idConsulta);

    }

    public void concluirConsulta() {
        System.out.print("ID da Consulta a ser concluída: ");
        Long idConsulta = scanner.nextLong();
        consultasController.concluirConsulta(idConsulta);
    }

    public void gerarRelatorioPdf(){
       relatorioController.relatorioPDF();
    }

    public void gerarRelatorioHtml(){
       relatorioController.relatorioHTML();
    }

    public void sendMail(String cpfPaciente, String dataConsulta){
        sistema.adicionarObservador(emailConsulta);
        ArrayList<String> list = new ArrayList<>();
        list.add(cpfPaciente);
        list.add(dataConsulta);
        sistema.agendarConsulta(list);
    }

}
