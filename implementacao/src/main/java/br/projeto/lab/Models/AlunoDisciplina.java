package br.projeto.lab.Models;

public class AlunoDisciplina {
    private String idAluno;
    private String idDisciplina;
    private int creditosObtidos;

    public AlunoDisciplina(String idAluno, String idDisciplina, int creditosObtidos) {
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.creditosObtidos = creditosObtidos;
    }

    public String getIdAluno() { return idAluno; }
    public void setIdAluno(String idAluno) { this.idAluno = idAluno; }
    
    public String getIdDisciplina() { return idDisciplina; }
    public void setIdDisciplina(String idDisciplina) { this.idDisciplina = idDisciplina; }
    
    public int getCreditosObtidos() { return creditosObtidos; }
    public void setCreditosObtidos(int creditosObtidos) { this.creditosObtidos = creditosObtidos; }
}