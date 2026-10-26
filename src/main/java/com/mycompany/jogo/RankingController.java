
     package com.mycompany.jogo;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import com.mycompany.jogo.dao.JogadorDAO;
import com.mycompany.jogo.model.Jogador;
import java.util.List;

public class RankingController {

    @FXML
    private VBox vboxPodio; // vai ser onde vamos colocar os nomes

    @FXML
    public void initialize() {
        // Carrega o ranking do banco
        try {
            JogadorDAO dao = new JogadorDAO();
            List<Jogador> top4 = dao.listarTop4(); // se seu DAO tiver outro nome, me fala

            // Aqui depois a gente posiciona os personagens
            // por enquanto só mostra os nomes
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void voltarParaMenu() throws IOException {
        App.setRoot("primary"); // volta pra tela inicial
    }

}
