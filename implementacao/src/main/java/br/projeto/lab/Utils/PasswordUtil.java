package br.projeto.lab.Utils;

import java.util.Random;

public class PasswordUtil {
  public static String generateRandomPassword(int length) {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-+";
    Random random = new Random();
    StringBuilder password = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(chars.length());
      password.append(chars.charAt(index));
    }
    return password.toString();
  }

  public static boolean verificarSenha(String senha, String senhaHash) {
    return senhaHash.equals(PasswordUtil.senhaHash(senha));
  }

  public static String senhaHash(String senha) {

    return senha; 
  }
}