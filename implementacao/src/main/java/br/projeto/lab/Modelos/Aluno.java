package br.projeto.lab.Modelos;

import java.util.List;

import br.projeto.lab.Enums.Permissao;

public class Aluno extends Usuario {
  private String curso;
  private int semestreAtual;
  private Matricula matricula;

  public Aluno(String identificador, String senha, String email, String nome, String curso, int semestreAtual) {
    super(identificador, senha, email, nome);
    this.curso = curso;
    this.semestreAtual=semestreAtual;
  }

  @Override
  public boolean temPermissao(Permissao permissao) {
    return switch (permissao) {
      case MATRICULAR_DISCIPLINA, CANCELAR_MATRICULA, VISUALIZAR_MATRICULAS -> true;
      default -> false;
    };
  }

  public boolean podeMatricular() {
    return matricula.getDisciplinas().size() < 6;
  }
  
  public void fazerMatricula(List<Disciplina> disciplinas) {
    if (this.matricula != null && "Ativa".equals(this.matricula.getStatus())) {
      throw new IllegalStateException("Aluno já possui matrícula ativa");
    }

    this.matricula = new Matricula();
    this.matricula.setPeriodo(semestreAtual);
    this.matricula.setStatus("Ativa");
    disciplinas.forEach(this.matricula::adicionarDisciplina);
  }


  public String getCurso() {
    return curso;
  }

  public List<Disciplina> getDisciplinas() {
    return matricula.getDisciplinas();
  }

  public int getSemestreAtual() {
    return semestreAtual;
  }
}