package com.mycompany.jogo.dao;

import com.mycompany.jogo.database.DatabaseConnection;
import com.mycompany.jogo.model.Jogador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {

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
        }
        return null;
    }

    public boolean atualizarMoedas(int jogadorId, int novasMoedas) {
        String sql = "UPDATE jogador SET moedas = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novasMoedas);
            stmt.setInt(2, jogadorId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar moedas do jogador: " + e.getMessage());
            return false;
        }
    }

    public List<Jogador> obterRankingPorMoedas() {
        List<Jogador> lista = new ArrayList<>();
        String sql = "SELECT id, nome, moedas, pontos_estilo, data_cadastro FROM jogador ORDER BY moedas DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Jogador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("moedas"),
                    rs.getInt("pontos_estilo"),
                    rs.getTimestamp("data_cadastro")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ranking por moedas: " + e.getMessage());
        }
        return lista;
    }

    public boolean exportarRankingParaCSV(String caminhoArquivo) {
        List<Jogador> ranking = obterRankingPorMoedas();
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            writer.println("Posicao,Nome,Moedas,PontosEstilo");
            int pos = 1;
            for (Jogador j : ranking) {
                writer.println(pos + "," + j.getNome() + "," + j.getMoedas() + "," + j.getPontosEstilo());
                pos++;
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivo CSV do ranking: " + e.getMessage());
            return false;
        }
    }
}