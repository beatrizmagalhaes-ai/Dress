package com.mycompany.jogo;

import com.mycompany.jogo.dao.JogadorDAO;
import com.mycompany.jogo.model.Jogador;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class DesfileController {

    // Dados recebidos do GameController antes de trocar de tela
    public static String bonecoDesfile;
    public static String roupaDesfile;
    public static String categoriaRoupaDesfile;

    @FXML private AnchorPane desfilePane;
    @FXML private StackPane containerAvatar; // Agrupa corpo + roupa para andarem juntos
    @FXML private ImageView imgPersonagemDesfile;
    @FXML private ImageView imgRoupaDesfile;
    @FXML private Label lblPontuacao;
    @FXML private Label lblMoedasGanhas;
    @FXML private Label lblMensagemJurados;

    private JogadorDAO jogadorDAO = new JogadorDAO();

    @FXML
    public void initialize() {
        // 1. Carrega o fundo da passarela (coloque uma imagem passarela.png nos resources)
        try {
            String imagePath = getClass().getResource("passarela.png").toExternalForm();
            desfilePane.setStyle("-fx-background-image: url('" + imagePath + "'); " +
                                 "-fx-background-size: 100% 100%;");
        } catch (Exception e) {
            System.err.println("Fundo da passarela não encontrado, usando fundo padrão.");
        }

        // 2. Monta o avatar exatamente como estava no vestiário
        carregarImagem(imgPersonagemDesfile, bonecoDesfile);
        if (roupaDesfile != null) {
            carregarImagem(imgRoupaDesfile, roupaDesfile);
        }

        // 3. Inicia a animação de desfile e depois calcula os pontos
        animarPassarela();
    }

    private void animarPassarela() {
        // Começa fora da tela à esquerda (-500px) e vai até o centro (0)
        containerAvatar.setTranslateX(-500);

        TranslateTransition caminhada = new TranslateTransition(Duration.seconds(2.5), containerAvatar);
        caminhada.setFromX(-500);
        caminhada.setToX(0);

        // Quando terminar de andar, exibe a nota dos jurados
        caminhada.setOnFinished(event -> calcularESalvarResultado());
        caminhada.play();
    }

    private void calcularESalvarResultado() {
        int pontos = 0;
        int moedas = 0;
        String feedback = "";

        if (roupaDesfile == null) {
            // Desfilou com a roupa inicial ("de mendigo")
            pontos = 10;
            moedas = 15;
            feedback = "Ousado... mas faltou trocar de roupa! (+10 pts)";
        } else {
            // Cálculo base + bônus aleatório dos jurados (entre 10 e 50)
            int bonusJurados = new Random().nextInt(41) + 10;
            pontos = 50 + bonusJurados;
            moedas = pontos * 2; // Ex: 80 pontos = 160 moedas
            feedback = "Look incrível na passarela! Os jurados adoraram!";
        }

        lblPontuacao.setText("Pontos de Estilo: +" + pontos);
        lblMoedasGanhas.setText("Moedas Ganhas: +" + moedas);
        lblMensagemJurados.setText(feedback);

        // Atualiza no PostgreSQL
        Jogador jogador = jogadorDAO.buscarPorNome(GameController.personagemAtual);
        if (jogador != null) {
            int totalMoedas = jogador.getMoedas() + moedas;
            int totalPontos = jogador.getPontosEstilo() + pontos;
            jogadorDAO.atualizarRecompensaDesfile(jogador.getId(), totalMoedas, totalPontos);
        }
    }

    private void carregarImagem(ImageView view, String caminho) {
        if (view == null || caminho == null) return;
        InputStream is = getClass().getResourceAsStream(caminho);
        if (is != null) {
            view.setImage(new Image(is));
            view.setPreserveRatio(true);
        }
    }

    @FXML
    private void voltarParaVestiario() throws IOException {
        App.setRoot("game");
    }

    @FXML
    private void irParaRanking() throws IOException {
        App.setRoot("ranking");
    }
}