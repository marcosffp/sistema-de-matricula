package br.projeto.lab.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
  private Long id;
  private String nome;
  private boolean optativa;
  private Professor professor;
  private List<Aluno> alunosMatriculados = new ArrayList<>();

  public Disciplina(Long id, String nome, boolean optativa) {
    this.id = id;
    this.nome = nome;
    this.optativa = optativa;
  }

  public void adicionarAluno(Aluno aluno) {
    if (!alunosMatriculados.contains(aluno)) {
      alunosMatriculados.add(aluno);
    }
  }

  public void removerAluno(Aluno aluno) {
    alunosMatriculados.remove(aluno);
  }


  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Professor getProfessor() {
    return professor;
  }

  public List<Aluno> getAlunosMatriculados() {
    return new ArrayList<>(alunosMatriculados);
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setProfessor(Professor professor) {
    this.professor = professor;
  }

  public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
    this.alunosMatriculados = alunosMatriculados;
  }

  public boolean isOptativa() {
    return optativa;
  }

  public void setOptativa(boolean optativa) {
    this.optativa = optativa;
  }
}