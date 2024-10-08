package com.SisMed.repository;

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
                "cpf VARCHAR(11) NOT NULL UNIQUE, " +
                "endereco VARCHAR(255), " +
                "sexo VARCHAR(15), " +
                "dataNascimento DATE, " +
                "ficha VARCHAR(500), " +
                "historicoMedico VARCHAR(500)," +
                "userName VARCHAR(12), " +
                "senha VARCHAR(128), " +
                "userType INT);";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Pacientes criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarTabelaMedicos() {
        String sql = "CREATE TABLE IF NOT EXISTS Medicos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(255) NOT NULL, " +
                "cpf VARCHAR(11) NOT NULL UNIQUE, " +
                "crm VARCHAR(20) NOT NULL, " +
                "endereco VARCHAR(255), " +
                "sexo VARCHAR(15), " +
                "dataNascimento DATE, " +
                "especializacoes VARCHAR(200)," +
                "userName VARCHAR(12)," +
                "senha VARCHAR(128), " +
                "userType INT);";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Médicos criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarTabelaAdmins() {
        String sql = "CREATE TABLE IF NOT EXISTS Administradores (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(255) NOT NULL, " +
                "cpf VARCHAR(11) NOT NULL UNIQUE, " +
                "endereco VARCHAR(255), " +
                "sexo VARCHAR(15), " +
                "dataNascimento DATE," +
                "userName VARCHAR(12)," +
                "senha VARCHAR(128)," +
                "userType INT); ";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Administradores criada com sucesso.");
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
                "FOREIGN KEY (cpfPaciente) REFERENCES Pacientes(cpf), " +
                "FOREIGN KEY (cpfMedico) REFERENCES Medicos(cpf));";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela Consultas criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
