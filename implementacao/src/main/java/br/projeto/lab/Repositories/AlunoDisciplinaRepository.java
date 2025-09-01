package br.projeto.lab.Repositories;

import br.projeto.lab.Models.AlunoDisciplina;
import br.projeto.lab.Utils.FileManager;
import java.io.IOException;
import java.util.*;

public class AlunoDisciplinaRepository {
    private static final String ALUNO_DISCIPLINA_FILE = "implementacao/arquivos/aluno_disciplina.csv";

    public static void salvarAlunoDisciplina(AlunoDisciplina relacao) throws IOException {
        String linha = relacao.getIdAluno() + ";" + relacao.getIdDisciplina() + ";" + relacao.getCreditosObtidos();
        FileManager.escreverLinha(ALUNO_DISCIPLINA_FILE, linha);
    }

    public static void removerAlunoDisciplina(String idAluno, String idDisciplina) throws IOException {
        List<String> linhas = FileManager.lerArquivo(ALUNO_DISCIPLINA_FILE);
        List<String> linhasRestantes = new ArrayList<>();
        
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (!(partes[0].equals(idAluno) && partes[1].equals(idDisciplina))) {
                linhasRestantes.add(linha);
            }
        }
        
        FileManager.reescreverArquivo(ALUNO_DISCIPLINA_FILE, linhasRestantes);
    }

    public static List<AlunoDisciplina> listarAlunosMatriculadosNaDisciplina(String idDisciplina) throws IOException {
        List<AlunoDisciplina> alunos = new ArrayList<>();
        List<String> linhas = FileManager.lerArquivo(ALUNO_DISCIPLINA_FILE);
        
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[1].equals(idDisciplina)) {
                alunos.add(new AlunoDisciplina(partes[0], partes[1], Integer.parseInt(partes[2])));
            }
        }
        
        return alunos;
    }

    public static List<AlunoDisciplina> listarDisciplinasMatriculadasDoAluno(String idAluno) throws IOException {
        List<AlunoDisciplina> disciplinas = new ArrayList<>();
        List<String> linhas = FileManager.lerArquivo(ALUNO_DISCIPLINA_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[0].equals(idAluno)) {
                disciplinas.add(new AlunoDisciplina(partes[0], partes[1], Integer.parseInt(partes[2])));
            }
        }
        return disciplinas;
    }
}