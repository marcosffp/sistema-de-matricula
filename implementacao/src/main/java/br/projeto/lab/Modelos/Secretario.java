package br.projeto.lab.Modelos;

import java.util.ArrayList;
import java.util.List;

import br.projeto.lab.Enums.Permissao;

public class Secretario extends Usuario {

  private List<Aluno> alunosGerenciados;
  private List<Disciplina> disciplinasGerenciadas;
  private List<Professor> professoresGerenciados;
  private List<String> cursosGerenciados;

  public Secretario(String identificador, String senha, String email, String nome) {
    super(identificador, senha, email, nome);
    this.alunosGerenciados = new ArrayList<>();
    this.disciplinasGerenciadas = new ArrayList<>();
    this.professoresGerenciados = new ArrayList<>();
    this.cursosGerenciados = new ArrayList<>();
  }

 @Override
 public boolean temPermissao(Permissao permissao) {
   return switch (permissao) {
     case GERENCIAR_CURRICULO,
         GERENCIAR_DISCIPLINAS,
         GERENCIAR_PROFESSORES,
         GERENCIAR_ALUNOS,
         DEFINIR_PERIODO_MATRICULA ->
       true;
     default -> false;
   };
 }

 public List<Aluno> getAlunosGerenciados() {
  return alunosGerenciados;
 }

 public void setAlunosGerenciados(Aluno aluno) {
  this.alunosGerenciados.add(aluno);
 }

 public List<Disciplina> getDisciplinasGerenciadas() {
  return disciplinasGerenciadas;
 }

 public void setDisciplinasGerenciadas(Disciplina disciplina) {
  this.disciplinasGerenciadas.add(disciplina);
 }

 public List<Professor> getProfessoresGerenciados() {
  return professoresGerenciados;
 }

 public void setProfessoresGerenciados(Professor professor) {
  this.professoresGerenciados.add(professor);
 }

 public List<String> getCursosGerenciados() {
  return cursosGerenciados;
 }

 public void setCursosGerenciados(String curso) {
  this.cursosGerenciados.add(curso);
 }

 public String gerarCurriculoSemestral() {
   return "";
 }

  
 
  
}
