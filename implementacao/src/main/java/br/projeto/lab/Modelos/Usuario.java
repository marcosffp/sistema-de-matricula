package br.projeto.lab.Modelos;

import br.projeto.lab.Enums.Permissao;
import br.projeto.lab.Utils.GerenciadorSessao;
import br.projeto.lab.Utils.PasswordUtil;

public abstract class Usuario {
    private String identificador;
    private String senhaHash;
    private String email;
    private String nome;
    private boolean ativo;

    public Usuario(String identificador, String senha, String email, String nome) {
        this.identificador = identificador;
        this.senhaHash = PasswordUtil.senhaHash(senha);
        this.email = email;
        this.nome = nome;
        this.ativo = true;
    }

    public String realizarLogin(String identificador, String senha) {
        if (this.identificador.equals(identificador) &&
                PasswordUtil.verificarSenha(senha, this.senhaHash) &&
                this.ativo) {

            GerenciadorSessao sessionManager = GerenciadorSessao.getInstancia();
            return sessionManager.criarSessao(
                    this.identificador,
                    this.getClass().getSimpleName());
        } else {
            return null;
        }
    }

    public static boolean autenticado(String sessionToken) {
        return GerenciadorSessao.getInstancia().sessaoValida(sessionToken);
    }

    public static void logout(String sessionToken) {
        GerenciadorSessao.getInstancia().invalidarSessao(sessionToken);
    }

    public abstract boolean temPermissao(Permissao permissao);

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
