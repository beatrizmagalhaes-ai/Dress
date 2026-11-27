package com.mycompany.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BancoDeRoupas {
    private static List<Roupa> todasAsRoupas = new ArrayList<>();

    static {
        // ================= VESTIDOS =================
        todasAsRoupas.add(new Roupa("v1", "Vestido 1", "VESTIDO", "/com/mycompany/jogo/icones/vestido1.png", "/com/mycompany/jogo/vestidas/vestido1.png", 40));
        todasAsRoupas.add(new Roupa("v2", "Vestido 2", "VESTIDO", "/com/mycompany/jogo/icones/vestido2.png", "/com/mycompany/jogo/vestidas/vestido2.png", 50));
        todasAsRoupas.add(new Roupa("v3", "Vestido 3", "VESTIDO", "/com/mycompany/jogo/icones/vestido3.png", "/com/mycompany/jogo/vestidas/vestido3.png", 60));
        todasAsRoupas.add(new Roupa("v4", "Vestido 4", "VESTIDO", "/com/mycompany/jogo/icones/vestido4.png", "/com/mycompany/jogo/vestidas/vestido4.png", 70));
        todasAsRoupas.add(new Roupa("v5", "Vestido 5", "VESTIDO", "/com/mycompany/jogo/icones/vestido5.png", "/com/mycompany/jogo/vestidas/vestido5.png", 80));
        todasAsRoupas.add(new Roupa("v6", "Vestido 6", "VESTIDO", "/com/mycompany/jogo/icones/vestido6.png", "/com/mycompany/jogo/vestidas/vestido6.png", 95));

        // ================= BLUSAS =================
        todasAsRoupas.add(new Roupa("b1", "Blusa 1", "BLUSA", "/com/mycompany/jogo/icones/blusa1.png", "/com/mycompany/jogo/vestidas/blusa1.png", 30));
        todasAsRoupas.add(new Roupa("b2", "Blusa 2", "BLUSA", "/com/mycompany/jogo/icones/blusa2.png", "/com/mycompany/jogo/vestidas/blusa2.png", 40));
        todasAsRoupas.add(new Roupa("b3", "Blusa 3", "BLUSA", "/com/mycompany/jogo/icones/blusa3.png", "/com/mycompany/jogo/vestidas/blusa3.png", 50));
        todasAsRoupas.add(new Roupa("b4", "Blusa 4", "BLUSA", "/com/mycompany/jogo/icones/blusa4.png", "/com/mycompany/jogo/vestidas/blusa4.png", 60));
        todasAsRoupas.add(new Roupa("b5", "Blusa 5", "BLUSA", "/com/mycompany/jogo/icones/blusa5.png", "/com/mycompany/jogo/vestidas/blusa5.png", 70));
        todasAsRoupas.add(new Roupa("b6", "Blusa 6", "BLUSA", "/com/mycompany/jogo/icones/blusa6.png", "/com/mycompany/jogo/vestidas/blusa6.png", 85));

        // ================= CALÇAS =================
        todasAsRoupas.add(new Roupa("c1", "Calça 1", "CALCA", "/com/mycompany/jogo/icones/calca1.png", "/com/mycompany/jogo/vestidas/calca1.png", 35));
        todasAsRoupas.add(new Roupa("c2", "Calça 2", "CALCA", "/com/mycompany/jogo/icones/calca2.png", "/com/mycompany/jogo/vestidas/calca2.png", 45));
        todasAsRoupas.add(new Roupa("c3", "Calça 3", "CALCA", "/com/mycompany/jogo/icones/calca3.png", "/com/mycompany/jogo/vestidas/calca3.png", 55));
        todasAsRoupas.add(new Roupa("c4", "Calça 4", "CALCA", "/com/mycompany/jogo/icones/calca4.png", "/com/mycompany/jogo/vestidas/calca4.png", 65));
        todasAsRoupas.add(new Roupa("c5", "Calça 5", "CALCA", "/com/mycompany/jogo/icones/calca5.png", "/com/mycompany/jogo/vestidas/calca5.png", 75));
        todasAsRoupas.add(new Roupa("c6", "Calça 6", "CALCA", "/com/mycompany/jogo/icones/calca6.png", "/com/mycompany/jogo/vestidas/calca6.png", 90));

        // ================= ACESSÓRIOS =================
        todasAsRoupas.add(new Roupa("a1", "Acessório 1", "ACESSORIO", "/com/mycompany/jogo/icones/acessorio1.png", "/com/mycompany/jogo/vestidas/acessorio1.png", 25));
        todasAsRoupas.add(new Roupa("a2", "Acessório 2", "ACESSORIO", "/com/mycompany/jogo/icones/acessorio2.png", "/com/mycompany/jogo/vestidas/acessorio2.png", 35));
        todasAsRoupas.add(new Roupa("a3", "Acessório 3", "ACESSORIO", "/com/mycompany/jogo/icones/acessorio3.png", "/com/mycompany/jogo/vestidas/acessorio3.png", 45));
        todasAsRoupas.add(new Roupa("a4", "Acessório 4", "ACESSORIO", "/com/mycompany/jogo/icones/acessorio4.png", "/com/mycompany/jogo/vestidas/acessorio4.png", 55));
        todasAsRoupas.add(new Roupa("a5", "Acessório 5", "ACESSORIO", "/com/mycompany/jogo/icones/acessorio5.png", "/com/mycompany/jogo/vestidas/acessorio5.png", 65));
        todasAsRoupas.add(new Roupa("a6", "Acessório 6", "ACESSORIO", "/com/mycompany/jogo/icones/acessorio6.png", "/com/mycompany/jogo/vestidas/acessorio6.png", 75));

        // ================= SAPATOS =================
        todasAsRoupas.add(new Roupa("s1", "Sapato 1", "SAPATO", "/com/mycompany/jogo/icones/sapato1.png", "/com/mycompany/jogo/vestidas/sapato1.png", 30));
        todasAsRoupas.add(new Roupa("s2", "Sapato 2", "SAPATO", "/com/mycompany/jogo/icones/sapato2.png", "/com/mycompany/jogo/vestidas/sapato2.png", 40));
        todasAsRoupas.add(new Roupa("s3", "Sapato 3", "SAPATO", "/com/mycompany/jogo/icones/sapato3.png", "/com/mycompany/jogo/vestidas/sapato3.png", 50));
        todasAsRoupas.add(new Roupa("s4", "Sapato 4", "SAPATO", "/com/mycompany/jogo/icones/sapato4.png", "/com/mycompany/jogo/vestidas/sapato4.png", 60));
        todasAsRoupas.add(new Roupa("s5", "Sapato 5", "SAPATO", "/com/mycompany/jogo/icones/sapato5.png", "/com/mycompany/jogo/vestidas/sapato5.png", 70));
        todasAsRoupas.add(new Roupa("s6", "Sapato 6", "SAPATO", "/com/mycompany/jogo/icones/sapato6.png", "/com/mycompany/jogo/vestidas/sapato6.png", 80));
    }

    public static List<Roupa> getRoupasPorCategoria(String categoria) {
        return todasAsRoupas.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public static Roupa getRoupaAleatoria() {
        if (todasAsRoupas.isEmpty()) return null;
        return todasAsRoupas.get(new Random().nextInt(todasAsRoupas.size()));
    }
}