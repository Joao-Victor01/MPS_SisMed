package SisMed.service.relatorios;

import SisMed.database.H2Database;
import SisMed.model.RelatorioAcesso;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RelatorioService {

    Connection connection = null;

    public RelatorioService() {

    }

    public void gerarRelatorioDeAcesso() throws SQLException {
        System.out.println("Relatório de Acesso dos Usuários:");
        List<String> relatorios = coletarDados();
        for (String relatorio : relatorios) {
            System.out.println(relatorio);
        }
    }
    public List<String> coletarDados() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:SisMedDB", "user", "");
        List<String> relatorios = new ArrayList<>();
        String sql = "SELECT tipo_usuario, userName, data_acesso FROM access_user";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long tipoUsuario = rs.getLong("tipo_usuario");
                String userName = rs.getString("userName");
                String dataAcesso = rs.getString("data_acesso");

                String dataFormatada = formatarData(dataAcesso);
                String tipoUsuarioDescricao;
                if (tipoUsuario == 1) {
                    tipoUsuarioDescricao = "Médico";
                } else if (tipoUsuario == 2) {
                    tipoUsuarioDescricao = "Paciente";
                } else {
                    tipoUsuarioDescricao = "Adminstrador";
                }

                String relatorio = String.format("Usuario do tipo: %s, username: %s, acessou o sistema no dia %s.",
                        tipoUsuarioDescricao, userName, dataFormatada);
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatorios;
    }

    private String formatarData(String dataAcesso) {

        Timestamp timestamp = Timestamp.valueOf(dataAcesso);
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return localDateTime.format(formatter);
    }

}