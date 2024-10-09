package SisMed.service.relatorios;

import java.sql.SQLException;

public class RelatorioHTML{

    private RelatorioService relatorioService = new RelatorioService();


    public void formatarRelatorio() throws SQLException {
        System.out.println("Criando relatório em HTML...");
        this.relatorioService.gerarRelatorioDeAcesso();
    }

}
