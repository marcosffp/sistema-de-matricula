package br.projeto.lab.Modelos;

import java.util.List;
import java.util.ArrayList;

public class Aluno extends Usuario {
  private String curso;
  private List<String> disciplinasMatriculadas;
  private int semestreAtual;

  public Aluno(String identificador, String senha, String email, String nome, String curso) {
    super(identificador, senha, email, nome);
    this.curso = curso;
    this.disciplinasMatriculadas = new ArrayList<>();
    this.semestreAtual = 1;
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case MATRICULAR_DISCIPLINA, CANCELAR_MATRICULA, VISUALIZAR_MATRICULAS -> true;
      default -> false;
    };
  }

  // Métodos específicos do aluno
  public boolean podeMatricular() {
    return disciplinasMatriculadas.size() < 6; // 4 obrigatórias + 2 optativas
  }

  // Getters e setters
  public String getCurso() {
    return curso;
  }

  public List<String> getDisciplinasMatriculadas() {
    return new ArrayList<>(disciplinasMatriculadas);
  }

  public int getSemestreAtual() {
    return semestreAtual;
  }
}