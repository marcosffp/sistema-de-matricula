package br.projeto.lab.Models;

import br.projeto.lab.Enums.Permissao;

public class Professor extends Usuario {

  public Professor(String identificador, String senha, String email, String nome) {
    super(identificador, senha, email, nome);
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case GERENCIAR_DISCIPLINAS, VISUALIZAR_MATRICULAS -> true;
      default -> false;
    };
  }
}