package com.mycompany.jogo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configurações do Banco de Dados PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/mbm_dress_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres"; // ⚠️ ALTERA AQUI PARA A TUA SENHA DO POSTGRESQL

    /**
     * Estabelece e retorna a conexão ativa com o PostgreSQL
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC do PostgreSQL no classpath
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do PostgreSQL não foi encontrado!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Método utilitário para testar a conexão diretamente (podes executar como Java Main Class)
     */
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexão com o banco 'mbm_dress_db' realizada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("❌ Falha ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
}