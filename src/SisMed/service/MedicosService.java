package SisMed.service;

import SisMed.exception.DadosMedicoInvalidosException;
import SisMed.exception.ErroCadastroMedicoException;
import SisMed.exception.MedicoExistenteException;
import SisMed.model.Medicos;
import SisMed.model.Pacientes;
import SisMed.repository.MedicosRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicosService {

    private MedicosRepository medicosRepository;
    private Connection connection;

    public MedicosService(MedicosRepository medicosRepository, Connection connection){
        this.medicosRepository = medicosRepository;
        this.connection = connection;
    }

    public void cadastrarMedico (Medicos medico){
        try {
            Medicos medicoExistente = medicosRepository.buscarPorCpf(medico.getCpf()).orElse(null);
            if (medicoExistente != null) {
                throw new MedicoExistenteException("Medico com CPF " + medico.getCpf() + " já está cadastrado.", medicoExistente);
            }

            validarDadosMedico(medico);

            medicosRepository.salvar(medico);
        } catch (MedicoExistenteException | DadosMedicoInvalidosException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroCadastroMedicoException("Erro ao cadastrar médico.", e);
        }
    }
    public List<Medicos> listarMedicos() {
        return medicosRepository.listarTodos();
    }

    public void cadastrarMedicoDb(Medicos medico) {
        final String sql = "INSERT INTO Medicos (nome, cpf, crm, endereco, sexo, dataNascimento, especializacoes) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, medico.getNome());
            pstmt.setLong(2, medico.getCpf());
            pstmt.setString(3, medico.getCrm());
            pstmt.setString(4, medico.getEndereco());
            pstmt.setString(5, medico.getSexo());
            pstmt.setDate(6, Date.valueOf(medico.getDataNascimento()));
            pstmt.setString(7, medico.getEspecializacoes());
            pstmt.executeUpdate();
            System.out.println("Médico inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Medicos> listarMedicosDb() {
        List<Medicos> medicos = new ArrayList<>();
        String sql = "SELECT * FROM Medicos";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Medicos medico = new Medicos();
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getLong("cpf"));
                medico.setCrm(rs.getString("crm"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setSexo(rs.getString("sexo"));
                medico.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                medicos.add(medico);
            }
            if (medicos.isEmpty()) {
                System.out.println("Nenhum médico cadastrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    private void validarDadosMedico(Medicos medico) {
        if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new DadosMedicoInvalidosException("O nome do médico é obrigatório.");
        }
        if (medico.getCpf() == null) {
            throw new DadosMedicoInvalidosException("O CPF do médico é obrigatório.");
        }
        if(medico.getCrm() == null || medico.getCrm().isEmpty()){
            throw new DadosMedicoInvalidosException("O CRM do médico é obrigatório.");
        }
    }
}
