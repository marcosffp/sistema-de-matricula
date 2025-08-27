package br.projeto.lab;

import br.projeto.lab.Modelos.*;
import br.projeto.lab.Utils.ArquivoUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sistema de Matrícula ===");
        System.out.print("Identificador: ");
        String identificador = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario usuario = autenticarUsuario(identificador, senha);
        if (usuario == null) {
            System.out.println("Login inválido!");
            return;
        }
        System.out.println("Bem-vindo, " + usuario.getNome() + " (" + usuario.getClass().getSimpleName() + ")");

        SistemaMatriculaFacade facade = new SistemaMatriculaFacade(usuario);

        if (usuario instanceof Secretaria) {
            menuSecretaria(sc, facade);
        } else if (usuario instanceof Aluno) {
            menuAluno(sc, facade, (Aluno) usuario);
        } else if (usuario instanceof Professor) {
            menuProfessor(sc, facade);
        }
    }

    private static Usuario autenticarUsuario(String identificador, String senha) throws Exception {
        return ArquivoUtils.autenticarUsuario(identificador, senha);
    }

    private static void menuSecretaria(Scanner sc, SistemaMatriculaFacade facade) throws Exception {
        while (true) {
            System.out.println("\nMenu Secretaria:");
            System.out.println("1. Criar curso");
            System.out.println("2. Criar disciplina");
            System.out.println("3. Criar usuário");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");
            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    criarCurso(sc);
                    break;
                case 2:
                    criarDisciplina(sc);
                    break;
                case 3:
                    criarUsuario(sc);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuAluno(Scanner sc, SistemaMatriculaFacade facade, Aluno aluno) throws Exception {
        while (true) {
            System.out.println("\nMenu Aluno:");
            System.out.println("1. Matricular em disciplina");
            System.out.println("2. Cancelar matrícula");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    matricularDisciplina(sc, facade, aluno);
                    break;
                case 2:
                    cancelarMatricula(sc, facade, aluno);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuProfessor(Scanner sc, SistemaMatriculaFacade facade) throws Exception {
        while (true) {
            System.out.println("\nMenu Professor:");
            System.out.println("1. Listar alunos de uma disciplina");
            System.out.println("2. Sair");
            System.out.print("Escolha: ");
            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    listarAlunosDisciplina(sc, facade);
                    break;
                case 2:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Operações usando ArquivoUtils
    private static void criarCurso(Scanner sc) throws Exception {
        System.out.print("Nome do curso: ");
        String nomeCurso = sc.nextLine();
        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(sc.nextLine());
        Curso curso = new Curso(nomeCurso, creditos);
        ArquivoUtils.salvarCurso(curso);
        System.out.println("Curso criado!");
    }

    private static void criarDisciplina(Scanner sc) throws Exception {
        System.out.print("Nome da disciplina: ");
        String nomeDisc = sc.nextLine();
        System.out.print("Período: ");
        int periodo = Integer.parseInt(sc.nextLine());
        System.out.print("Optativa (true/false): ");
        boolean optativa = Boolean.parseBoolean(sc.nextLine());
        System.out.print("ID do professor: ");
        String idProf = sc.nextLine();
        System.out.print("ID do curso: ");
        String idCurso = sc.nextLine();
        Disciplina disciplina = new Disciplina(nomeDisc, periodo, optativa);
        ArquivoUtils.salvarDisciplina(disciplina, idCurso, idProf);
        System.out.println("Disciplina criada!");
    }

    private static void criarUsuario(Scanner sc) throws Exception {
        System.out.print("Tipo (aluno/professor/secretaria): ");
        String tipo = sc.nextLine();
        System.out.print("Identificador: ");
        String id = sc.nextLine();
        System.out.print("Senha: ");
        String senhaNovo = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        ArquivoUtils.salvarLogin(tipo, id, senhaNovo);
        if ("aluno".equals(tipo)) {
            System.out.print("Curso: ");
            String curso = sc.nextLine();
            Aluno aluno = new Aluno(id, "", email, nome, curso);
            ArquivoUtils.salvarAluno(aluno);
        } else if ("professor".equals(tipo)) {
            Professor professor = new Professor(id, "", email, nome);
            ArquivoUtils.salvarProfessor(professor);
        } else if ("secretaria".equals(tipo)) {
            Secretaria secretaria = new Secretaria(id, "", email, nome);
            ArquivoUtils.salvarSecretaria(secretaria);
        }
        System.out.println("Usuário criado!");
    }

    private static void matricularDisciplina(Scanner sc, SistemaMatriculaFacade facade, Aluno aluno) throws Exception {
        System.out.print("ID da disciplina: ");
        String idDisc = sc.nextLine();
        Disciplina disciplina = ArquivoUtils.buscarDisciplinaPorId(idDisc);
        facade.matricularEmDisciplina(aluno, disciplina);
        System.out.println("Matriculado!");
    }

    private static void cancelarMatricula(Scanner sc, SistemaMatriculaFacade facade, Aluno aluno) throws Exception {
        System.out.print("ID da disciplina: ");
        String idDiscCancel = sc.nextLine();
        Disciplina disciplinaCancel = ArquivoUtils.buscarDisciplinaPorId(idDiscCancel);
        facade.cancelarMatriculaEmDisciplina(aluno, disciplinaCancel);
        System.out.println("Matrícula cancelada!");
    }

    private static void listarAlunosDisciplina(Scanner sc, SistemaMatriculaFacade facade) throws Exception {
        System.out.print("ID da disciplina: ");
        String idDisc = sc.nextLine();
        Disciplina disciplina = ArquivoUtils.buscarDisciplinaPorId(idDisc);
        facade.listarAlunosMatriculados(disciplina);
    }
}