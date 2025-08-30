package br.projeto.lab.Models;

import java.util.HashSet;
import java.util.Set;

import br.projeto.lab.Enums.Permissao;

public class Aluno extends Usuario {
  private String nomeCurso;
  private Set<Disciplina> disciplinasMatriculadas;

  public Aluno(String codPessoa, String senha, String email, String nome, String curso) {
    super(codPessoa, senha, email, nome);
    this.nomeCurso = curso;
    this.disciplinasMatriculadas = new HashSet<>();
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case MATRICULAR_DISCIPLINA, CANCELAR_MATRICULA, VISUALIZAR_MATRICULAS -> true;
      default -> false;
    };
  }

  public String getNomeCurso() {
    return nomeCurso;
  }

  public void setNomeCurso(String nomeCurso) {
    this.nomeCurso = nomeCurso;
  }

  public void incluirDisciplina(Disciplina disciplina){
    if(disciplina == null)
      throw new IllegalArgumentException("Disciplina deve existir no sistema");
    this.disciplinasMatriculadas.add(disciplina);
  }

  public Set<Disciplina> getDisciplinasMatriculadas() {
    return disciplinasMatriculadas;
  }

  public void removerDisciplina(Disciplina disciplina){
    if(disciplina == null)
      throw new IllegalArgumentException("Disciplina deve existir no sistema");
    this.disciplinasMatriculadas.remove(disciplina);
  }
  
  @Override
  public String toString() {
    return super.toString() + " - Curso: " + getNomeCurso();
  }
}