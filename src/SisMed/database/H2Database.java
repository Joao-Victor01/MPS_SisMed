package SisMed.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Database {
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:SisMedDB", "user", "");
            System.out.println("Conectado ao banco de dados.");
            criarTabelaUsuarios(); // Chama para criar a tabela de usuários
            criarTabelaConsultas(); // Chama para criar a tabela de consultas
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Desconectado do banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarTabelaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(255) NOT NULL, " +
                "cpf VARCHAR(11) NOT NULL UNIQUE, " +
                "endereco VARCHAR(255), " +
                "sexo VARCHAR(15), " +
                "dataNascimento DATE, " +
                "userName VARCHAR(12) NOT NULL UNIQUE, " +
                "senha VARCHAR(128) NOT NULL, " +
                "tipoUsuario INT NOT NULL, " +
                "crm VARCHAR(20), " +
                "especializacoes VARCHAR(200), " +
                "ficha VARCHAR(500), " +
                "historicoMedico VARCHAR(500));";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Usuários criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarTabelaConsultas() {
        String sql = "CREATE TABLE IF NOT EXISTS Consultas (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cpfPaciente VARCHAR(11) NOT NULL, " +
                "cpfMedico VARCHAR(11) NOT NULL, " +
                "dataConsulta DATE NOT NULL, " +
                "descricao VARCHAR(500), " +
                "FOREIGN KEY (cpfPaciente) REFERENCES Usuarios(cpf), " + // Corrigido para referenciar Usuarios
                "FOREIGN KEY (cpfMedico) REFERENCES Usuarios(cpf));"; // Corrigido para referenciar Usuarios
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Consultas criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
