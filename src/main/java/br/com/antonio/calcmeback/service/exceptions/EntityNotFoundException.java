package br.com.antonio.calcmeback.service.exceptions;

/**
 * Exceção Personalizada.
 *
 * @author Antônio Lima Jr
 */
public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
