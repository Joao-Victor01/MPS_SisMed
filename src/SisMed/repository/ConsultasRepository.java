package SisMed.repository;

import SisMed.database.DatabaseConnectionManager;
import SisMed.model.Consultas;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultasRepository implements ConsultasRepositoryInterface {

    private DatabaseConnectionManager connectionManager;

    public ConsultasRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void salvar(Consultas consulta) {
        final String sql = "INSERT INTO Consultas (cpfPaciente, cpfMedico, dataConsulta, descricao) VALUES (?, ?, ?, ?)";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, consulta.getCpfPaciente());
            pstmt.setLong(2, consulta.getCpfMedico());
            pstmt.setDate(3, Date.valueOf(consulta.getDataConsulta()));
            pstmt.setString(4, consulta.getDescricao());
            pstmt.executeUpdate();
            System.out.println("Consulta inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultas> listarTodos() {
        List<Consultas> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consultas";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Consultas consulta = new Consultas();
                consulta.setId(rs.getLong("id"));
                consulta.setCpfMedico(rs.getLong("cpfMedico"));
                consulta.setCpfPaciente(rs.getLong("cpfPaciente"));
                consulta.setDataConsulta(rs.getDate("dataConsulta").toLocalDate());
                consulta.setDescricao(rs.getString("descricao"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    @Override
    public void atualizar(Long idConsulta, LocalDate novaData, String novaDescricao) {
        final String sql = "UPDATE Consultas SET dataConsulta = ?, descricao = ? WHERE id = ?";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(novaData));
            pstmt.setString(2, novaDescricao);
            pstmt.setLong(3, idConsulta);
            pstmt.executeUpdate();
            System.out.println("Consulta atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(Long idConsulta) {
        final String sql = "DELETE FROM Consultas WHERE id = ?";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, idConsulta);
            pstmt.executeUpdate();
            System.out.println("Consulta removida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Consultas> consultaPorId(Long idConsulta) {
        final String sql = "SELECT * FROM Consultas WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, idConsulta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Consultas consulta = new Consultas();
                consulta.setId(rs.getLong("id"));
                consulta.setCpfMedico(rs.getLong("cpfMedico"));
                consulta.setCpfPaciente(rs.getLong("cpfPaciente"));
                consulta.setDataConsulta(rs.getDate("dataConsulta").toLocalDate());
                consulta.setDescricao(rs.getString("descricao"));
                return Optional.of(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
