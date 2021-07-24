package br.com.antonio.calcmeback.service.impl;

import br.com.antonio.calcmeback.model.Cadastro;
import br.com.antonio.calcmeback.repository.CadastroRepository;
import br.com.antonio.calcmeback.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Esta classe tem a responsabilidade de fazer a ligação do controller com o banco.
 * e implementa um contrato de um CRUD.
 *
 * @author Antônio Lima Jr
 */
@Service
public class CadastroServiceImpl implements CadastroService {

  private final CadastroRepository cadastroRepository;

  @Autowired
  public CadastroServiceImpl(CadastroRepository cadastroRepository) {
    this.cadastroRepository = cadastroRepository;
  }

  @Override
  public List<Cadastro> listAll() {
    return this.cadastroRepository.findAll();
  }

  @Override
  public Cadastro getById(String id) {
    return this.cadastroRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Cadastro não existe!"));
  }

  @Override
  public Cadastro create(Cadastro cadastro) {
    return this.cadastroRepository.save(cadastro);
  }

  @Override
  public Cadastro put(Cadastro cadastro) {
    return this.cadastroRepository.save(cadastro);
  }

  @Override
  public void delete(String id) {
    this.cadastroRepository.delete(getById(id));
  }
}