package com.mycompany.jogo;

public class Roupa {
    private String id;
    private String nome;
    private String categoria;
    private String caminhoIcone;
    private String caminhoVestida;

    public Roupa(String id, String nome, String categoria, String caminhoIcone, String caminhoVestida) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.caminhoIcone = caminhoIcone;
        this.caminhoVestida = caminhoVestida;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getCaminhoIcone() { return caminhoIcone; }
    public String getCaminhoVestida() { return caminhoVestida; }
}