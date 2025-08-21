package br.projeto.lab.Modelos;

import br.projeto.lab.Utils.PasswordUtil;
import br.projeto.lab.Utils.SessionManager;

public abstract class Usuario {
    private String identificador;
    private String senhaHash;
    private String email;
    private String nome;
    private boolean ativo;

    public Usuario(String identificador, String senha, String email, String nome) {
        this.identificador = identificador;
        this.senhaHash = PasswordUtil.hashPassword(senha);
        this.email = email;
        this.nome = nome;
        this.ativo = true;
    }

    public String realizarLogin(String identificador, String senha) {
        if (this.identificador.equals(identificador) &&
                PasswordUtil.verifyPassword(senha, this.senhaHash) &&
                this.ativo) {

            SessionManager sessionManager = SessionManager.getInstance();
            return sessionManager.createSession(
                    this.identificador,
                    this.getClass().getSimpleName());
        } else {
            return null;
        }
    }

    public static boolean isAuthenticated(String sessionToken) {
        return SessionManager.getInstance().isValidSession(sessionToken);
    }

    public static void logout(String sessionToken) {
        SessionManager.getInstance().invalidateSession(sessionToken);
    }

    // Método para verificar permissões baseado no tipo de usuário
    public abstract boolean temPermissao(String acao);

    // Getters
    public String getIdentificador() {
        return identificador;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
