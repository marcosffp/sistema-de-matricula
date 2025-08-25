package br.projeto.lab;

import br.projeto.lab.Modelos.*;
import br.projeto.lab.Controllers.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎓 Sistema de Matrícula - Teste Básico");
        
        try {
            // Criar período de matrícula
            PeriodoMatricula periodo = new PeriodoMatricula(
                LocalDateTime.now().minusDays(1), 
                LocalDateTime.now().plusDays(30)
            );
            
            // Criar professor
            Professor prof = new Professor("123", "senha123", "prof@univ.br", "Dr. João", "ICEI");
            
            // Criar disciplinas
            Disciplina disc1 = new Disciplina("Matemática", prof, true);
            Disciplina disc2 = new Disciplina("Física", prof, false);
            
            // Criar grade curricular
            GradeCurricular grade = new GradeCurricular("Engenharia", 1, 
                Arrays.asList(disc1), Arrays.asList(disc2));
            
            // Criar curso
            Curso curso = new Curso("Engenharia da Computação", 240, Arrays.asList(grade));
            
            // Criar aluno
            Aluno aluno = new Aluno("456", "senha456", "aluno@univ.br", "Maria", "Engenharia");
            
            // Criar controller
            MatriculaController controller = new MatriculaController(periodo);
            
            // Testar matrícula
            controller.criarMatricula(aluno, curso, 1, Arrays.asList(disc1));
            
            System.out.println("✅ Teste básico passou! Sistema funcionando.");
            
        } catch (Exception e) {
            System.err.println("❌ Erro no teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}