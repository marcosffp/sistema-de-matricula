package br.projeto.lab.Handlers;

import br.projeto.lab.SistemaMatriculaFacade;
import br.projeto.lab.Models.*;
import br.projeto.lab.Utils.ArquivoUtils;
import java.util.Scanner;

public class AlunoHandler {
    private Scanner scanner;
    private SistemaMatriculaFacade facade;

    public AlunoHandler(Scanner scanner, SistemaMatriculaFacade facade) {
        this.scanner = scanner;
        this.facade = facade;
    }

    public void executarMenu(Aluno aluno) throws Exception {
        while (true) {
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());
            
            switch (opcao) {
                case 1: matricularDisciplina(aluno); break;
                case 2: cancelarMatricula(aluno); break;
                case 3: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\nMenu Aluno:");
        System.out.println("1. Matricular em disciplina");
        System.out.println("2. Cancelar matrícula");
        System.out.println("3. Sair");
        System.out.print("Escolha: ");
    }

    private void matricularDisciplina(Aluno aluno) throws Exception {
        System.out.print("ID da disciplina: ");
        String idDisc = scanner.nextLine();
        System.out.print("Créditos da disciplina: ");
        int creditos = Integer.parseInt(scanner.nextLine());
        
        Disciplina disciplina = ArquivoUtils.buscarDisciplinaPorId(idDisc);
        if (disciplina != null) {
            if (facade.podeMatricularAluno(aluno, disciplina)) {
                AlunoDisciplina relacao = new AlunoDisciplina(aluno.getIdentificador(), idDisc, creditos);
                ArquivoUtils.salvarAlunoDisciplina(relacao);
                System.out.println("Matriculado com sucesso!");
            } else {
                System.out.println("Erro: Usuário sem permissão para matricular.");
            }
        } else {
            System.out.println("Disciplina não encontrada!");
        }
    }

    private void cancelarMatricula(Aluno aluno) throws Exception {
        System.out.print("ID da disciplina: ");
        String idDisc = scanner.nextLine();
        
        if (facade.podeCancelarMatricula(aluno.getIdentificador(), idDisc)) {
            ArquivoUtils.removerAlunoDisciplina(aluno.getIdentificador(), idDisc);
            System.out.println("Matrícula cancelada com sucesso!");
        } else {
            System.out.println("Erro: Usuário sem permissão para cancelar matrícula.");
        }
    }
}