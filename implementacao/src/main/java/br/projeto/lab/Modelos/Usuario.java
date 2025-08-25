package br.projeto.lab.Modelos;

import br.projeto.lab.Utils.GerenciadorSessao;
import br.projeto.lab.Utils.SenhaUtil;
import br.projeto.lab.Enums.Permissao;

public abstract class Usuario {
    private String identificador;
    private String senhaHash;
    private String email;
    private String nome;
    private boolean ativo;

    public Usuario(String identificador, String senha, String email, String nome) {
        this.identificador = identificador;
        this.senhaHash = SenhaUtil.gerarHashSenha(senha);
        this.email = email;
        this.nome = nome;
        this.ativo = true;
    }

    public String realizarLogin(String identificador, String senha) {
        if (this.identificador.equals(identificador) &&
                SenhaUtil.verificarSenha(senha, this.senhaHash) &&
                this.ativo) {

            GerenciadorSessao gerenciadorSessao = GerenciadorSessao.getInstancia();
            return gerenciadorSessao.criarSessao(
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

    // Método para verificar permissões baseado no tipo de usuário
    public abstract boolean temPermissao(Permissao permissao);

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
