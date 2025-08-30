package br.projeto.lab.Repositories;

import br.projeto.lab.Models.*;
import br.projeto.lab.Utils.FileManager;
import java.io.IOException;
import java.util.*;

public class UsuarioRepository {
    private static final String ALUNOS_FILE = "implementacao/arquivos/alunos.csv";
    private static final String PROFESSORES_FILE = "implementacao/arquivos/professores.csv";
    private static final String SECRETARIAS_FILE = "implementacao/arquivos/secretarias.csv";

    // Formato CSV professores: identificador;nome;email
    public static void salvarProfessor(Professor professor) throws IOException {
        String linha = professor.getIdentificador() + ";" + professor.getNome() + ";" + professor.getEmail();
        FileManager.escreverLinha(PROFESSORES_FILE, linha);
    }

    // Formato CSV secretarias: identificador;nome;email  
    public static void salvarSecretaria(Secretaria secretaria) throws IOException {
        String linha = secretaria.getIdentificador() + ";" + secretaria.getNome() + ";" + secretaria.getEmail();
        FileManager.escreverLinha(SECRETARIAS_FILE, linha);
    }

    // Formato CSV alunos: identificador;nome;email;curso
    public static void salvarAluno(Aluno aluno) throws IOException {
        String linha = aluno.getIdentificador() + ";" + 
                      aluno.getNome() + ";" + aluno.getEmail() + ";" + aluno.getNomeCurso();
        FileManager.escreverLinha(ALUNOS_FILE, linha);
    }

    public static Professor buscarProfessorPorId(String id) throws IOException {
        List<String> linhas = FileManager.lerArquivo(PROFESSORES_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[0].equals(id)) {
                return new Professor(partes[0], "", partes[2], partes[1]);
            }
        }
        return null;
    }

    public static Secretaria buscarSecretariaPorId(String id) throws IOException {
        List<String> linhas = FileManager.lerArquivo(SECRETARIAS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[0].equals(id)) {
                return new Secretaria(partes[0], "", partes[2], partes[1]);
            }
        }
        return null;
    }

    public static Aluno buscarAlunoPorId(String id) throws IOException {
        List<String> linhas = FileManager.lerArquivo(ALUNOS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes[0].equals(id)) {
                return new Aluno(partes[0], "", partes[2], partes[1], partes[3]);
            }
        }
        return null;
    }

    public static List<Usuario> listarUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        
        // Alunos: identificador;nome;email;curso
        List<String> linhasAlunos = FileManager.lerArquivo(ALUNOS_FILE);
        for (String linha : linhasAlunos) {
            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                usuarios.add(new Aluno(partes[0], "", partes[2], partes[1], partes[3]));
            }
        }
        
        // Professores: identificador;nome;email
        List<String> linhasProfessores = FileManager.lerArquivo(PROFESSORES_FILE);
        for (String linha : linhasProfessores) {
            String[] partes = linha.split(";");
            if (partes.length >= 3) {
                usuarios.add(new Professor(partes[0], "", partes[2], partes[1]));
            }
        }
        
        // Secretarias: identificador;nome;email
        List<String> linhasSecretarias = FileManager.lerArquivo(SECRETARIAS_FILE);
        for (String linha : linhasSecretarias) {
            String[] partes = linha.split(";");
            if (partes.length >= 3) {
                usuarios.add(new Secretaria(partes[0], "", partes[2], partes[1]));
            }
        }
        
        return usuarios;
    }

    public static void removerUsuario(String idUsuario) throws IOException {
        FileManager.removerLinhaPorId(ALUNOS_FILE, idUsuario, 0);
        FileManager.removerLinhaPorId(PROFESSORES_FILE, idUsuario, 0);
        FileManager.removerLinhaPorId(SECRETARIAS_FILE, idUsuario, 0);
    }
}