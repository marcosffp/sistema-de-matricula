package br.projeto.lab.Handlers;

import br.projeto.lab.SistemaMatriculaFacade;
import br.projeto.lab.Models.*;
import br.projeto.lab.Utils.ArquivoUtils;
import java.util.*;

public class ProfessorHandler {
    private Scanner scanner;
    private SistemaMatriculaFacade facade;

    public ProfessorHandler(Scanner scanner, SistemaMatriculaFacade facade) {
        this.scanner = scanner;
        this.facade = facade;
    }

    public void executarMenu(Professor professor) throws Exception {
        while (true) {
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());
            
            switch (opcao) {
                case 1: listarAlunosDisciplina(); break;
                case 2: listarMinhasDisciplinas(professor); break;
                case 3: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\nMenu Professor:");
        System.out.println("1. Listar alunos de uma disciplina");
        System.out.println("2. Listar minhas disciplinas");
        System.out.println("3. Sair");
        System.out.print("Escolha: ");
    }

    private void listarAlunosDisciplina() throws Exception {
        System.out.print("ID da disciplina: ");
        String idDisc = scanner.nextLine();
        
        try {
            List<AlunoDisciplina> relacoes = ArquivoUtils.listarAlunosMatriculadosNaDisciplina(idDisc);
            List<Usuario> usuarios = ArquivoUtils.listarUsuarios();
            List<Aluno> alunos = new ArrayList<>();
            
            for (Usuario user : usuarios) {
                if (user instanceof Aluno) {
                    alunos.add((Aluno) user);
                }
            }
            
            facade.exibirAlunosMatriculados(relacoes, alunos);
        } catch (SecurityException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarMinhasDisciplinas(Professor professor) throws Exception {
        System.out.println("Minhas Disciplinas:");
        List<Disciplina> disciplinas = ArquivoUtils.listarDisciplinasPorProfessor(professor.getIdentificador());
        for (Disciplina disc : disciplinas) {
            System.out.println(disc);
        }
    }
}