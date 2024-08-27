package SisMed.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Database {
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:SisMedDB", "sa", "");
            System.out.println("Conectado ao banco de dados.");
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

    public void criarTabelaPacientes() {
        String sql = "CREATE TABLE IF NOT EXISTS Pacientes (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(255) NOT NULL, " +
                "cpf VARCHAR(11) NOT NULL, " +
                "endereco VARCHAR(255), " +
                "sexo VARCHAR(15), " +
                "dataNascimento DATE);";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Pacientes criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
