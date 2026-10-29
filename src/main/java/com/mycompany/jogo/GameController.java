package com.mycompany.jogo;

import com.mycompany.jogo.dao.JogadorDAO;
import com.mycompany.jogo.model.Jogador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class GameController {

    public static String personagemAtual = "MAYARA";
    public static String fundoAtual = "fundoMayara.png";
    public static String imagemBonecoAtual = "mayaraBase.png";

    @FXML private AnchorPane gamePane;
    @FXML private Label lblMoedas;
    @FXML private ImageView imgPersonagem;
    @FXML private GridPane gridItens;

    private JogadorDAO jogadorDAO = new JogadorDAO();
    private Jogador jogadorLogado;

    @FXML
    public void initialize() {
        // 1. Aplica o fundo do personagem
        if (fundoAtual != null && !fundoAtual.isEmpty()) {
            try {
                String imagePath = getClass().getResource(fundoAtual).toExternalForm();
                gamePane.setStyle("-fx-background-image: url('" + imagePath + "'); " +
                                  "-fx-background-size: 100% 100%; " +
                                  "-fx-background-position: center; " +
                                  "-fx-background-repeat: no-repeat;");
            } catch (Exception e) {
                System.err.println("Erro ao carregar fundo: " + fundoAtual);
            }
        }

        // 2. Carrega a imagem do boneco
        if (imagemBonecoAtual != null && !imagemBonecoAtual.isEmpty()) {
            try {
                Image boneco = new Image(getClass().getResourceAsStream(imagemBonecoAtual));
                imgPersonagem.setImage(boneco);
                imgPersonagem.setPreserveRatio(true);
            } catch (Exception e) {
                System.err.println("Erro ao carregar boneco: " + imagemBonecoAtual);
            }
        }

        // 3. Atualiza dados do banco
        atualizarDadosJogador();
    }

    private void atualizarDadosJogador() {
        jogadorLogado = jogadorDAO.buscarPorNome(personagemAtual);
        if (jogadorLogado != null) {
            lblMoedas.setText(String.valueOf(jogadorLogado.getMoedas()));
        } else {
            lblMoedas.setText("0");
        }
    }

    @FXML
    private void salvarLook() {
        if (jogadorLogado != null) {
            int novasMoedas = jogadorLogado.getMoedas() + 100;
            boolean atualizou = jogadorDAO.atualizarMoedas(jogadorLogado.getId(), novasMoedas);
            
            if (atualizou) {
                jogadorLogado.setMoedas(novasMoedas);
                lblMoedas.setText(String.valueOf(novasMoedas));
                System.out.println("Look salvo com sucesso!");
            }
        }
    }

    @FXML
    private void voltarParaSelecao() throws IOException {
        App.setRoot("secondary");
    }

    @FXML private void mostrarVestidos() {}
    @FXML private void mostrarBlusas() {}
    @FXML private void mostrarCalcas() {}
    @FXML private void mostrarAcessorios() {}
    @FXML private void mostrarSapatos() {}
    @FXML private void sairOuAbrirLoja() {}
}