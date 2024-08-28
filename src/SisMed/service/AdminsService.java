package SisMed.service;

import SisMed.exception.AdminExistenteException;
import SisMed.exception.DadosAdminInvalidosException;
import SisMed.exception.DadosMedicoInvalidosException;
import SisMed.exception.ErroCadastroAdminException;
import SisMed.model.Admins;
import SisMed.model.Pacientes;
import SisMed.repository.AdminsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminsService {

    private AdminsRepository adminsRepository;
    private Connection connection;

    public AdminsService(AdminsRepository adminsRepository, Connection connection) {
        this.adminsRepository = adminsRepository;
        this.connection = connection;
    }

    public void cadastrarAdmin(Admins admin) {
        try {
            Admins adminExistente = adminsRepository.buscarPorCpf(admin.getCpf()).orElse(null);
            if (adminExistente != null) {
                throw new AdminExistenteException("Administrador com CPF " + admin.getCpf() + " já está cadastrado.", adminExistente);
            }

            validarDadosAdmin(admin);

            adminsRepository.salvar(admin);
        } catch (AdminExistenteException | DadosAdminInvalidosException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroCadastroAdminException("Erro ao cadastrar Administrador.", e);
        }
    }

    public List<Admins> listarAdmins() {
        return adminsRepository.listarTodos();
    }


    public void cadastrarAdminDb(Admins admin) {
        final String sql = "INSERT INTO Administradores (nome, cpf, endereco, sexo, dataNascimento) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, admin.getNome());
            pstmt.setLong(2, admin.getCpf());
            pstmt.setString(3, admin.getEndereco());
            pstmt.setString(4, admin.getSexo());
            pstmt.setDate(5, Date.valueOf(admin.getDataNascimento()));
            pstmt.executeUpdate();
            System.out.println("Administrador inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Admins> listarAdminsDb() {
        List<Admins> admins = new ArrayList<>();
        String sql = "SELECT * FROM Administradores";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Admins admin = new Admins();
                admin.setNome(rs.getString("nome"));
                admin.setCpf(rs.getLong("cpf"));
                admin.setEndereco(rs.getString("endereco"));
                admin.setSexo(rs.getString("sexo"));
                admin.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                admins.add(admin);
            }
            if (admins.isEmpty()) {
                System.out.println("Nenhum administrador cadastrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    private void validarDadosAdmin(Admins admin) {
        if (admin.getNome() == null || admin.getNome().isEmpty()) {
            throw new DadosMedicoInvalidosException("O nome do Administrador é obrigatório.");
        }
        if (admin.getCpf() == null) {
            throw new DadosAdminInvalidosException("O CPF do Administrador é obrigatório.");
        }

    }
}
