package br.projeto.lab.Modelos;

import java.util.List;

import br.projeto.lab.Enums.Permissao;

import java.util.ArrayList;

public class Professor extends Usuario {
  private String departamento;
  private List<Disciplina> disciplinasLecionadas;

  public Professor(String identificador, String senha, String email, String nome, String departamento) {
    super(identificador, senha, email, nome);
    this.departamento = departamento;
    this.disciplinasLecionadas = new ArrayList<>();
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case GERENCIAR_DISCIPLINAS, VISUALIZAR_MATRICULAS -> true;
      default -> false;
    };
  }

  // Getters e setters
  public String getDepartamento() {
    return departamento;
  }

  public List<Aluno> getAlunosPorDisciplina(Disciplina disciplina) {
    if (!disciplinasLecionadas.contains(disciplina)) {
      throw new IllegalArgumentException("Professor não ministra esta disciplina");
    }
    return disciplina.getAlunosMatriculados();
  }
}