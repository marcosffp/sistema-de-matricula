package br.projeto.lab.Models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Curso {
    private String id;
    private String nome;
    private int creditos;
    private Set<Disciplina> disciplinas;

    public Curso(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = new HashSet<>();
    }

    public Curso(String nome, int creditos, String id) {
        this.nome = nome;
        this.creditos = creditos;
        this.id = id;
        this.disciplinas = new HashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Set<Disciplina> getDisciplinasPorPeriodo(int periodo) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getPeriodo() == periodo)
                .collect(Collectors.toSet());
    }

    public Set<Disciplina> getDisciplinasPorNome(String entrada) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getNome()
                        .toLowerCase()
                        .contains(entrada.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public void incluirDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }

    public String getId() {
        return id;
    }
}
