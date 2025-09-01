package br.projeto.lab.Handlers;

import br.projeto.lab.SistemaMatriculaFacade;
import br.projeto.lab.Models.*;

import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private SistemaMatriculaFacade facade;

    public MenuHandler(Scanner scanner, SistemaMatriculaFacade facade) {
        this.scanner = scanner;
        this.facade = facade;
    }

    public void menuSecretaria() throws Exception {
        SecretariaHandler secretariaHandler = new SecretariaHandler(scanner, facade);
        secretariaHandler.executarMenu();
    }

    public void menuAluno(Aluno aluno) throws Exception {
        AlunoHandler alunoHandler = new AlunoHandler(scanner, facade);
        alunoHandler.executarMenu(aluno);
    }

    public void menuProfessor(Professor professor) throws Exception {
        ProfessorHandler professorHandler = new ProfessorHandler(scanner, facade);
        professorHandler.executarMenu(professor);
    }
}