package br.projeto.lab.Controllers;

import br.projeto.lab.Modelos.*;
import java.util.List;

public class MatriculaController {
    private PeriodoMatricula periodoMatricula;

    public MatriculaController(PeriodoMatricula periodoMatricula) {
        this.periodoMatricula = periodoMatricula;
    }

    public void criarMatricula(Aluno aluno, Curso curso, int semestre, List<Disciplina> disciplinasSelecionadas) {
        // Verificar período de matrícula
        if (!periodoMatricula.periodoAtivo()) {
            throw new IllegalStateException("Período de matrícula não está ativo.");
        }

        // Verificar vagas nas disciplinas
        for (Disciplina disciplina : disciplinasSelecionadas) {
            if (!disciplina.vagasDisponiveis()) {
                throw new IllegalStateException("Disciplina " + disciplina.getNome() + " não possui vagas disponíveis.");
            }
            if (!curso.disciplinaPertenceAoSemestre(semestre, disciplina)) {
                throw new IllegalArgumentException("Disciplina " + disciplina.getNome() + " não pertence à grade do semestre.");
            }
        }

        aluno.matricular(new Matricula(aluno, disciplinasSelecionadas, semestre));
        
        for (Disciplina disciplina : disciplinasSelecionadas) {
            disciplina.adicionarAluno(aluno);
        }
    }

    public void cancelarMatricula(Aluno aluno) {
        if (!periodoMatricula.periodoAtivo()) {
            throw new IllegalStateException("Período de matrícula não está ativo.");
        }

        Matricula matricula = aluno.getMatricula();
        if (matricula != null) {
            for (Disciplina disciplina : matricula.getDisciplinas()) {
                disciplina.removerAluno(aluno);
            }
        }
        aluno.desmatricular();
    }

    public List<Matricula> listarMatriculasPorAluno(Aluno aluno) {
        return List.of(aluno.getMatricula());
    }
}