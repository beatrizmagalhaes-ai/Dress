package com.mycompany.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDeRoupas {
    private static List<Roupa> todasAsRoupas = new ArrayList<>();

    static {
        // Vestido 1
        todasAsRoupas.add(new Roupa(
            "v1", 
            "Vestido 1", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido1.png", 
            "/com/mycompany/jogo/vestidas/vestido1.png"
        ));

        // Vestido 2
        todasAsRoupas.add(new Roupa(
            "v2", 
            "Vestido 2", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido2.png", 
            "/com/mycompany/jogo/vestidas/vestido2.png"
        ));

        // Vestido 3
        todasAsRoupas.add(new Roupa(
            "v3", 
            "Vestido 3", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido3.png", 
            "/com/mycompany/jogo/vestidas/vestido3.png"
        ));

        // Vestido 4
        todasAsRoupas.add(new Roupa(
            "v4", 
            "Vestido 4", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido4.png", 
            "/com/mycompany/jogo/vestidas/vestido4.png"
        ));

        // Vestido 5
        todasAsRoupas.add(new Roupa(
            "v5", 
            "Vestido 5", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido5.png", 
            "/com/mycompany/jogo/vestidas/vestido5.png"
        ));

        // Vestido 6
        todasAsRoupas.add(new Roupa(
            "v6", 
            "Vestido 6", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido6.png", 
            "/com/mycompany/jogo/vestidas/vestido6.png"
        ));
    }

    public static List<Roupa> getRoupasPorCategoria(String categoria) {
        return todasAsRoupas.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}