package br.projeto.lab.Models;

import java.util.HashSet;
import java.util.Set;

import br.projeto.lab.Enums.Permissao;

public class Professor extends Usuario {
  private Set<Disciplina> disciplinasLecionadas;

  public Professor(String identificador, String senha, String email, String nome) {
    super(identificador, senha, email, nome);
    this.disciplinasLecionadas = new HashSet<>();
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case GERENCIAR_DISCIPLINAS, VISUALIZAR_MATRICULAS -> true;
      default -> false;
    };
  }

  public Set<Aluno> getAlunosPorDisciplina(Disciplina disciplina) {
    if (!disciplinasLecionadas.contains(disciplina)) {
      throw new IllegalArgumentException("Professor não ministra esta disciplina");
    }
    return disciplina.getAlunosMatriculados();
  }
}