package br.projeto.lab.Utils;

import java.io.*;
import java.util.*;

public class FileManager {
    
    public static void escreverLinha(String arquivo, String linha) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(linha + "\n");
        }
    }
    
    public static List<String> lerArquivo(String arquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            // Arquivo não existe
        }
        return linhas;
    }
    
    public static void reescreverArquivo(String arquivo, List<String> linhas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : linhas) {
                bw.write(linha + "\n");
            }
        }
    }
    
    public static void removerLinhaPorId(String arquivo, String id, int colunaId) throws IOException {
        List<String> linhas = lerArquivo(arquivo);
        List<String> linhasRestantes = new ArrayList<>();
        
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length > colunaId && !partes[colunaId].equals(id)) {
                linhasRestantes.add(linha);
            }
        }
        
        reescreverArquivo(arquivo, linhasRestantes);
    }
    
    public static String gerarIdNumericoUnico(String arquivo, int tamanho) throws IOException {
        Random rand = new Random();
        Set<String> idsExistentes = new HashSet<>();
        
        List<String> linhas = lerArquivo(arquivo);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length > 0) {
                idsExistentes.add(partes[0]);
            }
        }
        
        String id;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tamanho; i++) {
                sb.append(rand.nextInt(10));
            }
            id = sb.toString();
        } while (idsExistentes.contains(id));
        
        return id;
    }
}