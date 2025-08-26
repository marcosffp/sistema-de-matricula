package br.projeto.lab.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SenhaUtil {

    private SenhaUtil() {}

    public static String gerarHashSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] sal = gerarSal();
            md.update(sal);
            byte[] hashSenha = md.digest(senha.getBytes());

            byte[] combinado = new byte[sal.length + hashSenha.length];
            System.arraycopy(sal, 0, combinado, 0, sal.length);
            System.arraycopy(hashSenha, 0, combinado, sal.length, hashSenha.length);

            return Base64.getEncoder().encodeToString(combinado);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    public static boolean verificarSenha(String senha, String hashArmazenado) {
        try {
            byte[] combinado = Base64.getDecoder().decode(hashArmazenado);
            byte[] sal = new byte[16];
            byte[] hash = new byte[combinado.length - 16];

            System.arraycopy(combinado, 0, sal, 0, sal.length);
            System.arraycopy(combinado, sal.length, hash, 0, hash.length);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(sal);
            byte[] novoHash = md.digest(senha.getBytes());

            return MessageDigest.isEqual(hash, novoHash);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    private static byte[] gerarSal() {
        SecureRandom random = new SecureRandom();
        byte[] sal = new byte[16];
        random.nextBytes(sal);
        return sal;
    }
}