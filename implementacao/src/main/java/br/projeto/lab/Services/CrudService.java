package br.projeto.lab.Services;

import br.projeto.lab.SistemaMatriculaFacade;
import br.projeto.lab.Models.*;
import br.projeto.lab.Utils.ArquivoUtils;
import java.util.*;

public class CrudService {
    private SistemaMatriculaFacade facade;

    public CrudService(SistemaMatriculaFacade facade) {
        this.facade = facade;
    }

    public void criarCurso(Scanner scanner) throws Exception {
        System.out.print("Nome do curso: ");
        String nomeCurso = scanner.nextLine();
        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(scanner.nextLine());
        Curso curso = new Curso(nomeCurso, creditos);
        ArquivoUtils.salvarCurso(curso);
        System.out.println("Curso criado!");
    }

    public void criarDisciplina(Scanner scanner) throws Exception {
        System.out.print("Nome da disciplina: ");
        String nomeDisc = scanner.nextLine();
        System.out.print("Período: ");
        int periodo = Integer.parseInt(scanner.nextLine());
        System.out.print("Optativa (true/false): ");
        boolean optativa = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("ID do professor: ");
        String idProf = scanner.nextLine();
        System.out.print("ID do curso: ");
        String idCurso = scanner.nextLine();
        Disciplina disciplina = new Disciplina(nomeDisc, periodo, optativa);
        ArquivoUtils.salvarDisciplina(disciplina, idCurso, idProf);
        System.out.println("Disciplina criada!");
    }

    public void criarUsuario(Scanner scanner) throws Exception {
        System.out.print("Tipo (aluno/professor/secretaria): ");
        String tipo = scanner.nextLine();
        System.out.print("Identificador: ");
        String id = scanner.nextLine();
        System.out.print("Senha: ");
        String senhaNovo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        ArquivoUtils.salvarLogin(tipo, id, senhaNovo);
        
        if ("aluno".equals(tipo)) {
            System.out.print("Matrícula: ");
            String matricula = scanner.nextLine();
            System.out.print("Curso: ");
            String curso = scanner.nextLine();
            Aluno aluno = new Aluno(id, senhaNovo, nome, matricula, curso);
            ArquivoUtils.salvarAluno(aluno);
        } else if ("professor".equals(tipo)) {
            System.out.print("Departamento: ");
            String departamento = scanner.nextLine();
            Professor professor = new Professor(id, senhaNovo, nome, departamento);
            ArquivoUtils.salvarProfessor(professor);
        } else if ("secretaria".equals(tipo)) {
            System.out.print("Departamento: ");
            String departamento = scanner.nextLine();
            Secretaria secretaria = new Secretaria(id, senhaNovo, nome, departamento);
            ArquivoUtils.salvarSecretaria(secretaria);
        }
        System.out.println("Usuário criado!");
    }

    public void listarCursos() throws Exception {
        try {
            List<Curso> cursos = ArquivoUtils.listarCursos();
            facade.exibirCursos(cursos);
        } catch (SecurityException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarDisciplinas() throws Exception {
        try {
            List<Disciplina> disciplinas = ArquivoUtils.listarDisciplinas();
            facade.exibirDisciplinas(disciplinas);
        } catch (SecurityException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarUsuarios() throws Exception {
        try {
            List<Usuario> usuarios = ArquivoUtils.listarUsuarios();
            facade.exibirUsuarios(usuarios);
        } catch (SecurityException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void removerCurso(Scanner scanner) throws Exception {
        if (!facade.podeRemoverCurso()) {
            System.out.println("Erro: Usuário sem permissão para remover curso.");
            return;
        }
        System.out.print("ID do curso: ");
        String idCurso = scanner.nextLine();
        try {
            if (ArquivoUtils.cursoTemDisciplinas(idCurso)) {
                System.out.println("Erro: Não é possível remover: curso possui disciplinas vinculadas.");
                return;
            }
            ArquivoUtils.removerCurso(idCurso);
            System.out.println("Curso removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover curso: " + e.getMessage());
        }
    }

    public void removerDisciplina(Scanner scanner) throws Exception {
        if (!facade.podeRemoverDisciplina()) {
            System.out.println("Erro: Usuário sem permissão para remover disciplina.");
            return;
        }
        System.out.print("ID da disciplina: ");
        String idDisciplina = scanner.nextLine();
        try {
            if (ArquivoUtils.disciplinaTemAlunos(idDisciplina)) {
                System.out.println("Erro: Não é possível remover: disciplina possui alunos matriculados.");
                return;
            }
            ArquivoUtils.removerDisciplina(idDisciplina);
            System.out.println("Disciplina removida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover disciplina: " + e.getMessage());
        }
    }

    public void removerUsuario(Scanner scanner) throws Exception {
        if (!facade.podeRemoverUsuario()) {
            System.out.println("Erro: Usuário sem permissão para remover usuário.");
            return;
        }
        System.out.print("ID do usuário: ");
        String idUsuario = scanner.nextLine();
        try {
            if (ArquivoUtils.usuarioTemVinculos(idUsuario)) {
                System.out.println("Erro: Não é possível remover: usuário possui vínculos ativos.");
                return;
            }
            ArquivoUtils.removerUsuario(idUsuario);
            System.out.println("Usuário removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}