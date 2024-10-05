package SisMed.service;

import SisMed.exception.AdminExistenteException;
import SisMed.exception.DadosAdminInvalidosException;
import SisMed.exception.DadosMedicoInvalidosException;
import SisMed.exception.ErroCadastroAdminException;
import SisMed.interfaces.RepositoryFactoryImpl;
import SisMed.model.Admins;
import SisMed.repository.AdminsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminsService {

    private final AdminsRepository adminsRepository;
    private final Connection connection;

    public AdminsService(RepositoryFactoryImpl repositoryFactory, Connection connection) {
        this.adminsRepository = repositoryFactory.createAdminsRepository();
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
        final String sql = "INSERT INTO Administradores (nome, cpf, endereco, sexo, dataNascimento, userName, senha, userType)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, 3)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, admin.getNome());
            pstmt.setLong(2, admin.getCpf());
            pstmt.setString(3, admin.getEndereco());
            pstmt.setString(4, admin.getSexo());
            pstmt.setDate(5, Date.valueOf(admin.getDataNascimento()));
            pstmt.setString(6,admin.getUserName());
            pstmt.setString(7, admin.getSenha());
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

    public boolean loginAdmin(String userName, String senha) {
        String sql = "SELECT * FROM Administradores WHERE userName = ? AND senha = ?";
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

    public Admins filtrarAdminUserName(String userName) {
        String sql = "SELECT * FROM Administradores WHERE userName = ?";
        Admins admin = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admins();
                admin.setNome(rs.getString("nome"));
                admin.setCpf(rs.getLong("cpf"));
                admin.setEndereco(rs.getString("endereco"));
                admin.setSexo(rs.getString("sexo"));
                admin.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                admin.setUserName(rs.getString("userName"));
                admin.setSenha(rs.getString("senha"));
                admin.setUserType(rs.getInt("userType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
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
