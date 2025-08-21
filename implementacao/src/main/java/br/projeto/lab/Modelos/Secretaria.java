package br.projeto.lab.Modelos;

public class Secretaria extends Usuario {

  public Secretaria(String identificador, String senha, String email, String nome) {
    super(identificador, senha, email, nome);
  }

  @Override
  public boolean temPermissao(String acao) {
    return switch (acao) {
      case "GERENCIAR_CURRICULO", "GERENCIAR_DISCIPLINAS", "GERENCIAR_PROFESSORES",
          "GERENCIAR_ALUNOS", "DEFINIR_PERIODO_MATRICULA" ->
        true;
      default -> false;
    };
  }
}