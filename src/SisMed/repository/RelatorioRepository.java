package SisMed.repository;

import SisMed.database.DatabaseConnectionManager;
import SisMed.model.Relatorio;

import java.sql.*;
import java.util.*;

public class RelatorioRepository implements RelatorioRepositoryInterface{

    private DatabaseConnectionManager connectionManager;
    public RelatorioRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void salvar(Relatorio relatorio) {
            String sql = "INSERT INTO Relatorio (tipo_usuario, userName, data_acesso) VALUES (?, ?, NOW())";

            try (Connection connection = connectionManager.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, relatorio.getTipoUsuario());
                pstmt.setString(2, relatorio.getUserName());
                pstmt.executeUpdate();
                System.out.println("Acesso registrado com sucesso.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public List<Relatorio> listarRelatorio() {

        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT * FROM Relatorio";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setTipoUsuario(rs.getLong("tipo_usuario"));
                relatorio.setUserName(rs.getString("userName"));
                relatorio.setDataAcesso(rs.getString("data_acesso"));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorios;
    }
    @Override
    public Optional<Relatorio> buscarRelatorioPorId(Long id) {
        return Optional.empty();
    }
}
