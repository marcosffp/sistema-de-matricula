package br.projeto.lab.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
  private Map<String, UserSession> activeSessions;
  private static SessionManager instance;

  private SessionManager() {
    this.activeSessions = new HashMap<>();
  }

  public static SessionManager getInstance() {
    if (instance == null) {
      instance = new SessionManager();
    }
    return instance;
  }

  public String createSession(String userId, String userType) {
    String token = UUID.randomUUID().toString();
    UserSession session = new UserSession(userId, userType, LocalDateTime.now().plusHours(8));
    activeSessions.put(token, session);
    return token;
  }

  public boolean isValidSession(String token) {
    UserSession session = activeSessions.get(token);
    if (session == null)
      return false;

    if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
      activeSessions.remove(token);
      return false;
    }
    return true;
  }

  public UserSession getSession(String token) {
    return activeSessions.get(token);
  }

  public void invalidateSession(String token) {
    activeSessions.remove(token);
  }

  public static class UserSession {
    private String userId;
    private String userType;
    private LocalDateTime expiresAt;

    public UserSession(String userId, String userType, LocalDateTime expiresAt) {
      this.userId = userId;
      this.userType = userType;
      this.expiresAt = expiresAt;
    }

    public String getUserId() {
      return userId;
    }

    public String getUserType() {
      return userType;
    }

    public LocalDateTime getExpiresAt() {
      return expiresAt;
    }
  }
}