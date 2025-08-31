package br.projeto.lab.Models;

import br.projeto.lab.Enums.Permissao;

public class Secretaria extends Usuario {

  public Secretaria(String identificador, String senha, String email, String nome) {
    super(identificador, senha, email, nome);
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case GERENCIAR_CURRICULO,
          GERENCIAR_DISCIPLINAS,
          GERENCIAR_PROFESSORES,
          GERENCIAR_ALUNOS,
          DEFINIR_PERIODO_MATRICULA ->
          true;
      default -> false;
    };
  }
}
