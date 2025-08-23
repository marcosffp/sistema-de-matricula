package br.projeto.lab.Utils;

public class JwtUtil {
  public static String gerarToken(String usuarioId) {
    // Lógica para gerar um token JWT
    return "tokenGerado";
  }

  public static boolean validarToken(String token) {
    // Lógica para validar o token JWT
    return true;
  }

  public static String obterUsuarioId(String token) {
    // Lógica para extrair o ID do usuário do token JWT
    return "usuarioId";
  }
}
