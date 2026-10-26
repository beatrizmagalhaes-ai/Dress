package com.mycompany.jogo;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button btnJogar;

    @FXML
    private Button btnRanking;

    @FXML
    private Button btnCreditos;

    @FXML
    private Button btnEncerrar;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void abrirRanking(ActionEvent event) {
        System.out.println("Ranking...");
    }

    @FXML
    private void abrirCreditos(ActionEvent event) {
        System.out.println("Créditos...");
    }

    @FXML
    private void encerrarJogo(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}