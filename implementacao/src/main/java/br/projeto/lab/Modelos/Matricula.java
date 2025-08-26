package br.projeto.lab.Modelos;

import java.util.List;
import java.util.ArrayList;

public class Matricula {
    private Aluno aluno;
    private List<Disciplina> disciplinas;
    private int semestre;

    public Matricula(Aluno aluno, List<Disciplina> disciplinas, int semestre) {
        this.aluno = aluno;
        this.disciplinas = new ArrayList<>(disciplinas);
        this.semestre = semestre;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(disciplinas);
    }

    public int getSemestre() {
        return semestre;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }
}