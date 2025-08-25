package br.projeto.lab.Modelos;

import java.util.List;

public class Curso {
    private String nome;
    private int creditos;
    private List<GradeCurricular> grades;

    public Curso(String nome, int creditos, List<GradeCurricular> grades) {
        this.nome = nome;
        this.creditos = creditos;
        this.grades = grades;
    }

    public GradeCurricular getGradePorSemestre(int semestre) {
        for (GradeCurricular grade : grades) {
            if (grade.getSemestre() == semestre) {
                return grade;
            }
        }
        return null;
    }

    public boolean disciplinaPertenceAoSemestre(int semestre, Disciplina disciplina) {
        GradeCurricular grade = getGradePorSemestre(semestre);
        if (grade == null) return false;
        return grade.getDisciplinasObrigatorias().contains(disciplina) ||
               grade.getDisciplinasOptativas().contains(disciplina);
    }

    public String getNome() {
        return nome;
    }

    public List<GradeCurricular> getGrades() {
        return grades;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}