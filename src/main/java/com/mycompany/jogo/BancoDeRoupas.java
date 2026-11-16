package com.mycompany.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDeRoupas {
    private static List<Roupa> todasAsRoupas = new ArrayList<>();

    static {
        // Cadastra o vestido 1
        todasAsRoupas.add(new Roupa(
            "v1", 
            "Vestido Floral", 
            "VESTIDO", 
            "/com/mycompany/jogo/icones/vestido1.png", 
            "/com/mycompany/jogo/vestidas/vestido1.png"
        ));
    }

    public static List<Roupa> getRoupasPorCategoria(String categoria) {
        return todasAsRoupas.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}