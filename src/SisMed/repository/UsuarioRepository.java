package SisMed.repository;

import SisMed.database.DatabaseConnectionManager;
import SisMed.factory.UsuarioFactoryRegistry;
import SisMed.model.Admin;
import SisMed.model.Medico;
import SisMed.model.Paciente;
import SisMed.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements UsuarioRepositoryInterface{
    private DatabaseConnectionManager connectionManager;
    private final int userTypeMedico = 1;
    private final int userTypePaciente = 2;
    private final int userTypeAdmin = 3;

    public UsuarioRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nome, cpf, endereco, sexo, dataNascimento, userName, " +
                "senha, tipoUsuario, crm, especializacoes, ficha, historicoMedico) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setLong(2, usuario.getCpf());
            pstmt.setString(3, usuario.getEndereco());
            pstmt.setString(4, usuario.getSexo());
            pstmt.setDate(5, Date.valueOf(usuario.getDataNascimento()));
            pstmt.setString(6, usuario.getUserName());
            pstmt.setString(7, usuario.getSenha());
            pstmt.setInt(8, obterTipoUsuario(usuario)); // Método que retorna o tipo como int

            // Campos específicos
            if (usuario instanceof Medico) {
                pstmt.setString(9, ((Medico) usuario).getCrm());
                pstmt.setString(10, ((Medico) usuario).getEspecializacoes());
                pstmt.setNull(11, Types.VARCHAR);
                pstmt.setNull(12, Types.VARCHAR);
            } else if (usuario instanceof Paciente) {
                pstmt.setNull(9, Types.VARCHAR);
                pstmt.setNull(10, Types.VARCHAR);
                pstmt.setString(11, ((Paciente) usuario).getFicha());
                pstmt.setString(12, ((Paciente) usuario).getHistoricoMedico());
            } else { // Admin
                pstmt.setNull(9, Types.VARCHAR);
                pstmt.setNull(10, Types.VARCHAR);
                pstmt.setNull(11, Types.VARCHAR);
                pstmt.setNull(12, Types.VARCHAR);
            }
            pstmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginUsuario(String userName, String senha) {
        String sql = "SELECT * FROM Usuarios WHERE userName = ? AND senha = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se o usuário for encontrado
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

    @Override
    public Usuario filtrarUsuarioPorUserName(String userName) {
        String sql = "SELECT * FROM Usuarios WHERE userName = ?";
        Usuario usuario = null;

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int tipoUsuario = rs.getInt("tipoUsuario");

                usuario = UsuarioFactoryRegistry.criarUsuario(tipoUsuario);
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getLong("cpf"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                usuario.setUserName(rs.getString("userName"));
                usuario.setSenha(rs.getString("senha"));

                if (usuario instanceof Medico) {
                    ((Medico) usuario).setCrm(rs.getString("crm"));
                    ((Medico) usuario).setEspecializacoes(rs.getString("especializacoes"));
                } else if (usuario instanceof Paciente) {
                    ((Paciente) usuario).setFicha(rs.getString("ficha"));
                    ((Paciente) usuario).setHistoricoMedico(rs.getString("historicoMedico"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = UsuarioFactoryRegistry.criarUsuario(rs.getInt("tipoUsuario"));

                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getLong("cpf"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                usuario.setUserName(rs.getString("userName"));
                usuario.setSenha(rs.getString("senha"));

                // Campos específicos
                if (usuario instanceof Medico) {
                    ((Medico) usuario).setCrm(rs.getString("crm"));
                    ((Medico) usuario).setEspecializacoes(rs.getString("especializacoes"));
                } else if (usuario instanceof Paciente) {
                    ((Paciente) usuario).setFicha(rs.getString("ficha"));
                    ((Paciente) usuario).setHistoricoMedico(rs.getString("historicoMedico"));
                }
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private int obterTipoUsuario(Usuario usuario) {
        if (usuario instanceof Medico) {
            return userTypeMedico; // Médico
        } else if (usuario instanceof Paciente) {
            return userTypePaciente; // Paciente
        } else if (usuario instanceof Admin) {
            return userTypeAdmin; // Administrador
        }
        throw new IllegalArgumentException("Tipo de usuário desconhecido! " +
                "Escolha:\n\t1.Médico;\n\t2.Paciente;\n\t3.Administrador.");
    }
}
