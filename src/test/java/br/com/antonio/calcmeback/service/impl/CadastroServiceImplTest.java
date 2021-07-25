package br.com.antonio.calcmeback.service.impl;

import br.com.antonio.calcmeback.model.Cadastro;
import br.com.antonio.calcmeback.repository.CadastroRepository;
import br.com.antonio.calcmeback.service.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.MessageFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class Description.
 *
 * @author Antônio Lima Jr
 */
@ExtendWith(MockitoExtension.class)
class CadastroServiceImplTest {

  @InjectMocks
  private CadastroServiceImpl cadastroService;

  @Mock
  private CadastroRepository cadastroRepository;

  private Cadastro cadastroEsperado(String teste) {
    Cadastro cadastroEsperado = new Cadastro();
    cadastroEsperado.setId(teste);
    cadastroEsperado.setName("teste");
    cadastroEsperado.setEmail("teste@teste.com");
    cadastroEsperado.setTelefone("999999999999");
    return cadastroEsperado;
  }

  @Test
  void listAllTest() {
    String idTeste = "9999";
    String idTeste2 = "8888";
    List<Cadastro> listaEsperada = List.of(
        this.cadastroEsperado(idTeste),
        this.cadastroEsperado(idTeste2)
    );

    Mockito.when(this.cadastroRepository.findAll())
        .thenReturn(listaEsperada);

    assertEquals(this.cadastroService.listAll(), listaEsperada);
  }

  @Test
  void getByIdTest() {
    String idTeste = "9999";
    String idTeste2 = "99998888";

    String messageErrorEmException = MessageFormat.format("Não encontramos nenhum cadastro com este id: {0}", idTeste2);

    Mockito.when(this.cadastroRepository.findById(idTeste))
        .thenReturn(java.util.Optional.of(cadastroEsperado(idTeste)));

    Mockito.when(this.cadastroRepository.findById(idTeste2))
        .thenThrow(new EntityNotFoundException(messageErrorEmException));


    assertEquals(this.cadastroService.getById(idTeste), this.cadastroEsperado(idTeste));
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> this.cadastroService.getById(idTeste2));
    assertTrue(exception.getMessage().contains(messageErrorEmException));
  }

  @Test
  void createTest() {
    String idTeste = "9999";
    Cadastro cadastroEsperado = this.cadastroEsperado(idTeste);

    Mockito.when(this.cadastroRepository.save(cadastroEsperado))
        .thenReturn(cadastroEsperado);

    assertEquals(this.cadastroService.create(cadastroEsperado), cadastroEsperado);
  }

  @Test
  void putTest() {
    String idTeste = "9999";
    Cadastro cadastroEsperado = this.cadastroEsperado(idTeste);

    Mockito.when(this.cadastroRepository.findById(idTeste))
        .thenReturn(java.util.Optional.of(cadastroEsperado));
    Mockito.when(this.cadastroRepository.save(cadastroEsperado))
        .thenReturn(cadastroEsperado);

    assertEquals(this.cadastroService.put(cadastroEsperado), cadastroEsperado);
  }

  @Test
  void deleteTest() {
    String idTeste = "9999";
    Cadastro cadastroEsperado = this.cadastroEsperado(idTeste);

    Mockito.when(this.cadastroRepository.findById(idTeste))
        .thenThrow(new EntityNotFoundException("Não encontramos nenhum cadastro com este id: " + idTeste));

    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> this.cadastroService.delete(idTeste));
    assertTrue(exception.getMessage().contains("Não encontramos nenhum cadastro com este id: " + idTeste));
  }
}