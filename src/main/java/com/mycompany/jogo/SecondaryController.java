package com.mycompany.jogo;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void selecionarMariana() throws Exception {
        GameController.personagemAtual = "MARIANA";
        GameController.fundoAtual = "fundoMariana.png";
        GameController.imagemBonecoAtual = "marianaBase.png";
        App.setRoot("game");
    }

    @FXML
    private void selecionarMayara() throws Exception {
        GameController.personagemAtual = "MAYARA";
        GameController.fundoAtual = "fundoMayara.png";
        GameController.imagemBonecoAtual = "mayaraBase.png";
        App.setRoot("game");
    }

    @FXML
    private void selecionarBeatriz() throws Exception {
        GameController.personagemAtual = "BEATRIZ";
        GameController.fundoAtual = "fundoBeatriz.png";
        GameController.imagemBonecoAtual = "beatrizBase.png";
        App.setRoot("game");
    }

    @FXML
    private void selecionarMarco() throws Exception {
        GameController.personagemAtual = "MARCO";
        GameController.fundoAtual = "fundoMarco.png";
        GameController.imagemBonecoAtual = "marcoBase.png";
        App.setRoot("game");
    }

    @FXML
    private void voltarParaMenu() throws Exception {
        App.setRoot("primary");
    }
}