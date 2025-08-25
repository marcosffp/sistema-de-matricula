package br.projeto.lab.Modelos;

import java.util.List;

public class GradeCurricular {
    private String curso;
    private int semestre;
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;

    public GradeCurricular(String curso, int semestre, List<Disciplina> disciplinasObrigatorias, List<Disciplina> disciplinasOptativas) {
        this.curso = curso;
        this.semestre = semestre;
        this.disciplinasObrigatorias = disciplinasObrigatorias;
        this.disciplinasOptativas = disciplinasOptativas;
    }

    public String getCurso() {
        return curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public List<Disciplina> getDisciplinasObrigatorias() {
        return disciplinasObrigatorias;
    }

    public List<Disciplina> getDisciplinasOptativas() {
        return disciplinasOptativas;
    }
}