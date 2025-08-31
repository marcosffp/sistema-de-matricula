package br.projeto.lab.Models;

import br.projeto.lab.Enums.Permissao;

public class Aluno extends Usuario {
  private String nomeCurso;

  public Aluno(String codPessoa, String senha, String email, String nome, String curso) {
    super(codPessoa, senha, email, nome);
    this.nomeCurso = curso;
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
  
  @Override
  public String toString() {
    return super.toString() + " - Curso: " + getNomeCurso();
  }
}