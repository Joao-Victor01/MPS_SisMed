package SisMed.service.relatorios;

import java.sql.SQLException;

public class RelatorioPDF {

    private final RelatorioService relatorioService = new RelatorioService();


    public void formatarRelatorio() throws SQLException {
        System.out.println("Criando relatório em PDF...");
        this.relatorioService.gerarRelatorioDeAcesso();
    }

}