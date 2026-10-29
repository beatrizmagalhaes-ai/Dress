package com.mycompany.jogo;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void selecionarMariana() throws IOException {
        GameController.personagemAtual = "MARIANA";
        GameController.fundoAtual = "fundoMariana.png";
        GameController.imagemBonecoAtual = "marianaBase.png";
        App.setRoot("game");
    }

    @FXML
    private void selecionarMayara() throws IOException {
        GameController.personagemAtual = "MAYARA";
        GameController.fundoAtual = "fundoMayara.png";
        GameController.imagemBonecoAtual = "mayaraBase.png";
        App.setRoot("game");
    }

    @FXML
    private void selecionarBeatriz() throws IOException {
        GameController.personagemAtual = "BEATRIZ";
        GameController.fundoAtual = "fundoBeatriz.png";
        GameController.imagemBonecoAtual = "beatrizBase.png";
        App.setRoot("game");
    }

    @FXML
    private void selecionarMarco() throws IOException {
        GameController.personagemAtual = "MARCO";
        GameController.fundoAtual = "fundoMarco.png";
        GameController.imagemBonecoAtual = "marcoBase.png";
        App.setRoot("game");
    }

    @FXML
    private void voltarParaMenu() throws IOException {
        App.setRoot("primary");
    }
}