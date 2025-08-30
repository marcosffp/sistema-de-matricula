package br.projeto.lab.Utils;

import br.projeto.lab.Models.*;
import br.projeto.lab.Repositories.*;
import br.projeto.lab.Services.ValidationService;
import java.io.IOException;
import java.util.List;

public class ArquivoUtils {

    // Delegação para repositórios - Usuários
    public static void salvarAluno(Aluno aluno) throws IOException {
        UsuarioRepository.salvarAluno(aluno);
    }

    public static void salvarProfessor(Professor professor) throws IOException {
        UsuarioRepository.salvarProfessor(professor);
    }

    public static void salvarSecretaria(Secretaria secretaria) throws IOException {
        UsuarioRepository.salvarSecretaria(secretaria);
    }

    public static void salvarLogin(String tipo, String id, String senha) throws IOException {
        LoginRepository.salvarLogin(tipo, id, senha);
    }

    public static Aluno buscarAlunoPorId(String id) throws IOException {
        return UsuarioRepository.buscarAlunoPorId(id);
    }

    public static Professor buscarProfessorPorId(String id) throws IOException {
        return UsuarioRepository.buscarProfessorPorId(id);
    }

    public static Secretaria buscarSecretariaPorId(String id) throws IOException {
        return UsuarioRepository.buscarSecretariaPorId(id);
    }

    public static List<Usuario> listarUsuarios() throws IOException {
        return UsuarioRepository.listarUsuarios();
    }

    public static void removerUsuario(String idUsuario) throws IOException {
        UsuarioRepository.removerUsuario(idUsuario);
    }

    // Delegação para repositórios - Cursos
    public static void salvarCurso(Curso curso) throws IOException {
        CursoRepository.salvarCurso(curso);
    }

    public static Curso buscarCursoPorId(String idCurso) throws IOException {
        return CursoRepository.buscarCursoPorId(idCurso);
    }

    public static List<Curso> listarCursos() throws IOException {
        return CursoRepository.listarCursos();
    }

    public static void removerCurso(String idCurso) throws IOException {
        CursoRepository.removerCurso(idCurso);
    }

    // Delegação para repositórios - Disciplinas
    public static void salvarDisciplina(Disciplina disciplina, String idCurso, String idProf) throws IOException {
        DisciplinaRepository.salvarDisciplina(disciplina, idCurso, idProf);
    }

    public static Disciplina buscarDisciplinaPorId(String idDisc) throws IOException {
        return DisciplinaRepository.buscarDisciplinaPorId(idDisc);
    }

    public static List<Disciplina> listarDisciplinas() throws IOException {
        return DisciplinaRepository.listarDisciplinas();
    }

    public static void removerDisciplina(String idDisciplina) throws IOException {
        DisciplinaRepository.removerDisciplina(idDisciplina);
    }

    // Delegação para repositórios - Matrículas
    public static void salvarAlunoDisciplina(AlunoDisciplina relacao) throws IOException {
        AlunoDisciplinaRepository.salvarAlunoDisciplina(relacao);
    }

    public static void removerAlunoDisciplina(String idAluno, String idDisciplina) throws IOException {
        AlunoDisciplinaRepository.removerAlunoDisciplina(idAluno, idDisciplina);
    }

    public static List<AlunoDisciplina> listarAlunosMatriculadosNaDisciplina(String idDisciplina) throws IOException {
        return AlunoDisciplinaRepository.listarAlunosMatriculadosNaDisciplina(idDisciplina);
    }

    // Delegação para validações
    public static boolean cursoTemDisciplinas(String idCurso) throws IOException {
        return ValidationService.cursoTemDisciplinas(idCurso);
    }

    public static boolean disciplinaTemAlunos(String idDisciplina) throws IOException {
        return ValidationService.disciplinaTemAlunos(idDisciplina);
    }

    public static boolean usuarioTemVinculos(String idUsuario) throws IOException {
        return ValidationService.usuarioTemVinculos(idUsuario);
    }

    // Autenticação (única responsabilidade restante)
    public static Usuario autenticarUsuario(String id, String senha) throws Exception {
        String[] partes = LoginRepository.buscarLogin(id, senha);
        if (partes != null) {
            String tipo = partes[0];
            if ("aluno".equals(tipo)) {
                return buscarAlunoPorId(id);
            } else if ("professor".equals(tipo)) {
                return buscarProfessorPorId(id);
            } else if ("secretaria".equals(tipo)) {
                return buscarSecretariaPorId(id);
            }
        }
        return null;
    }
}