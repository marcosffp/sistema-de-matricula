package br.projeto.lab.Exceptions;

public class OperacaoException extends RuntimeException {
  public OperacaoException(String message) {
    super(message);
  }

  public OperacaoException(String message, Throwable cause) {
    super(message, cause);
  }
}
