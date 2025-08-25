package br.projeto.lab.Controllers;

import br.projeto.lab.Modelos.Disciplina;
import br.projeto.lab.Modelos.Professor;
import br.projeto.lab.Modelos.Aluno;
import br.projeto.lab.Modelos.Curso;

import java.util.List;

public class DisciplinaController {
    public void atribuirProfessor(Disciplina disciplina, Professor professor) {
        disciplina.setProfessor(professor);
    }

    public List<Aluno> listarAlunos(Disciplina disciplina) {
        return disciplina.getAlunosMatriculados();
    }

    public List<Disciplina> listarDisciplinasPorProfessor(Professor professor, Curso curso) {
        return curso.getGrades().stream()
                .flatMap(grade -> grade.getDisciplinasObrigatorias().stream())
                .filter(disciplina -> disciplina.getProfessor().equals(professor))
                .toList();
    }

    public List<Disciplina> listarDisciplinasPorAluno(Aluno aluno) {
        return aluno.getMatricula() != null ? aluno.getMatricula().getDisciplinas() : List.of();
    }
    
    public List<Aluno> listarAlunosPorDisciplina(Disciplina disciplina) {
        return disciplina.getAlunosMatriculados();
    }
    
    public void criarDisciplina(String nome, Professor professor, boolean obrigatoria, List<Disciplina> listaDisciplinas) {
        Disciplina nova = new Disciplina(nome, professor, obrigatoria);
        listaDisciplinas.add(nova);
    }

    public void removerDisciplina(Disciplina disciplina, List<Disciplina> listaDisciplinas) {
        listaDisciplinas.remove(disciplina);
    }
}