package br.projeto.lab.Services;

import br.projeto.lab.Utils.FileManager;
import java.io.IOException;
import java.util.List;

public class ValidationService {
    private static final String DISCIPLINAS_FILE = "implementacao/arquivos/disciplinas.csv";
    private static final String ALUNO_DISCIPLINA_FILE = "implementacao/arquivos/aluno_disciplina.csv";

    public static boolean cursoTemDisciplinas(String idCurso) throws IOException {
        List<String> linhas = FileManager.lerArquivo(DISCIPLINAS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length > 4 && partes[4].equals(idCurso)) {
                return true;
            }
        }
        return false;
    }

    public static boolean disciplinaTemAlunos(String idDisciplina) throws IOException {
        List<String> linhas = FileManager.lerArquivo(ALUNO_DISCIPLINA_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[1].equals(idDisciplina)) {
                return true;
            }
        }
        return false;
    }

    public static boolean usuarioTemVinculos(String idUsuario) throws IOException {
        // Verificar se é professor de alguma disciplina
        List<String> linhasDisciplinas = FileManager.lerArquivo(DISCIPLINAS_FILE);
        for (String linha : linhasDisciplinas) {
            String[] partes = linha.split(";");
            if (partes.length > 3 && partes[3].equals(idUsuario)) {
                return true;
            }
        }
        
        // Verificar se é aluno matriculado
        List<String> linhasMatriculas = FileManager.lerArquivo(ALUNO_DISCIPLINA_FILE);
        for (String linha : linhasMatriculas) {
            String[] partes = linha.split(";");
            if (partes[0].equals(idUsuario)) {
                return true;
            }
        }
        
        return false;
    }
}