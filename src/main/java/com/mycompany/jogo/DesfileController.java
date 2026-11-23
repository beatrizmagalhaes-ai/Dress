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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DesfileController {

    public static String bonecoJogador;
    public static Roupa roupaJogador;

    @FXML private AnchorPane desfilePane;

    @FXML private StackPane containerAvatar1;
    @FXML private ImageView imgPersonagemDesfile1;
    @FXML private ImageView imgRoupaDesfile1;
    @FXML private Label lblNomeJogador;

    @FXML private StackPane containerAvatar2;
    @FXML private ImageView imgPersonagemDesfile2;
    @FXML private ImageView imgRoupaDesfile2;
    @FXML private Label lblNomeRival;

    @FXML private Label lblPlacarDuelo;
    @FXML private Label lblMensagemJurados;
    @FXML private Label lblPontuacao;
    @FXML private Label lblMoedasGanhas;

    private JogadorDAO jogadorDAO = new JogadorDAO();
    private String nomeRivalSorteado;
    private Roupa roupaRivalSorteada;

    @FXML
    public void initialize() {
        // 1. Carrega o fundo do desfile (salve a imagem gerada como passarela.png nos resources)
        try {
            String imagePath = getClass().getResource("passarela.png").toExternalForm();
            desfilePane.setStyle("-fx-background-image: url('" + imagePath + "'); " +
                                 "-fx-background-size: 100% 100%;");
        } catch (Exception e) {
            System.err.println("Aviso: passarela.png não encontrada.");
        }

        // 2. Configura o Jogador (Esquerda)
        lblNomeJogador.setText(GameController.personagemAtual);
        carregarImagem(imgPersonagemDesfile1, bonecoJogador);
        if (roupaJogador != null) {
            carregarImagem(imgRoupaDesfile1, roupaJogador.getCaminhoVestida());
        }

        // 3. Sorteia o Rival e a Roupa dele (Direita)
        prepararRival();

        // 4. Inicia a animação de entrada na passarela
        animarEntrada();
    }

    private void prepararRival() {
        String[] todosPersonagens = {"MARIANA", "MAYARA", "BEATRIZ", "MARCO"};
        List<String> candidatos = new ArrayList<>();

        for (String p : todosPersonagens) {
            if (!p.equalsIgnoreCase(GameController.personagemAtual)) {
                candidatos.add(p);
            }
        }

        // Sorteia um dos 3 personagens restantes
        nomeRivalSorteado = candidatos.get(new Random().nextInt(candidatos.size()));
        lblNomeRival.setText(nomeRivalSorteado);

        String arquivoBonecoRival = nomeRivalSorteado.toLowerCase() + "BaseLimpa.png";
        carregarImagem(imgPersonagemDesfile2, arquivoBonecoRival);

        // Sorteia uma roupa do BancoDeRoupas
        roupaRivalSorteada = BancoDeRoupas.getRoupaAleatoria();
        if (roupaRivalSorteada != null) {
            carregarImagem(imgRoupaDesfile2, roupaRivalSorteada.getCaminhoVestida());
        }
    }

    private void animarEntrada() {
        containerAvatar1.setTranslateX(-600);
        containerAvatar2.setTranslateX(600);

        TranslateTransition entrada1 = new TranslateTransition(Duration.seconds(2.2), containerAvatar1);
        entrada1.setFromX(-600);
        entrada1.setToX(0);

        TranslateTransition entrada2 = new TranslateTransition(Duration.seconds(2.2), containerAvatar2);
        entrada2.setFromX(600);
        entrada2.setToX(0);

        entrada1.setOnFinished(event -> apurarVencedor());

        entrada1.play();
        entrada2.play();
    }

    private void apurarVencedor() {
        Random random = new Random();

        // Calcula nota do jogador (pontos da roupa + sorteio dos jurados de 10 a 30)
        int baseJogador = (roupaJogador != null) ? roupaJogador.getPontos() : 10;
        int notaJogador = baseJogador + (random.nextInt(21) + 10);

        // Calcula nota do rival
        int baseRival = (roupaRivalSorteada != null) ? roupaRivalSorteada.getPontos() : 30;
        int notaRival = baseRival + (random.nextInt(21) + 10);

        lblPlacarDuelo.setText(GameController.personagemAtual + ": " + notaJogador + " pts  X  " + nomeRivalSorteado + ": " + notaRival + " pts");

        int pontosGanhos;
        int moedasGanhas;

        if (notaJogador >= notaRival) {
            lblMensagemJurados.setText("VITÓRIA! Você arrasou na passarela e venceu " + nomeRivalSorteado + "!");
            pontosGanhos = 50 + (notaJogador - notaRival);
            moedasGanhas = 100;
        } else {
            lblMensagemJurados.setText(nomeRivalSorteado + " venceu desta vez! Compre roupas melhores na loja e tente de novo!");
            pontosGanhos = 15;
            moedasGanhas = 30;
        }

        lblPontuacao.setText("Pontos de Estilo: +" + pontosGanhos);
        lblMoedasGanhas.setText("Moedas Ganhas: +" + moedasGanhas);

        // Salva no PostgreSQL
        Jogador jogador = jogadorDAO.buscarPorNome(GameController.personagemAtual);
        if (jogador != null) {
            jogadorDAO.atualizarRecompensaDesfile(
                jogador.getId(),
                jogador.getMoedas() + moedasGanhas,
                jogador.getPontosEstilo() + pontosGanhos
            );
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