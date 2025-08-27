package br.projeto.lab;

import br.projeto.lab.Modelos.*;
import br.projeto.lab.Enums.Permissao;

public class SistemaMatriculaFacade {
    private Usuario usuario;

    public SistemaMatriculaFacade(Usuario usuario) {
        this.usuario = usuario;
    }

    // Gerenciar disciplinas do curso
    public void incluirDisciplinaNoCurso(Curso curso, Disciplina disciplina) {
        if (!usuario.temPermissao(Permissao.GERENCIAR_DISCIPLINAS)) {
            throw new SecurityException("Usuário sem permissão para incluir disciplina.");
        }
        curso.incluirDisciplina(disciplina);
    }

    public void removerDisciplinaDoCurso(Curso curso, Disciplina disciplina) {
        if (!usuario.temPermissao(Permissao.GERENCIAR_DISCIPLINAS)) {
            throw new SecurityException("Usuário sem permissão para remover disciplina.");
        }
        curso.removerDisciplina(disciplina);
    }

    // Gerenciar professores
    public void atribuirProfessorADisciplina(Disciplina disciplina, Professor professor) {
        if (!usuario.temPermissao(Permissao.GERENCIAR_PROFESSORES)) {
            throw new SecurityException("Usuário sem permissão para gerenciar professores.");
        }
        disciplina.setProfessor(professor);
    }

    // Gerenciar alunos
    public void incluirAlunoNaDisciplina(Disciplina disciplina, Aluno aluno) {
        if (!usuario.temPermissao(Permissao.GERENCIAR_ALUNOS)) {
            throw new SecurityException("Usuário sem permissão para gerenciar alunos.");
        }
        disciplina.incluirAluno(aluno);
        aluno.incluirDisciplina(disciplina);
    }

    public void removerAlunoDaDisciplina(Disciplina disciplina, Aluno aluno) {
        if (!usuario.temPermissao(Permissao.GERENCIAR_ALUNOS)) {
            throw new SecurityException("Usuário sem permissão para gerenciar alunos.");
        }
        disciplina.removerAluno(aluno);
        aluno.removerDisciplina(disciplina);
    }

    // Matrícula do aluno (caso o usuário seja aluno)
    public void matricularEmDisciplina(Aluno aluno, Disciplina disciplina) {
        if (!usuario.temPermissao(Permissao.MATRICULAR_DISCIPLINA)) {
            throw new SecurityException("Usuário sem permissão para se matricular.");
        }
        aluno.incluirDisciplina(disciplina);
        disciplina.incluirAluno(aluno);
    }

    public void cancelarMatriculaEmDisciplina(Aluno aluno, Disciplina disciplina) {
        if (!usuario.temPermissao(Permissao.CANCELAR_MATRICULA)) {
            throw new SecurityException("Usuário sem permissão para cancelar matrícula.");
        }
        aluno.removerDisciplina(disciplina);
        disciplina.removerAluno(aluno);
    }

    // Visualizar alunos de uma disciplina (professor)
    public void listarAlunosMatriculados(Disciplina disciplina) {
        if (!usuario.temPermissao(Permissao.VISUALIZAR_MATRICULAS)) {
            throw new SecurityException("Usuário sem permissão para visualizar matrículas.");
        }
        for (Aluno aluno : disciplina.getAlunosMatriculados()) {
            System.out.println(aluno.getNome());
        }
    }
}