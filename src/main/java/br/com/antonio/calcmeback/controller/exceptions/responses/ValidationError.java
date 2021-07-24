package br.com.antonio.calcmeback.controller.exceptions.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * Modelo de resposta para erros de validação de dados.
 *
 * @author Antônio Lima Jr
 */
@Getter
@Setter
@NoArgsConstructor
public class ValidationError implements Serializable {
  private static final long serialVersionUID = 1L;

  private Instant timestamp;
  private Integer status;
  private String error;
  private List<String> messages;
  private String path;
}
