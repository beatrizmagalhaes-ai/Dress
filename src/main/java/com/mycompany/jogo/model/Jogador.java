package com.mycompany.jogo.model;

import java.sql.Timestamp;

public class Jogador {

    private int id;
    private String nome;
    private int moedas;
    private int pontosEstilo;
    private Timestamp dataCadastro;

    // Construtor Vazio
    public Jogador() {
    }

    // Construtor Completo
    public Jogador(int id, String nome, int moedas, int pontosEstilo, Timestamp dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.moedas = moedas;
        this.pontosEstilo = pontosEstilo;
        this.dataCadastro = dataCadastro;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public int getPontosEstilo() {
        return pontosEstilo;
    }

    public void setPontosEstilo(int pontosEstilo) {
        this.pontosEstilo = pontosEstilo;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}