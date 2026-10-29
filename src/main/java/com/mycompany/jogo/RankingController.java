package com.mycompany.jogo;

import com.mycompany.jogo.dao.JogadorDAO;
import com.mycompany.jogo.model.Jogador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

public class RankingController {

    private JogadorDAO jogadorDAO = new JogadorDAO();

    @FXML
    private void initialize() {
        // Carrega e exibe os dados do ranking ao abrir a tela
        List<Jogador> ranking = jogadorDAO.obterRankingPorMoedas();
        for (int i = 0; i < ranking.size(); i++) {
            Jogador j = ranking.get(i);
            System.out.println((i + 1) + "º Lugar: " + j.getNome() + " - " + j.getMoedas() + " moedas");
        }
    }

    @FXML
    private void voltarParaMenu() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void exportarRankingCSV(ActionEvent event) {
        boolean exportou = jogadorDAO.exportarRankingParaCSV("ranking_mbm_dress.csv");
        if (exportou) {
            System.out.println("Ranking exportado para 'ranking_mbm_dress.csv' com sucesso!");
        }
    }
}