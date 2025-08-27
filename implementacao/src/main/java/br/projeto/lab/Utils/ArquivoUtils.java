package br.projeto.lab.Utils;

import br.projeto.lab.Modelos.*;
import java.io.*;
import java.util.*;

public class ArquivoUtils {
    private static final String ALUNOS_FILE = "implementacao/arquivos/alunos.csv";
    private static final String PROFESSORES_FILE = "implementacao/arquivos/professores.csv";
    private static final String SECRETARIAS_FILE = "implementacao/arquivos/secretarias.csv";
    private static final String CURSOS_FILE = "implementacao/arquivos/cursos.csv";
    private static final String DISCIPLINAS_FILE = "implementacao/arquivos/disciplinas.csv";
    private static final String LOGINS_FILE = "implementacao/arquivos/logins.csv";

    // Alunos
    public static void salvarAluno(Aluno aluno) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ALUNOS_FILE, true))) {
            bw.write(aluno.getIdentificador() + ";" + aluno.getNome() + ";" + aluno.getEmail() + ";" +
                    aluno.getNomeCurso() + "\n");
        }
    }

    public static Aluno buscarAlunoPorId(String id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ALUNOS_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(id)) {
                    return new Aluno(partes[0], "", partes[2], partes[1], partes[3]);
                }
            }
        }
        return null;
    }

    // Professores
    public static void salvarProfessor(Professor professor) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PROFESSORES_FILE, true))) {
            bw.write(professor.getIdentificador() + ";" + professor.getNome() + ";" + professor.getEmail() + "\n");
        }
    }

    public static Professor buscarProfessorPorId(String id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(PROFESSORES_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(id)) {
                    return new Professor(partes[0], "", partes[2], partes[1]);
                }
            }
        }
        return null;
    }

    // Secretarias
    public static void salvarSecretaria(Secretaria secretaria) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SECRETARIAS_FILE, true))) {
            bw.write(secretaria.getIdentificador() + ";" + secretaria.getNome() + ";" + secretaria.getEmail() + "\n");
        }
    }

    public static Secretaria buscarSecretariaPorId(String id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(SECRETARIAS_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(id)) {
                    return new Secretaria(partes[0], "", partes[2], partes[1]);
                }
            }
        }
        return null;
    }

    // Cursos
    public static void salvarCurso(Curso curso) throws IOException {
        String idCurso = gerarIdNumericoUnico(CURSOS_FILE, 6); // 6 dígitos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CURSOS_FILE, true))) {
            bw.write(idCurso + ";" + curso.getNome() + ";" + curso.getCreditos() + "\n");
        }
    }

    public static Curso buscarCursoPorId(String idCurso) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(CURSOS_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(idCurso)) {
                    return new Curso(partes[1], Integer.parseInt(partes[2]));
                }
            }
        }
        return null;
    }

    // Disciplinas
    public static void salvarDisciplina(Disciplina disciplina, String idCurso, String idProf) throws IOException {
        String idDisc = gerarIdNumericoUnico(DISCIPLINAS_FILE, 6); // 6 dígitos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DISCIPLINAS_FILE, true))) {
            bw.write(idDisc + ";" + disciplina.getNome() + ";" + disciplina.getPeriodo() + ";" +
                    disciplina.isOptativa() + ";" + idProf + ";" + idCurso + "\n");
        }
    }

    public static Disciplina buscarDisciplinaPorId(String idDisc) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(DISCIPLINAS_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(idDisc)) {
                    String nome = partes[1];
                    int periodo = Integer.parseInt(partes[2]);
                    boolean optativa = Boolean.parseBoolean(partes[3]);
                    return new Disciplina(nome, periodo, optativa);
                }
            }
        }
        return null;
    }

    public static Usuario autenticarUsuario(String id, String senha) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(LOGINS_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[1].equals(id)) {
                    String senhaHash = partes[2];
                    if (SenhaUtil.verificarSenha(senha, senhaHash)) {
                        return UsuarioFactory.criarUsuario(partes[0], id);
                    } else {
                        throw new Exception("Senha incorreta.");
                    }
                }
            }
            throw new Exception("Usuário não encontrado.");
        }
    }

    public static void salvarLogin(String tipo, String id, String senhaNovo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOGINS_FILE, true))) {
            String senhaHash = SenhaUtil.gerarHashSenha(senhaNovo);
            bw.write(tipo + ";" + id + ";" + senhaHash + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String gerarIdNumericoUnico(String arquivo, int tamanho) throws IOException {
        Random rand = new Random();
        String id;
        Set<String> idsExistentes = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                idsExistentes.add(partes[0]);
            }
        } catch (Exception e) {
            System.out.println("Erro ao abrir arquivo... Gerando ID único");
        } finally {
            do {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < tamanho; i++) {
                    sb.append(rand.nextInt(10));
                }
                id = sb.toString();
            } while (idsExistentes.contains(id));
        }
        return id;
    }
}