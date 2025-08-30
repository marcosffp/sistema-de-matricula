package br.projeto.lab;

import br.projeto.lab.Enums.Permissao;
import br.projeto.lab.Models.*;

import java.util.List;

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
            throw new SecurityException("Usuário sem permissão para matricular.");
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

    // Validação para operações de matrícula
    public boolean podeMatricularAluno(Aluno aluno, Disciplina disciplina) {
        return usuario.temPermissao(Permissao.MATRICULAR_DISCIPLINA) || 
               usuario.temPermissao(Permissao.GERENCIAR_ALUNOS);
    }

    public boolean podeCancelarMatricula(String idAluno, String idDisciplina) {
        return usuario.temPermissao(Permissao.CANCELAR_MATRICULA) || 
               usuario.temPermissao(Permissao.GERENCIAR_ALUNOS);
    }

    // Validações para listagem
    public boolean podeListarCursos() {
        return usuario.temPermissao(Permissao.GERENCIAR_DISCIPLINAS);
    }

    public boolean podeListarDisciplinas() {
        return usuario.temPermissao(Permissao.GERENCIAR_DISCIPLINAS);
    }

    public boolean podeListarUsuarios() {
        return usuario.temPermissao(Permissao.GERENCIAR_ALUNOS) || 
               usuario.temPermissao(Permissao.GERENCIAR_PROFESSORES);
    }

    // Validações para remoção
    public boolean podeRemoverCurso() {
        return usuario.temPermissao(Permissao.GERENCIAR_DISCIPLINAS);
    }

    public boolean podeRemoverDisciplina() {
        return usuario.temPermissao(Permissao.GERENCIAR_DISCIPLINAS);
    }

    public boolean podeRemoverUsuario() {
        return usuario.temPermissao(Permissao.GERENCIAR_ALUNOS) || 
               usuario.temPermissao(Permissao.GERENCIAR_PROFESSORES);
    }

    // Validação para visualizar matrículas (Professor)
    public boolean podeVisualizarMatriculas() {
        return usuario.temPermissao(Permissao.VISUALIZAR_MATRICULAS);
    }

    // Exibir informações de objetos em heap
    public void exibirCursos(List<Curso> cursos) {
        if (!podeListarCursos()) {
            throw new SecurityException("Usuário sem permissão para listar cursos.");
        }
        System.out.println("=== Cursos ===");
        for (Curso curso : cursos) {
            System.out.println("ID: " + curso.getId() + " - Nome: " + curso.getNome() + " - Créditos: " + curso.getCreditos());
        }
    }

    public void exibirDisciplinas(List<Disciplina> disciplinas) {
        if (!podeListarDisciplinas()) {
            throw new SecurityException("Usuário sem permissão para listar disciplinas.");
        }
        System.out.println("=== Disciplinas ===");
        for (Disciplina disc : disciplinas) {
            System.out.println("ID: " + disc.getId() + " - Nome: " + disc.getNome() + " - Período: " + disc.getPeriodo() + 
                             " - Optativa: " + (disc.isOptativa() ? "Sim" : "Não"));
        }
    }

    public void exibirUsuarios(List<Usuario> usuarios) {
        if (!podeListarUsuarios()) {
            throw new SecurityException("Usuário sem permissão para listar usuários.");
        }
        System.out.println("=== Usuários ===");
        for (Usuario user : usuarios) {
            System.out.println("ID: " + user.getIdentificador() + " - Nome: " + user.getNome() + 
                             " - Tipo: " + user.getClass().getSimpleName());
        }
    }

    public void exibirAlunosMatriculados(List<AlunoDisciplina> relacoes, List<Aluno> alunos) {
        if (!podeVisualizarMatriculas()) {
            throw new SecurityException("Usuário sem permissão para visualizar matrículas.");
        }
        System.out.println("=== Alunos Matriculados ===");
        for (AlunoDisciplina relacao : relacoes) {
            for (Aluno aluno : alunos) {
                if (aluno.getIdentificador().equals(relacao.getIdAluno())) {
                    System.out.println("Aluno: " + aluno.getNome() + " - Créditos: " + relacao.getCreditosObtidos());
                    break;
                }
            }
        }
    }
}