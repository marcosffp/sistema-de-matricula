package br.projeto.lab.Modelos;

import java.util.HashSet;
import java.util.Set;

import br.projeto.lab.Enums.Permissao;

public class Secretaria extends Usuario {
  private Set<Curso> cursosGerenciados;

  public Secretaria(String identificador, String senha, String email, String nome) {
    super(identificador, senha, email, nome);
    this.cursosGerenciados = new HashSet<>();
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

 public void incluirCurso(Curso curso) {
  this.cursosGerenciados.add(curso);
 }

 public void removerCurso(Curso curso) {
  this.cursosGerenciados.remove(curso);
 }

 public void editarCurso(Curso antigo, Curso novo) {
  this.cursosGerenciados.remove(antigo);
  this.cursosGerenciados.add(novo);
 }
}
