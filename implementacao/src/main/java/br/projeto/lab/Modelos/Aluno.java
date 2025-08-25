package br.projeto.lab.Modelos;

import java.util.List;
import java.util.ArrayList;

import br.projeto.lab.Enums.Permissao;

public class Aluno extends Usuario {
  private String curso;
  private Matricula matricula;
  private int semestreAtual;

  public Aluno(String identificador, String senha, String email, String nome, String curso) {
    super(identificador, senha, email, nome);
    this.curso = curso;
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
    if(matricula == null) {
      return true;
    }

    List<Disciplina> disciplinas = matricula.getDisciplinas();
    int obrigatorias = 0;
    int optativas = 0;

    for (Disciplina disciplina : disciplinas) {
      if (disciplina.isObrigatoria()) {
        obrigatorias++;
      } else {
        optativas++;
      }
    }

    return obrigatorias <= 4 && optativas <= 2;
  }

  public void matricular(Matricula matricula) {
    if (!temPermissao(Permissao.MATRICULAR_DISCIPLINA)) {
      throw new IllegalStateException("Aluno não tem permissão para se matricular.");
    }

    if (!podeMatricular()) {
      throw new IllegalArgumentException("Quantidade de disciplinas obrigatórias ou optativas excedida.");
    }

    if (matricula.getDisciplinas().size() > 6) {
      throw new IllegalArgumentException("Não é permitido se matricular em mais de 6 disciplinas.");
    }

    this.matricula = matricula;
  }

  public void desmatricular(){
    if(!temPermissao(Permissao.CANCELAR_MATRICULA)){
      throw new IllegalStateException("Aluno não tem permissão para cancelar matrícula.");
    }
    this.matricula = null;
  }

  // Getters e setters
  public String getCurso() {
    return curso;
  }

  public Matricula getMatricula() {
      return matricula;
  }

  public List<Disciplina> getDisciplinasMatriculadas() {
    if (matricula == null) {
      return new ArrayList<>();
    }
    return matricula.getDisciplinas();
  }

  public int getSemestreAtual() {
    return semestreAtual;
  }
}