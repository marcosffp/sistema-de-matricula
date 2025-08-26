package br.projeto.lab.Modelos;

import java.util.List;

import br.projeto.lab.Enums.Permissao;

import java.util.ArrayList;

public class Professor extends Funcionario {
  private List<String> disciplinasLecionadas;

  public Professor(String identificador, String senha, String email, String nome, String departamento) {
    super(identificador, senha, email, nome, departamento);
    this.disciplinasLecionadas = new ArrayList<>();
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case GERENCIAR_DISCIPLINAS, VISUALIZAR_MATRICULAS -> true;
      default -> false;
    };
  }

  public List<String> getDisciplinasLecionadas() {
    return new ArrayList<>(disciplinasLecionadas);
  }
}