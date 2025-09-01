package br.projeto.lab;

import br.projeto.lab.Enums.Permissao;
import br.projeto.lab.Models.*;

import java.util.List;

public class SistemaMatriculaFacade {
    private Usuario usuario;

    public SistemaMatriculaFacade(Usuario usuario) {
        this.usuario = usuario;
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
            System.out.println(curso);
        }
    }

    public void exibirDisciplinas(List<Disciplina> disciplinas) {
        if (!podeListarDisciplinas()) {
            throw new SecurityException("Usuário sem permissão para listar disciplinas.");
        }
        System.out.println("=== Disciplinas ===");
        for (Disciplina disc : disciplinas) {
            System.out.println(disc);
        }
    }

    public void exibirUsuarios(List<Usuario> usuarios) {
        if (!podeListarUsuarios()) {
            throw new SecurityException("Usuário sem permissão para listar usuários.");
        }
        System.out.println("=== Usuários ===");
        for (Usuario user : usuarios) {
            System.out.println(user);
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
                    System.out.println("ID: " + aluno.getIdentificador() +" - Aluno: " + aluno.getNome() + " - Créditos: " + relacao.getCreditosObtidos());
                    break;
                }
            }
        }
    }
}