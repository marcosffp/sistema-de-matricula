package br.projeto.lab.Controllers;

import br.projeto.lab.Modelos.Curso;
import br.projeto.lab.Modelos.GradeCurricular;
import br.projeto.lab.Modelos.Disciplina;
import java.util.List;

public class GradeCurricularController {
    public GradeCurricular getGradePorSemestre(Curso curso, int semestre) {
        return curso.getGradePorSemestre(semestre);
    }

    public List<Disciplina> listarDisciplinasObrigatorias(Curso curso, int semestre) {
        GradeCurricular grade = curso.getGradePorSemestre(semestre);
        return grade != null ? grade.getDisciplinasObrigatorias() : null;
    }

    public List<Disciplina> listarDisciplinasOptativas(Curso curso, int semestre) {
        GradeCurricular grade = curso.getGradePorSemestre(semestre);
        return grade != null ? grade.getDisciplinasOptativas() : null;
    }

    public void criarGradeCurricular(Curso curso, int semestre, List<Disciplina> obrigatorias, List<Disciplina> optativas) {
        GradeCurricular novaGrade = new GradeCurricular(curso.getNome(), semestre, obrigatorias, optativas);
        curso.getGrades().add(novaGrade);
    }

    public void removerGradeCurricular(Curso curso, int semestre) {
        GradeCurricular grade = curso.getGradePorSemestre(semestre);
        if (grade != null) curso.getGrades().remove(grade);
    }
}