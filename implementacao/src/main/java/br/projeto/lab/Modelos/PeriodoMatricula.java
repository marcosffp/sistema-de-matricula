package br.projeto.lab.Modelos;

import java.time.LocalDateTime;

public class PeriodoMatricula {
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private boolean ativo;

    public PeriodoMatricula(LocalDateTime inicio, LocalDateTime fim) {
        this.inicio = inicio;
        this.fim = fim;
        this.ativo = true;
    }

    public boolean periodoAtivo() {
        LocalDateTime agora = LocalDateTime.now();
        return ativo && agora.isAfter(inicio) && agora.isBefore(fim);
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}