package com.mycompany.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDeRoupas {

    private static final List<Roupa> TODAS_AS_ROUPAS = new ArrayList<>();

    static {
        // ================= VESTIDOS (6) =================
        TODAS_AS_ROUPAS.add(new Roupa("v1", "Vestido 1", "VESTIDO", "icones/vestido1.png", "vestidas/vestido1.png"));
        TODAS_AS_ROUPAS.add(new Roupa("v2", "Vestido 2", "VESTIDO", "icones/vestido2.png", "vestidas/vestido2.png"));
        TODAS_AS_ROUPAS.add(new Roupa("v3", "Vestido 3", "VESTIDO", "icones/vestido3.png", "vestidas/vestido3.png"));
        TODAS_AS_ROUPAS.add(new Roupa("v4", "Vestido 4", "VESTIDO", "icones/vestido4.png", "vestidas/vestido4.png"));
        TODAS_AS_ROUPAS.add(new Roupa("v5", "Vestido 5", "VESTIDO", "icones/vestido5.png", "vestidas/vestido5.png"));
        TODAS_AS_ROUPAS.add(new Roupa("v6", "Vestido 6", "VESTIDO", "icones/vestido6.png", "vestidas/vestido6.png"));

        // ================= BLUSAS (6) =================
        TODAS_AS_ROUPAS.add(new Roupa("b1", "Blusa 1", "BLUSA", "icones/blusa1.png", "vestidas/blusa1.png"));
        TODAS_AS_ROUPAS.add(new Roupa("b2", "Blusa 2", "BLUSA", "icones/blusa2.png", "vestidas/blusa2.png"));
        TODAS_AS_ROUPAS.add(new Roupa("b3", "Blusa 3", "BLUSA", "icones/blusa3.png", "vestidas/blusa3.png"));
        TODAS_AS_ROUPAS.add(new Roupa("b4", "Blusa 4", "BLUSA", "icones/blusa4.png", "vestidas/blusa4.png"));
        TODAS_AS_ROUPAS.add(new Roupa("b5", "Blusa 5", "BLUSA", "icones/blusa5.png", "vestidas/blusa5.png"));
        TODAS_AS_ROUPAS.add(new Roupa("b6", "Blusa 6", "BLUSA", "icones/blusa6.png", "vestidas/blusa6.png"));

        // ================= CALÇAS (6) =================
        TODAS_AS_ROUPAS.add(new Roupa("c1", "Calça 1", "CALCA", "icones/calca1.png", "vestidas/calca1.png"));
        TODAS_AS_ROUPAS.add(new Roupa("c2", "Calça 2", "CALCA", "icones/calca2.png", "vestidas/calca2.png"));
        TODAS_AS_ROUPAS.add(new Roupa("c3", "Calça 3", "CALCA", "icones/calca3.png", "vestidas/calca3.png"));
        TODAS_AS_ROUPAS.add(new Roupa("c4", "Calça 4", "CALCA", "icones/calca4.png", "vestidas/calca4.png"));
        TODAS_AS_ROUPAS.add(new Roupa("c5", "Calça 5", "CALCA", "icones/calca5.png", "vestidas/calca5.png"));
        TODAS_AS_ROUPAS.add(new Roupa("c6", "Calça 6", "CALCA", "icones/calca6.png", "vestidas/calca6.png"));

        // ================= SAPATOS (6) =================
        TODAS_AS_ROUPAS.add(new Roupa("s1", "Sapato 1", "SAPATO", "icones/sapato1.png", "vestidas/sapato1.png"));
        TODAS_AS_ROUPAS.add(new Roupa("s2", "Sapato 2", "SAPATO", "icones/sapato2.png", "vestidas/sapato2.png"));
        TODAS_AS_ROUPAS.add(new Roupa("s3", "Sapato 3", "SAPATO", "icones/sapato3.png", "vestidas/sapato3.png"));
        TODAS_AS_ROUPAS.add(new Roupa("s4", "Sapato 4", "SAPATO", "icones/sapato4.png", "vestidas/sapato4.png"));
        TODAS_AS_ROUPAS.add(new Roupa("s5", "Sapato 5", "SAPATO", "icones/sapato5.png", "vestidas/sapato5.png"));
        TODAS_AS_ROUPAS.add(new Roupa("s6", "Sapato 6", "SAPATO", "icones/sapato6.png", "vestidas/sapato6.png"));

        // ================= ACESSÓRIOS (6) =================
        TODAS_AS_ROUPAS.add(new Roupa("a1", "Acessório 1", "ACESSORIO", "icones/acessorio1.png", "vestidas/acessorio1.png"));
        TODAS_AS_ROUPAS.add(new Roupa("a2", "Acessório 2", "ACESSORIO", "icones/acessorio2.png", "vestidas/acessorio2.png"));
        TODAS_AS_ROUPAS.add(new Roupa("a3", "Acessório 3", "ACESSORIO", "icones/acessorio3.png", "vestidas/acessorio3.png"));
        TODAS_AS_ROUPAS.add(new Roupa("a4", "Acessório 4", "ACESSORIO", "icones/acessorio4.png", "vestidas/acessorio4.png"));
        TODAS_AS_ROUPAS.add(new Roupa("a5", "Acessório 5", "ACESSORIO", "icones/acessorio5.png", "vestidas/acessorio5.png"));
        TODAS_AS_ROUPAS.add(new Roupa("a6", "Acessório 6", "ACESSORIO", "icones/acessorio6.png", "vestidas/acessorio6.png"));
    }

    public static List<Roupa> getRoupasPorCategoria(String categoria) {
        return TODAS_AS_ROUPAS.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}