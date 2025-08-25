package br.projeto.lab.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private static final int MIN_ALUNOS = 3;
    private static final int MAX_ALUNOS = 60;
    
    private String nome;
    private Professor professor;
    private boolean obrigatoria;
    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, Professor professor, boolean obrigatoria) {
        this.nome = nome;
        this.professor = professor;
        this.obrigatoria = obrigatoria;
        this.alunosMatriculados = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        if (alunosMatriculados.size() >= MAX_ALUNOS) {
            throw new IllegalStateException("Disciplina já atingiu o limite máximo de alunos (60).");
        }
        if (!alunosMatriculados.contains(aluno)) {
            alunosMatriculados.add(aluno);
        }
    }

    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    public boolean podeSerOfertada() {
        return alunosMatriculados.size() >= MIN_ALUNOS;
    }

    public boolean vagasDisponiveis() {
        return alunosMatriculados.size() < MAX_ALUNOS;
    }

    public String getNome() {
        return nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public List<Aluno> getAlunosMatriculados() {
        return new ArrayList<>(alunosMatriculados);
    }

    public int getQuantidadeAlunos() {
        return alunosMatriculados.size();
    }
}