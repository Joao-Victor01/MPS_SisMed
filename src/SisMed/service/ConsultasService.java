package SisMed.service;

import SisMed.exception.*;
import SisMed.model.Consultas;
import SisMed.repository.ConsultasRepository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultasService {
    private ConsultasRepository consultasRepository;
    private Connection connection;

    public ConsultasService(ConsultasRepository consultasRepository, Connection connection) {
        this.consultasRepository = consultasRepository;
        this.connection = connection;
    }

    public void cadastrarConsulta(Consultas consulta) {
        try {
            Consultas consultaExist = consultasRepository.consultaPorId(consulta.getId()).orElse(null);
            if (consultaExist != null) {
                throw new ConsultaExistenteException("Consulta já está cadastrada.", consultaExist);
            }

            validarDadosConsulta(consulta);

            consultasRepository.salvar(consulta);
        } catch (ConsultaExistenteException | DadosConsultaInvalidosException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroCadastroConsultaException("Erro ao cadastrar consulta.");
        }
    }

    public void cadastrarConsultaDb(Consultas consulta) {
        final String sql = "INSERT INTO Consultas (cpfPaciente, cpfMedico, dataConsulta, descricao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
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

    public List<Consultas> listarConsultas() {
        return consultasRepository.listarTodos();
    }

    public List<Consultas> listarConsultasDb() {
        List<Consultas> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consultas";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Consultas consulta = new Consultas();
                        consulta.setId(rs.getLong("id"));
                consulta.setCpfMedico(rs.getLong("cpfMedico"));
                consulta.setCpfPaciente(rs.getLong("cpfPaciente"));
                consulta.setDataConsulta(LocalDate.parse(rs.getString("dataConsulta")));
                consulta.setDescricao(rs.getString("descricao"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    public void atualizarConsulta(Long idConsulta, LocalDate novaData, String novaDescricao) {
        consultasRepository.atualizar(idConsulta, novaData, novaDescricao);
    }

    public void atualizarConsultaDb(Long idConsulta, LocalDate novaData, String novaDescricao) {
        final String sql = "UPDATE Consultas SET dataConsulta = ?, descricao = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(novaData));
            pstmt.setString(2, novaDescricao);
            pstmt.setLong(3, idConsulta);
            pstmt.executeUpdate();
            System.out.println("Consulta atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerConsulta(Long idConsulta) {
        consultasRepository.remover(idConsulta);
    }

    public void removerConsultaDb(Long idConsulta) {
        final String sql = "DELETE FROM Consultas WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, idConsulta);
            pstmt.executeUpdate();
            System.out.println("Consulta removida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void validarDadosConsulta(Consultas consulta) {
        if (consulta.getDataConsulta() == null) {
            throw new DadosConsultaInvalidosException("A data e hora da consulta são obrigatórias.");
        }
        if (consulta.getCpfMedico() == null) {
            throw new DadosConsultaInvalidosException("O médico responsável é obrigatório.");
        }
        if (consulta.getCpfPaciente() == null) {
            throw new DadosConsultaInvalidosException("O paciente é obrigatório.");
        }
        if (consulta.getDescricao() == null || consulta.getDescricao().isEmpty()) {
            throw new DadosConsultaInvalidosException("Os detalhes adicionais da consulta são obrigatórios.");
        }
    }


}
