package SisMed.service;

import SisMed.exception.DadosPacienteInvalidosException;
import SisMed.exception.ErroCadastroPacienteException;
import SisMed.exception.PacienteExistenteException;
import SisMed.model.Pacientes;
import SisMed.repository.H2Database;
import SisMed.repository.PacientesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacientesService {
    private PacientesRepository pacientesRepository;
    private Connection connection;
    private H2Database database;

    public PacientesService(PacientesRepository pacientesRepository, Connection connection, H2Database database){
        this.pacientesRepository = pacientesRepository;
        this.connection = connection;
        this.database = database;
    }

    //inserir paciente em variável local
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
        return pacientesRepository.listarTodos();  // Localmente
    }


    //inserir paciente no banco de dados
    public void inserirPaciente(Pacientes paciente) {
        final String sql = "INSERT INTO Pacientes (nome, cpf, endereco, sexo, dataNascimento) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, paciente.getNome());
            pstmt.setLong(2, paciente.getCpf());
            pstmt.setString(3, paciente.getEndereco());
            pstmt.setString(4, paciente.getSexo());
            pstmt.setDate(5, Date.valueOf(paciente.getDataNascimento()));
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


    private void validarDadosPaciente(Pacientes paciente) {
        if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new DadosPacienteInvalidosException("O nome do paciente é obrigatório.");
        }
        if (paciente.getCpf() == null) {
            throw new DadosPacienteInvalidosException("O CPF do paciente é obrigatório.");
        }
    }
}
