package SisMed.controller;

import SisMed.model.Relatorio;
import SisMed.service.RelatorioService;

import static java.time.LocalTime.now;

public class RelatorioController {

    private RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    public void registrarAcesso(long op, String userName) {
        try {
            Relatorio relatorio = new Relatorio();
            relatorio.setTipoUsuario(op);
            relatorio.setUserName(userName);
            relatorio.setDataAcesso(String.valueOf(now()));
            relatorioService.registrarAcesso(relatorio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void gerarRelatorio() {

        try {
            if (relatorioService.listarRelatorio().isEmpty()) {
                System.out.println("Não há nada para ver por aqui :) .");
            } else{
                relatorioService.listarRelatorio().forEach(relatorio -> {
                    System.out.println("O usuário "+ relatorio.getUserName() + ", seu perfil: "+ relatorioService.setUserType(relatorio.getTipoUsuario()) +", acessou o sistema em: " + relatorioService.formatarData(relatorio.getDataAcesso()) +";");
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void relatorioHTML() {
        System.out.println("-------------- Gerando seu relatorio em HTML ----------------");
        gerarRelatorio();
        System.out.println("------------------------  FIM   ----------------------------");
    }

    public void relatorioPDF() {
        System.out.println("-------------- Gerando seu relatorio em PDF ----------------");
        gerarRelatorio();
        System.out.println("------------------------  FIM   ----------------------------");
    }

}
