package br.projeto.lab.Modelos;

import br.projeto.lab.Enums.Permissao;
import br.projeto.lab.Utils.SenhaUtil;

public abstract class Usuario {
    private String identificador;
    private String senhaHash;
    private String email;
    private String nome;

    public Usuario(String identificador, String senha, String email, String nome) {
        this.identificador = identificador;
        this.senhaHash = SenhaUtil.gerarHashSenha(senha);
        this.email = email;
        this.nome = nome;
    }

    public boolean verificarSenha(String senha) {
        return SenhaUtil.verificarSenha(senha, this.senhaHash);
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
}
