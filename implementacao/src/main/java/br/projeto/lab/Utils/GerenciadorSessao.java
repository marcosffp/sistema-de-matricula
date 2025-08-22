package br.projeto.lab.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GerenciadorSessao {
  private Map<String, SessaoUsuario> sessoesAtivas;
  private static GerenciadorSessao instancia;

  private GerenciadorSessao() {
    this.sessoesAtivas = new HashMap<>();
  }

  public static GerenciadorSessao getInstancia() {
    if (instancia == null) {
      instancia = new GerenciadorSessao();
    }
    return instancia;
  }

  public String criarSessao(String idUsuario, String tipoUsuario) {
    String token = UUID.randomUUID().toString();
    SessaoUsuario sessao = new SessaoUsuario(idUsuario, tipoUsuario, LocalDateTime.now().plusHours(8));
    sessoesAtivas.put(token, sessao);
    return token;
  }

  public boolean sessaoValida(String token) {
    SessaoUsuario sessao = sessoesAtivas.get(token);
    if (sessao == null)
      return false;

    if (sessao.getExpiraEm().isBefore(LocalDateTime.now())) {
      sessoesAtivas.remove(token);
      return false;
    }
    return true;
  }

  public SessaoUsuario getSessao(String token) {
    return sessoesAtivas.get(token);
  }

  public void invalidarSessao(String token) {
    sessoesAtivas.remove(token);
  }

  public static class SessaoUsuario {
    private String idUsuario;
    private String tipoUsuario;
    private LocalDateTime expiraEm;

    public SessaoUsuario(String idUsuario, String tipoUsuario, LocalDateTime expiraEm) {
      this.idUsuario = idUsuario;
      this.tipoUsuario = tipoUsuario;
      this.expiraEm = expiraEm;
    }

    public String getIdUsuario() {
      return idUsuario;
    }

    public String getTipoUsuario() {
      return tipoUsuario;
    }

    public LocalDateTime getExpiraEm() {
      return expiraEm;
    }
  }
}