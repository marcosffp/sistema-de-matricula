package br.projeto.lab.Models;

public class Disciplina {
  private String id;
  private String nome;
  private int periodo;
  private boolean optativa;

  public Disciplina(String nome, int periodo, boolean optativa) {
    this.nome = nome;
    this.periodo = periodo;
    this.optativa = optativa;
  }

  public Disciplina(String nome, int periodo, boolean optativa, String id) {
    this.nome = nome;
    this.periodo = periodo;
    this.optativa = optativa;
    this.id = id;
  }

  public int getPeriodo() {
    return periodo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public boolean isOptativa() {
    return optativa;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "ID: " + getId() + " - Nome: " + getNome() + " - Período: " + getPeriodo() +
        " - Optativa: " + (isOptativa() ? "Sim" : "Não");
  }
}