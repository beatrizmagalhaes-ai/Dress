package com.mycompany.jogo;

import com.mycompany.jogo.dao.JogadorDAO;
import com.mycompany.jogo.model.Jogador;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GameController {

    public static String personagemAtual = "MAYARA";
    public static String fundoAtual = "fundoMayara.png";
    public static String imagemBonecoAtual = "mayaraBase.png";

    @FXML private AnchorPane gamePane;
    @FXML private Label lblMoedas;

    // Camadas do personagem (da mais baixa para a mais alta)
    @FXML private ImageView imgPersonagem;
    @FXML private ImageView imgSapatoEquipado;
    @FXML private ImageView imgCalcaEquipada;
    @FXML private ImageView imgRoupaEquipada;      // Blusas e Vestidos
    @FXML private ImageView imgAcessorioEquipado;

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

    // Controle do que está vestido em cada camada
    private Roupa sapatoAtual = null;
    private Roupa calcaAtual = null;
    private Roupa roupaCimaAtual = null; // Blusa ou Vestido
    private Roupa acessorioAtual = null;

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

        // 2. Carrega a imagem inicial do boneco (com roupa inicial)
        carregarBoneco(imagemBonecoAtual);

        // 3. Garante a ordem das camadas e proporção
        configurarCamadas();
        configurarSlots();

        // 4. Atualiza dados do banco
        atualizarDadosJogador();

        // 5. Inicia na aba de Vestidos
        mostrarVestidos();
    }

    private void configurarCamadas() {
        ImageView[] camadas = {
            imgPersonagem,
            imgSapatoEquipado,
            imgCalcaEquipada,
            imgRoupaEquipada,
            imgAcessorioEquipado
        };

        for (ImageView camada : camadas) {
            if (camada != null) {
                camada.setPreserveRatio(true);
                camada.toFront();
            }
        }
    }

    private void configurarSlots() {
        if (gridItens != null) {
            gridItens.toFront();
        }

        ImageView[] slots = {imgSlot1, imgSlot2, imgSlot3, imgSlot4, imgSlot5, imgSlot6};
        for (ImageView slot : slots) {
            if (slot != null) {
                slot.setPreserveRatio(true);
                GridPane.setHalignment(slot, HPos.CENTER);
                GridPane.setValignment(slot, VPos.CENTER);
            }
        }
    }

    private void carregarBoneco(String nomeArquivo) {
        if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
            try {
                InputStream is = getClass().getResourceAsStream(nomeArquivo);
                if (is != null) {
                    Image boneco = new Image(is);
                    imgPersonagem.setImage(boneco);
                    imgPersonagem.setPreserveRatio(true);
                    imgPersonagem.setScaleX(1.0);
                    imgPersonagem.setScaleY(1.0);
                    imgPersonagem.setTranslateX(0.0);
                    imgPersonagem.setTranslateY(0.0);
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

    private void atualizarEstadoCorpoBase() {
        boolean temAlgumaRoupa = (sapatoAtual != null) ||
                                 (calcaAtual != null) ||
                                 (roupaCimaAtual != null) ||
                                 (acessorioAtual != null);

        if (temAlgumaRoupa) {
            carregarBoneco(getImagemBonecoLimpo());
        } else {
            carregarBoneco(imagemBonecoAtual);
        }
    }

    private String resolverCaminhoVestida(Roupa roupa) {
        String caminho = roupa.getCaminhoVestida();
        // Se for o Marco, redireciona "vestidas/..." para "vestidas/marco/..."
        if ("MARCO".equalsIgnoreCase(personagemAtual) && caminho.startsWith("vestidas/")) {
            return caminho.replaceFirst("vestidas/", "vestidas/marco/");
        }
        return caminho;
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

    @FXML private void mostrarVestidos() { carregarCategoria("VESTIDO"); }
    @FXML private void mostrarBlusas() { carregarCategoria("BLUSA"); }
    @FXML private void mostrarCalcas() { carregarCategoria("CALCA"); }
    @FXML private void mostrarAcessorios() { carregarCategoria("ACESSORIO"); }
    @FXML private void mostrarSapatos() { carregarCategoria("SAPATO"); }
    @FXML private void sairOuAbrirLoja() { carregarCategoria("CABELO"); }

    private void carregarCategoria(String categoria) {
        roupasAtuais = BancoDeRoupas.getRoupasPorCategoria(categoria);
        limparSlots();

        ImageView[] slots = {imgSlot1, imgSlot2, imgSlot3, imgSlot4, imgSlot5, imgSlot6};
        for (int i = 0; i < roupasAtuais.size() && i < 6; i++) {
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
        if (roupasAtuais == null || indice >= roupasAtuais.size()) return;

        Roupa selecionada = roupasAtuais.get(indice);
        String categoria = selecionada.getCategoria().toUpperCase();

        switch (categoria) {
            case "SAPATO":
                if (sapatoAtual != null && sapatoAtual.getId().equals(selecionada.getId())) {
                    sapatoAtual = null;
                    if (imgSapatoEquipado != null) imgSapatoEquipado.setImage(null);
                } else {
                    sapatoAtual = selecionada;
                    carregarImagem(imgSapatoEquipado, resolverCaminhoVestida(selecionada));
                }
                break;

            case "CALCA":
                if (calcaAtual != null && calcaAtual.getId().equals(selecionada.getId())) {
                    calcaAtual = null;
                    if (imgCalcaEquipada != null) imgCalcaEquipada.setImage(null);
                } else {
                    // Se estava com vestido inteiro, tira o vestido para colocar a calça
                    if (roupaCimaAtual != null && "VESTIDO".equalsIgnoreCase(roupaCimaAtual.getCategoria())) {
                        roupaCimaAtual = null;
                        if (imgRoupaEquipada != null) imgRoupaEquipada.setImage(null);
                    }
                    calcaAtual = selecionada;
                    carregarImagem(imgCalcaEquipada, resolverCaminhoVestida(selecionada));
                }
                break;

            case "BLUSA":
                if (roupaCimaAtual != null && roupaCimaAtual.getId().equals(selecionada.getId())) {
                    roupaCimaAtual = null;
                    if (imgRoupaEquipada != null) imgRoupaEquipada.setImage(null);
                } else {
                    roupaCimaAtual = selecionada;
                    carregarImagem(imgRoupaEquipada, resolverCaminhoVestida(selecionada));
                }
                break;

            case "VESTIDO":
                if (roupaCimaAtual != null && roupaCimaAtual.getId().equals(selecionada.getId())) {
                    roupaCimaAtual = null;
                    if (imgRoupaEquipada != null) imgRoupaEquipada.setImage(null);
                } else {
                    // Vestido substitui blusa e tira calça
                    calcaAtual = null;
                    if (imgCalcaEquipada != null) imgCalcaEquipada.setImage(null);

                    roupaCimaAtual = selecionada;
                    carregarImagem(imgRoupaEquipada, resolverCaminhoVestida(selecionada));
                }
                break;

            case "ACESSORIO":
                if (acessorioAtual != null && acessorioAtual.getId().equals(selecionada.getId())) {
                    acessorioAtual = null;
                    if (imgAcessorioEquipado != null) imgAcessorioEquipado.setImage(null);
                } else {
                    acessorioAtual = selecionada;
                    carregarImagem(imgAcessorioEquipado, resolverCaminhoVestida(selecionada));
                }
                break;
        }

        atualizarEstadoCorpoBase();
        configurarCamadas();
    }

    private void limparSlots() {
        ImageView[] slots = {imgSlot1, imgSlot2, imgSlot3, imgSlot4, imgSlot5, imgSlot6};
        for (ImageView slot : slots) {
            if (slot != null) slot.setImage(null);
        }
    }

    private void carregarImagem(ImageView container, String caminho) {
        if (container == null || caminho == null || caminho.isEmpty()) return;

        try {
            InputStream is = getClass().getResourceAsStream(caminho);
            if (is == null) {
                System.err.println("Aviso: Imagem ainda não adicionada: " + caminho);
            } else {
                container.setImage(new Image(is));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + caminho);
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