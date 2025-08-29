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
                return new Disciplina(partes[1], Integer.parseInt(partes[2]), Boolean.parseBoolean(partes[3]));
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
                disciplinas.add(new Disciplina(partes[1], Integer.parseInt(partes[2]), Boolean.parseBoolean(partes[3])));
            }
        }
        
        return disciplinas;
    }

    public static void removerDisciplina(String idDisciplina) throws IOException {
        FileManager.removerLinhaPorId(DISCIPLINAS_FILE, idDisciplina, 0);
    }
}