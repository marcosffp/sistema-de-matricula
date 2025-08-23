package br.projeto.lab.Modelos;

public abstract class Funcionario extends Usuario{
    private String departamento;

    public Funcionario (String identificador, String senha, String email, String nome, String departamento) {
        super(identificador, senha, email, nome);
        setDepartamento(departamento);
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
