package br.projeto.lab.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.management.OperationsException;

import br.projeto.lab.Exceptions.OperacaoException;

public class PasswordUtil {

  private PasswordUtil() {
    // Construtor privado para evitar instâncias
  }

  public static String hashPassword(String password) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] salt = generateSalt();
      md.update(salt);
      byte[] hashedPassword = md.digest(password.getBytes());

      // Combina salt + hash para armazenamento
      byte[] combined = new byte[salt.length + hashedPassword.length];
      System.arraycopy(salt, 0, combined, 0, salt.length);
      System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);

      return Base64.getEncoder().encodeToString(combined);
    } catch (NoSuchAlgorithmException e) {
      throw new OperacaoException("Erro ao gerar hash da senha", e);
    }
  }

  public static boolean verifyPassword(String password, String hashedPassword) {
    try {
      byte[] combined = Base64.getDecoder().decode(hashedPassword);
      byte[] salt = new byte[16];
      byte[] hash = new byte[combined.length - 16];

      System.arraycopy(combined, 0, salt, 0, salt.length);
      System.arraycopy(combined, salt.length, hash, 0, hash.length);

      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(salt);
      byte[] newHash = md.digest(password.getBytes());

      return MessageDigest.isEqual(hash, newHash);
    } catch (NoSuchAlgorithmException e) {
      return false;
    }
  }

  private static byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }
}