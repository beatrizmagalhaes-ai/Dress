package com.mycompany.jogo.dao;
import com.mycompany.jogo.database.DatabaseConnection;
import com.mycompany.jogo.model.Jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JogadorDAO {

    /**
     * Busca um jogador pelo nome (MARIANA, MAIARA, BEATRIZ, MARCO)
     */
    public Jogador buscarPorNome(String nome) {
        String sql = "SELECT id, nome, moedas, pontos_estilo, data_cadastro FROM jogador WHERE UPPER(nome) = UPPER(?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Jogador(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("moedas"),
                        rs.getInt("pontos_estilo"),
                        rs.getTimestamp("data_cadastro")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar jogador por nome: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Atualiza o saldo de moedas do jogador no banco de dados
     */
    public boolean atualizarMoedas(int jogadorId, int novasMoedas) {
        String sql = "UPDATE jogador SET moedas = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novasMoedas);
            stmt.setInt(2, jogadorId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar moedas do jogador: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Atualiza os pontos de estilo do jogador após finalizar/salvar um look
     */
    public boolean atualizarPontosEstilo(int jogadorId, int novosPontos) {
        String sql = "UPDATE jogador SET pontos_estilo = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novosPontos);
            stmt.setInt(2, jogadorId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pontos de estilo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Adiciona um item ao inventário do jogador (Comprar/Adquirir roupa/acessório)
     */
    public boolean adicionarItemAoInventario(int jogadorId, int itemId) {
        String sql = "INSERT INTO inventario (jogador_id, item_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jogadorId);
            stmt.setInt(2, itemId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar item ao inventário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}