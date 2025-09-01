package br.projeto.lab.Models;

public class Curso {
    private String id;
    private String nome;
    private int creditos;

    public Curso(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
    }

    public Curso(String nome, int creditos, String id) {
        this.nome = nome;
        this.creditos = creditos;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " - Nome: " + getNome() + " - Créditos: " + getCreditos();
    }
}
