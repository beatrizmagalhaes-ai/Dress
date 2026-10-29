package com.mycompany.jogo;

public class PersonagemEscolhida {
    private String nome;
    private String caminhoFundo;
    private String caminhoBoneco;

    public PersonagemEscolhida(String nome, String caminhoFundo, String caminhoBoneco) {
        this.nome = nome;
        this.caminhoFundo = caminhoFundo;
        this.caminhoBoneco = caminhoBoneco;
    }

    public String getNome() { return nome; }
    public String getCaminhoFundo() { return caminhoFundo; }
    public String getCaminhoBoneco() { return caminhoBoneco; }
}