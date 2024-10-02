package com.SisMed.service;

import com.SisMed.exception.DadosPacienteInvalidosException;
import com.SisMed.exception.ErroCadastroPacienteException;
import com.SisMed.exception.PacienteExistenteException;
import com.SisMed.model.Pacientes;
import com.SisMed.repository.PacientesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacientesService {
    private PacientesRepository pacientesRepository;
    private Connection connection;

    public PacientesService(PacientesRepository pacientesRepository, Connection connection){
        this.pacientesRepository = pacientesRepository;
        this.connection = connection;
    }

    public void cadastrarPaciente (Pacientes paciente){
        try {
            Pacientes pacienteExistente = pacientesRepository.buscarPorCpf(paciente.getCpf()).orElse(null);
            if (pacienteExistente != null) {
                throw new PacienteExistenteException("Paciente com CPF " + paciente.getCpf() + " já está cadastrado.", pacienteExistente);
            }

            validarDadosPaciente(paciente);

            pacientesRepository.salvar(paciente);
        } catch (PacienteExistenteException | DadosPacienteInvalidosException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroCadastroPacienteException("Erro ao cadastrar paciente.", e);
        }
    }

    public List<Pacientes> listarPacientes() {
        return pacientesRepository.listarTodos();
    }


    public void cadastrarPacienteDb(Pacientes paciente) {
        final String sql = "INSERT INTO Pacientes (nome, cpf, endereco, sexo, dataNascimento, userName, senha, userType) VALUES (?, ?, ?, ?, ?, ?, ?, 2)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, paciente.getNome());
            pstmt.setLong(2, paciente.getCpf());
            pstmt.setString(3, paciente.getEndereco());
            pstmt.setString(4, paciente.getSexo());
            pstmt.setDate(5, Date.valueOf(paciente.getDataNascimento()));
            pstmt.setString(6, paciente.getUserName());
            pstmt.setString(7, paciente.getSenha());
            pstmt.executeUpdate();
            System.out.println("Paciente inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Pacientes> listarPacientesDb() {
        List<Pacientes> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Pacientes";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Pacientes paciente = new Pacientes();
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getLong("cpf"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                pacientes.add(paciente);
            }
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public boolean loginPaciente(String userName, String senha) {
        String sql = "SELECT * FROM Pacientes WHERE userName = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pacientes filtrarPacienteUserName(String userName) {
        String sql = "SELECT * FROM Pacientes WHERE userName = ?";
        Pacientes paciente = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Pacientes();
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getLong("cpf"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                paciente.setUserName(rs.getString("userName"));
                paciente.setSenha(rs.getString("senha"));
                paciente.setUserType(rs.getInt("userType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }


    private void validarDadosPaciente(Pacientes paciente) {
        if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new DadosPacienteInvalidosException("O nome do paciente é obrigatório.");
        }
        if (paciente.getCpf() == null) {
            throw new DadosPacienteInvalidosException("O CPF do paciente é obrigatório.");
        }
    }
}
