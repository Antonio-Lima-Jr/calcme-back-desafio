package br.com.antonio.calcmeback.service;

import br.com.antonio.calcmeback.model.Cadastro;

import java.util.List;

/**
 * Interface necessária para diminuir a dependência e
 * acoplamento de classes que necessitem destes métodos,
 * facilitando futuras alterações e adições de features.
 *
 * @author Antônio Lima Jr
 */
public interface CadastroService {

  public List<Cadastro> listAll();

  public Cadastro getById(String id);

  public Cadastro create(Cadastro cadastro);

  public Cadastro put(Cadastro cadastro);

  public void delete(String id);
}