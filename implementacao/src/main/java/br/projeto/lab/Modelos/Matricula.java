package br.projeto.lab.Modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private Long id;
    private LocalDate data;
    private String status;
    private int periodo;
    private Aluno aluno;
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void adicionarDisciplina(Disciplina disciplina) {
      if (!disciplinas.contains(disciplina)) {
        disciplinas.add(disciplina);
        disciplina.adicionarAluno(this.aluno);
      }
    }

    public void removerDisciplina(Disciplina disciplina) {
      disciplinas.remove(disciplina);
      disciplina.removerAluno(this.aluno);
    }

    public List<Disciplina> getDisciplinas() {
      return new ArrayList<>(disciplinas);
    }

    public Long getId() {
      return id;
    }

    public LocalDate getData() {
      return data;
    }

    public void setData(LocalDate data) {
      this.data = data;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public int getPeriodo() {
      return periodo;
    }

    public void setPeriodo(int periodo) {
      this.periodo = periodo;
    }

    public Aluno getAluno() {
      return aluno;
    }

    public void setAluno(Aluno aluno) {
      this.aluno = aluno;
    }    
}
