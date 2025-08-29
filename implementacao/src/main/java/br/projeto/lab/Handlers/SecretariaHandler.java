package br.projeto.lab.Handlers;

import br.projeto.lab.SistemaMatriculaFacade;
import br.projeto.lab.Services.CrudService;
import java.util.Scanner;

public class SecretariaHandler {
    private Scanner scanner;
    private SistemaMatriculaFacade facade;
    private CrudService crudService;

    public SecretariaHandler(Scanner scanner, SistemaMatriculaFacade facade) {
        this.scanner = scanner;
        this.facade = facade;
        this.crudService = new CrudService(facade);
    }

    public void executarMenu() throws Exception {
        while (true) {
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());
            
            switch (opcao) {
                case 1: crudService.criarCurso(scanner); break;
                case 2: crudService.criarDisciplina(scanner); break;
                case 3: crudService.criarUsuario(scanner); break;
                case 4: crudService.listarCursos(); break;
                case 5: crudService.listarDisciplinas(); break;
                case 6: crudService.listarUsuarios(); break;
                case 7: crudService.removerCurso(scanner); break;
                case 8: crudService.removerDisciplina(scanner); break;
                case 9: crudService.removerUsuario(scanner); break;
                case 10: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\nMenu Secretaria:");
        System.out.println("1. Criar curso");
        System.out.println("2. Criar disciplina");
        System.out.println("3. Criar usuário");
        System.out.println("4. Listar cursos");
        System.out.println("5. Listar disciplinas");
        System.out.println("6. Listar usuários");
        System.out.println("7. Remover curso");
        System.out.println("8. Remover disciplina");
        System.out.println("9. Remover usuário");
        System.out.println("10. Sair");
        System.out.print("Escolha: ");
    }
}