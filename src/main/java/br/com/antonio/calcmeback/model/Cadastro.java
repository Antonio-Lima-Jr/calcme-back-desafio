package br.com.antonio.calcmeback.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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
  @NotEmpty(message = "O nome no cadastro não pode ser vazio")
  @NotNull(message = "O nome no cadastro nao pode ser null")
  @Length(min = 3, max = 50, message = "O nome deverá ter entre {min} e {max} caracteres")
  private String name;
  @Email(message = "Não é um email válido")
  private String email;
  @Pattern(regexp = "\\d{9,11}", message = "O numero de telefone deve conter entre 9 e 11 caracteres numéricos")
  private String telefone;
}
