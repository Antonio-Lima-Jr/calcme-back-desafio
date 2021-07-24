package br.com.antonio.calcmeback.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Modelo de dados que deve ser persistido no banco.
 *
 * @author Antônio Lima Jr
 */
@Data
@Document
public class Cadastro {
  @Id
  private String id;
  private String name;
  private String email;
  private String telefone;
}
