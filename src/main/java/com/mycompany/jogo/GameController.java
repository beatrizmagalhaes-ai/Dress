package com.mycompany.jogo;

import com.mycompany.jogo.dao.JogadorDAO;
import com.mycompany.jogo.model.Jogador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;

public class GameController {

    public static String personagemAtual = "MAYARA";
    public static String fundoAtual = "fundoMayara.png";
    public static String imagemBonecoAtual = "mayaraBase.png";

    @FXML private AnchorPane gamePane;
    @FXML private Label lblMoedas;
    @FXML private ImageView imgPersonagem;
    @FXML private ImageView imgRoupaEquipada;
    @FXML private GridPane gridItens;

    // Ícones dos 6 slots do guarda-roupa
    @FXML private ImageView imgSlot1;
    @FXML private ImageView imgSlot2;
    @FXML private ImageView imgSlot3;
    @FXML private ImageView imgSlot4;
    @FXML private ImageView imgSlot5;
    @FXML private ImageView imgSlot6;

    private JogadorDAO jogadorDAO = new JogadorDAO();
    private Jogador jogadorLogado;
    private List<Roupa> roupasAtuais;
    private Roupa roupaAtualEquipada = null;

    @FXML
    public void initialize() {
        // 1. Aplica o fundo
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

        // 2. Carrega a imagem inicial do boneco (com roupa de mendigo)
        carregarBoneco(imagemBonecoAtual);

        if (imgRoupaEquipada != null) {
            imgRoupaEquipada.setPreserveRatio(true);
            imgRoupaEquipada.toFront();
        }

        // 3. Atualiza dados do banco
        atualizarDadosJogador();

        // 4. Força o carregamento da categoria VESTIDOS ao iniciar
        mostrarVestidos();
    }

    private void carregarBoneco(String nomeArquivo) {
        if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
            try {
                InputStream is = getClass().getResourceAsStream(nomeArquivo);
                if (is != null) {
                    Image boneco = new Image(is);
                    imgPersonagem.setImage(boneco);
                    imgPersonagem.setPreserveRatio(true);

                    // Ajusta o tamanho e a altura da imagem limpa para bater com a original
                    if (nomeArquivo.contains("Limpa")) {
                        double escala = 0.90;      // Diminui para 90% (altere para 0.88, 0.92, etc. se precisar)
                        double descerY = 18.0;     // Desce 18 pixels para os pés continuarem no pedestal
                        double moverX = 0.0;       // Ajuste horizontal se precisar

                        imgPersonagem.setScaleX(escala);
                        imgPersonagem.setScaleY(escala);
                        imgPersonagem.setTranslateY(descerY);
                        imgPersonagem.setTranslateX(moverX);
                    } else {
                        // Volta ao tamanho normal quando estiver com a roupa inicial
                        imgPersonagem.setScaleX(1.0);
                        imgPersonagem.setScaleY(1.0);
                        imgPersonagem.setTranslateY(0.0);
                        imgPersonagem.setTranslateX(0.0);
                    }
                } else {
                    System.err.println("Aviso: Imagem do boneco não encontrada: " + nomeArquivo);
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar boneco: " + nomeArquivo);
            }
        }
    }

    private String getImagemBonecoLimpo() {
        if (imagemBonecoAtual != null && imagemBonecoAtual.endsWith(".png")) {
            return imagemBonecoAtual.replace(".png", "Limpa.png");
        }
        return imagemBonecoAtual;
    }

    private void atualizarDadosJogador() {
        jogadorLogado = jogadorDAO.buscarPorNome(personagemAtual);
        if (jogadorLogado != null) {
            lblMoedas.setText(String.valueOf(jogadorLogado.getMoedas()));
        } else {
            lblMoedas.setText("0");
        }
    }

    // --- FILTROS DE CATEGORIA ---

    @FXML
    private void mostrarVestidos() {
        carregarCategoria("VESTIDO");
    }

    @FXML private void mostrarBlusas() { carregarCategoria("BLUSA"); }
    @FXML private void mostrarCalcas() { carregarCategoria("CALCA"); }
    @FXML private void mostrarAcessorios() { carregarCategoria("ACESSORIO"); }
    @FXML private void mostrarSapatos() { carregarCategoria("SAPATO"); }
    @FXML private void sairOuAbrirLoja() { carregarCategoria("CABELO"); }

    private void carregarCategoria(String categoria) {
        roupasAtuais = BancoDeRoupas.getRoupasPorCategoria(categoria);
        System.out.println("Categoria carregada: " + categoria + " | Quantidade de roupas encontradas: " + roupasAtuais.size());
        
        limparSlots();

        ImageView[] slots = {imgSlot1, imgSlot2, imgSlot3, imgSlot4, imgSlot5, imgSlot6};
        
        for (int i = 0; i < roupasAtuais.size() && i < 6; i++) {
            System.out.println("A carregar roupa " + i + " no slot: " + roupasAtuais.get(i).getCaminhoIcone());
            carregarImagem(slots[i], roupasAtuais.get(i).getCaminhoIcone());
        }
    }

    // --- CLIQUE NAS CAIXINHAS DO GUARDA-ROUPA ---

    @FXML private void equiparSlot1() { vestirRoupa(0); }
    @FXML private void equiparSlot2() { vestirRoupa(1); }
    @FXML private void equiparSlot3() { vestirRoupa(2); }
    @FXML private void equiparSlot4() { vestirRoupa(3); }
    @FXML private void equiparSlot5() { vestirRoupa(4); }
    @FXML private void equiparSlot6() { vestirRoupa(5); }

    private void vestirRoupa(int indice) {
        if (roupasAtuais != null && indice < roupasAtuais.size()) {
            Roupa roupaSelecionada = roupasAtuais.get(indice);

            // Se clicar na mesma roupa que já está vestida, tira a roupa e volta a roupa inicial
            if (roupaAtualEquipada != null && roupaAtualEquipada.getId().equals(roupaSelecionada.getId())) {
                imgRoupaEquipada.setImage(null);
                roupaAtualEquipada = null;
                carregarBoneco(imagemBonecoAtual);
                System.out.println("Roupa removida: " + roupaSelecionada.getNome());
                return;
            }

            // Troca o corpo base para a versão limpa (sem roupa de mendigo)
            carregarBoneco(getImagemBonecoLimpo());

            // Veste a nova roupa selecionada por cima
            carregarImagem(imgRoupaEquipada, roupaSelecionada.getCaminhoVestida());
            imgRoupaEquipada.toFront();
            roupaAtualEquipada = roupaSelecionada;
            System.out.println("Personagem vestido com: " + roupaSelecionada.getNome());
        }
    }

    private void limparSlots() {
        ImageView[] slots = {imgSlot1, imgSlot2, imgSlot3, imgSlot4, imgSlot5, imgSlot6};
        for (ImageView slot : slots) {
            if (slot != null) {
                slot.setImage(null);
            }
        }
    }

    private void carregarImagem(ImageView container, String caminho) {
        if (container == null) {
            System.err.println("ERRO: O ImageView está NULO! Verifique o fx:id no FXML.");
            return;
        }
        if (caminho == null || caminho.isEmpty()) return;

        try {
            InputStream is = getClass().getResourceAsStream(caminho);
            if (is == null) {
                System.err.println("ERRO: Ficheiro de imagem não encontrado no caminho: " + caminho);
            } else {
                Image img = new Image(is);
                container.setImage(img);
                System.out.println("Imagem carregada com sucesso em: " + caminho);
            }
        } catch (Exception e) {
            System.err.println("Exceção ao carregar imagem: " + caminho);
            e.printStackTrace();
        }
    }

    // --- SALVAR E VOLTAR ---

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
}