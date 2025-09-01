package br.projeto.lab;

import br.projeto.lab.Utils.ArquivoUtils;
import br.projeto.lab.Handlers.MenuHandler;
import br.projeto.lab.Models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Sistema de Matrícula ===");
        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = ArquivoUtils.autenticarUsuario(identificador, senha);
        if (usuario == null) {
            System.out.println("Usuário ou senha inválidos!");
            return;
        }

        System.out.println("Bem-vindo, " + usuario.getNome() + " (" + usuario.getClass().getSimpleName() + ")");

        SistemaMatriculaFacade facade = new SistemaMatriculaFacade(usuario);
        MenuHandler menuHandler = new MenuHandler(scanner, facade);

        if (usuario instanceof Secretaria) {
            menuHandler.menuSecretaria();
        } else if (usuario instanceof Aluno) {
            menuHandler.menuAluno((Aluno) usuario);
        } else if (usuario instanceof Professor) {
            menuHandler.menuProfessor((Professor) usuario);
        }
        
        scanner.close();
    }
}