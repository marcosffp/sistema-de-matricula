package br.projeto.lab.Repositories;

import br.projeto.lab.Models.Curso;
import br.projeto.lab.Utils.FileManager;
import java.io.IOException;
import java.util.*;

public class CursoRepository {
    private static final String CURSOS_FILE = "implementacao/arquivos/cursos.csv";

    // Formato CSV cursos: id;nome;creditos
    public static void salvarCurso(Curso curso) throws IOException {
        String id = FileManager.gerarIdNumericoUnico(CURSOS_FILE, 6);
        String linha = id + ";" + curso.getNome() + ";" + curso.getCreditos();
        FileManager.escreverLinha(CURSOS_FILE, linha);
    }

    public static Curso buscarCursoPorId(String idCurso) throws IOException {
        List<String> linhas = FileManager.lerArquivo(CURSOS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[0].equals(idCurso)) {
                return new Curso(partes[1], Integer.parseInt(partes[2]), partes[0]);
            }
        }
        return null;
    }

    public static Curso buscarCursoPorNome(String nomeCurso) throws IOException {
        List<String> linhas = FileManager.lerArquivo(CURSOS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[1].equals(nomeCurso)) {
                return new Curso(partes[1], Integer.parseInt(partes[2]), partes[0]);
            }
        }
        return null;
    }

    public static List<Curso> listarCursos() throws IOException {
        List<Curso> cursos = new ArrayList<>();
        List<String> linhas = FileManager.lerArquivo(CURSOS_FILE);
        
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 3) {
                cursos.add(new Curso(partes[1], Integer.parseInt(partes[2]), partes[0]));
            }
        }
        
        return cursos;
    }

    public static void removerCurso(String idCurso) throws IOException {
        FileManager.removerLinhaPorId(CURSOS_FILE, idCurso, 0);
    }
}