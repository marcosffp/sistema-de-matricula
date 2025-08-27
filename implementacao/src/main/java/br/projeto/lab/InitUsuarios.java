package br.projeto.lab;

import br.projeto.lab.Modelos.*;
import br.projeto.lab.Utils.*;

public class InitUsuarios {
    public static void main(String[] args) throws Exception {
        System.out.println("Criando usuários iniciais...");
        
        // Criar secretária Martha
        Secretaria martha = new Secretaria("admin", "", "martha@pucminas.br", "Martha");
        ArquivoUtils.salvarSecretaria(martha);
        ArquivoUtils.salvarLogin("secretaria", "admin", "admin123");
        System.out.println("Secretária criada: Martha (ID: admin, senha: admin123)");
        
        // Criar aluno Bernardo
        Aluno bernardo = new Aluno("859148", "", "bernardo.alvim@sga.pucminas.br", "Bernardo Alvim", "Engenharia de Software");
        ArquivoUtils.salvarAluno(bernardo);
        ArquivoUtils.salvarLogin("aluno", "859148", "Bernardo@1");
        System.out.println("Aluno criado: Bernardo Alvim (ID: 859148, senha: Bernardo@1)");
        
        // Criar um curso de exemplo
        Curso curso = new Curso("Engenharia de Software", 240);
        ArquivoUtils.salvarCurso(curso);
        System.out.println("Curso criado: Engenharia de Software");
        
        System.out.println("Usuários criados com sucesso!");
    }
}