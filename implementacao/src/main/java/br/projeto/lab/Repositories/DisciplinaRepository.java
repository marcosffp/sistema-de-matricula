package br.projeto.lab.Repositories;

import br.projeto.lab.Models.Disciplina;
import br.projeto.lab.Utils.FileManager;
import java.io.IOException;
import java.util.*;

public class DisciplinaRepository {
    private static final String DISCIPLINAS_FILE = "implementacao/arquivos/disciplinas.csv";

    // Formato CSV disciplinas: id;nome;periodo;optativa;professorId;cursoId
    public static void salvarDisciplina(Disciplina disciplina, String cursoId, String professorId) throws IOException {
        String id = FileManager.gerarIdNumericoUnico(DISCIPLINAS_FILE, 6);
        String linha = id + ";" + disciplina.getNome() + ";" + disciplina.getPeriodo() + ";" + 
                      disciplina.isOptativa() + ";" + professorId + ";" + cursoId;
        FileManager.escreverLinha(DISCIPLINAS_FILE, linha);
    }

    public static Disciplina buscarDisciplinaPorId(String idDisc) throws IOException {
        List<String> linhas = FileManager.lerArquivo(DISCIPLINAS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[0].equals(idDisc)) {
                return new Disciplina(partes[1], Integer.parseInt(partes[2]), Boolean.parseBoolean(partes[3]), partes[0]);
            }
        }
        return null;
    }

    public static List<Disciplina> listarDisciplinas() throws IOException {
        List<Disciplina> disciplinas = new ArrayList<>();
        List<String> linhas = FileManager.lerArquivo(DISCIPLINAS_FILE);
        
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                disciplinas.add(new Disciplina(partes[1], Integer.parseInt(partes[2]), Boolean.parseBoolean(partes[3]), partes[0]));
            }
        }
        
        return disciplinas;
    }

    public static void removerDisciplina(String idDisciplina) throws IOException {
        FileManager.removerLinhaPorId(DISCIPLINAS_FILE, idDisciplina, 0);
    }

    public static List<Disciplina> listarDisciplinasPorCurso(String idCurso) throws IOException {
        List<Disciplina> disciplinas = new ArrayList<>();
        List<String> linhas = FileManager.lerArquivo(DISCIPLINAS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 6 && partes[5].equals(idCurso)) {
                disciplinas.add(new Disciplina(partes[1], Integer.parseInt(partes[2]), Boolean.parseBoolean(partes[3]), partes[0]));
            }
        }
        return disciplinas;
    }

    public static List<Disciplina> listarDisciplinasPorProfessor(String idProfessor) throws IOException {
        List<Disciplina> disciplinas = new ArrayList<>();
        List<String> linhas = FileManager.lerArquivo(DISCIPLINAS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 5 && partes[4].equals(idProfessor)) {
                disciplinas.add(new Disciplina(partes[1], Integer.parseInt(partes[2]), Boolean.parseBoolean(partes[3]), partes[0]));
            }
        }
        return disciplinas;
    }
}