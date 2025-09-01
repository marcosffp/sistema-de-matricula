package br.projeto.lab.Repositories;

import br.projeto.lab.Utils.FileManager;
import br.projeto.lab.Utils.SenhaUtil;

import java.io.IOException;
import java.util.List;

public class LoginRepository {
    private static final String LOGINS_FILE = "implementacao/arquivos/logins.csv";

    // Formato CSV logins: tipo;identificador;senha
    public static void salvarLogin(String tipo, String identificador, String senha) throws IOException {
        String senhaHash = SenhaUtil.gerarHashSenha(senha);
        String linha = tipo + ";" + identificador + ";" + senhaHash;
        FileManager.escreverLinha(LOGINS_FILE, linha);
    }

    public static String[] buscarLogin(String identificador, String senha) throws IOException {
        List<String> linhas = FileManager.lerArquivo(LOGINS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 3 && partes[1].equals(identificador) && SenhaUtil.verificarSenha(senha, partes[2])) {
                return partes; // [tipo, identificador, senhaHash]
            }
        }
        return null;
    }

    public static void removerLogin(String identificador) throws IOException {
        FileManager.removerLinhaPorId(LOGINS_FILE, identificador, 1);
    }

    public static boolean loginExiste(String identificador) throws IOException {
        List<String> linhas = FileManager.lerArquivo(LOGINS_FILE);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 2 && partes[1].equals(identificador)) {
                return true;
            }
        }
        return false;
    }
}