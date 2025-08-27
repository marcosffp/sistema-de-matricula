package br.projeto.lab.Modelos;

import java.io.*;

public class UsuarioFactory {
    public static Usuario criarUsuario(String tipo, String identificador) throws Exception {
        switch (tipo) {
            case "aluno":
                return criarAluno(identificador);
            case "professor":
                return criarProfessor(identificador);
            case "secretaria":
                return criarSecretaria(identificador);
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido.");
        }
    }

    private static Aluno criarAluno(String identificador) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("implementacao/arquivos/alunos.csv"));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(";");
            if (partes[0].equals(identificador)) {
                String nome = partes[1], email = partes[2], curso = partes[3];
                br.close();
                return new Aluno(identificador, "", email, nome, curso);
            }
        }
        br.close();
        return null;
    }

    private static Professor criarProfessor(String identificador) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("implementacao/arquivos/professores.csv"));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(";");
            if (partes[0].equals(identificador)) {
                String nome = partes[1], email = partes[2];
                br.close();
                return new Professor(identificador, "", email, nome);
            }
        }
        br.close();
        return null;
    }

    private static Secretaria criarSecretaria(String identificador) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("implementacao/arquivos/secretarias.csv"));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(";");
            if (partes[0].equals(identificador)) {
                String nome = partes[1], email = partes[2];
                br.close();
                return new Secretaria(identificador, "", email, nome);
            }
        }
        br.close();
        return null;
    }
}