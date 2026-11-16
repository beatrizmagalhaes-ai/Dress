package com.mycompany.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDeRoupas {
    private static List<Roupa> todasAsRoupas = new ArrayList<>();

    static {
        // ================= VESTIDOS =================
        todasAsRoupas.add(new Roupa(
            "v1", 
            "Vestido 1", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido1.png", 
            "/com/mycompany/jogo/vestidas/vestido1.png"
        ));

        todasAsRoupas.add(new Roupa(
            "v2", 
            "Vestido 2", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido2.png", 
            "/com/mycompany/jogo/vestidas/vestido2.png"
        ));

        todasAsRoupas.add(new Roupa(
            "v3", 
            "Vestido 3", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido3.png", 
            "/com/mycompany/jogo/vestidas/vestido3.png"
        ));

        todasAsRoupas.add(new Roupa(
            "v4", 
            "Vestido 4", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido4.png", 
            "/com/mycompany/jogo/vestidas/vestido4.png"
        ));

        todasAsRoupas.add(new Roupa(
            "v5", 
            "Vestido 5", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido5.png", 
            "/com/mycompany/jogo/vestidas/vestido5.png"
        ));

        todasAsRoupas.add(new Roupa(
            "v6", 
            "Vestido 6", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido6.png", 
            "/com/mycompany/jogo/vestidas/vestido6.png"
        ));

        // ================= BLUSAS =================
        todasAsRoupas.add(new Roupa(
            "b1", 
            "Blusa 1", 
            "BLUSA", 
            "/com/mycompany/jogo/icones/blusa1.png", 
            "/com/mycompany/jogo/vestidas/blusa1.png"
        ));

        todasAsRoupas.add(new Roupa(
            "b2", 
            "Blusa 2", 
            "BLUSA", 
            "/com/mycompany/jogo/icones/blusa2.png", 
            "/com/mycompany/jogo/vestidas/blusa2.png"
        ));

        todasAsRoupas.add(new Roupa(
            "b3", 
            "Blusa 3", 
            "BLUSA", 
            "/com/mycompany/jogo/icones/blusa3.png", 
            "/com/mycompany/jogo/vestidas/blusa3.png"
        ));

        todasAsRoupas.add(new Roupa(
            "b4", 
            "Blusa 4", 
            "BLUSA", 
            "/com/mycompany/jogo/icones/blusa4.png", 
            "/com/mycompany/jogo/vestidas/blusa4.png"
        ));

        todasAsRoupas.add(new Roupa(
            "b5", 
            "Blusa 5", 
            "BLUSA", 
            "/com/mycompany/jogo/icones/blusa5.png", 
            "/com/mycompany/jogo/vestidas/blusa5.png"
        ));

        todasAsRoupas.add(new Roupa(
            "b6", 
            "Blusa 6", 
            "BLUSA", 
            "/com/mycompany/jogo/icones/blusa6.png", 
            "/com/mycompany/jogo/vestidas/blusa6.png"
        ));
    }

    public static List<Roupa> getRoupasPorCategoria(String categoria) {
        return todasAsRoupas.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}