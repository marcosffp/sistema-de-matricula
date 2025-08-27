package br.projeto.lab.Modelos;

import java.util.HashSet;
import java.util.Set;

public class Disciplina {
  private String nome;
  private int periodo;
  private boolean optativa;
  private Professor professor;
  private Set<Aluno> alunosMatriculados = new HashSet<>();

  public Disciplina(String nome, int periodo, boolean optativa) {
    this.nome = nome;
    this.periodo = periodo;
    this.optativa = optativa;
  }

  public void incluirAluno(Aluno aluno) {
    alunosMatriculados.add(aluno);
  }

  public void removerAluno(Aluno aluno) {
    alunosMatriculados.remove(aluno);
  }

  public int getPeriodo() {
    return periodo;
  }

  public String getNome() {
    return nome;
  }

  public Professor getProfessor() {
    return professor;
  }

  public Set<Aluno> getAlunosMatriculados() {
    return new HashSet<>(alunosMatriculados);
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setProfessor(Professor professor) {
    this.professor = professor;
  }

  public boolean isOptativa() {
    return optativa;
  }
}