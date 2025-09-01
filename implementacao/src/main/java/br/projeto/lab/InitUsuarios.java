package br.projeto.lab;

import br.projeto.lab.Models.Aluno;
import br.projeto.lab.Models.Curso;
import br.projeto.lab.Models.Professor;
import br.projeto.lab.Models.Secretaria;
import br.projeto.lab.Utils.ArquivoUtils;

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
        
        // Criar professor Carlos Silva
        Professor carlos = new Professor("246810", "", "carlos.silva@uni.com.br", "Carlos Silva");
        ArquivoUtils.salvarProfessor(carlos);
        ArquivoUtils.salvarLogin("professor", "246810", "Carlos@123");
        System.out.println("Professor criado: Carlos Silva (ID: 246810, senha: Carlos@123)");

        // Criar um curso de exemplo
        Curso curso = new Curso("Engenharia de Software", 240);
        ArquivoUtils.salvarCurso(curso);
        System.out.println("Curso criado: Engenharia de Software");
        
        System.out.println("Usuários criados com sucesso!");
    }
}