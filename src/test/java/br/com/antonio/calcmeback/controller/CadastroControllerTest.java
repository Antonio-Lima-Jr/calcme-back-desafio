package br.com.antonio.calcmeback.controller;

import br.com.antonio.calcmeback.model.Cadastro;
import br.com.antonio.calcmeback.service.impl.CadastroServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Test Unitário para o CadastroController.
 *
 * @author Antônio Lima Jr
 */
@WebMvcTest(CadastroController.class)
class CadastroControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CadastroServiceImpl cadastroService;

  private Cadastro cadastroExpectedWithId(String teste) {
    Cadastro cadastroEsperado = new Cadastro();
    cadastroEsperado.setId(teste);
    cadastroEsperado.setName("teste");
    cadastroEsperado.setEmail("teste@teste.com");
    cadastroEsperado.setTelefone("99999999999");
    return cadastroEsperado;
  }

  private Cadastro cadastroExpectedWithoutId() {
    Cadastro cadastroEsperado = new Cadastro();
    cadastroEsperado.setName("teste");
    cadastroEsperado.setEmail("teste@teste.com");
    cadastroEsperado.setTelefone("99999999999");
    return cadastroEsperado;
  }

  @Test
  void listAllTeste() throws Exception {
    List<Cadastro> result = List.of(this.cadastroExpectedWithId("teste"), this.cadastroExpectedWithId("teste2"));

    Gson gson = new Gson();
    String json = gson.toJson(result);

    Mockito.when(cadastroService.listAll()).thenReturn(result);

    this.mockMvc.perform(
        get("/cadastro"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(String.valueOf(json)));
  }

  @Test
  void getByIdTeste() throws Exception {
    String idTest = "0ybsc";
    Cadastro result = this.cadastroExpectedWithId(idTest);

    Gson gson = new Gson();
    String jsonExpected = gson.toJson(result);

    Mockito.when(cadastroService.getById(idTest)).thenReturn(result);

    this.mockMvc.perform(
        get("/cadastro/{id}", idTest))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonExpected));
  }

  @Test
  void verificarCriacaoDeCadastroNoBanco_quandoCreate() throws Exception {
    String idTest = "teste";

    Gson gson = new GsonBuilder().serializeNulls().create();

    String jsonResponseApi = gson.toJson(this.cadastroExpectedWithId(idTest));

    String jsonSendedApi = gson.toJson(this.cadastroExpectedWithoutId());

    Mockito.when(this.cadastroService.create(this.cadastroExpectedWithoutId())).
        thenReturn(this.cadastroExpectedWithId(idTest));

    this.mockMvc.perform(
        post("/cadastro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonSendedApi)
            .accept(MediaType.APPLICATION_JSON)
    )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(
            MockMvcResultMatchers.content().json(jsonResponseApi)
        );
  }

  @Test
  void verificarAsExceptions_quandoEnviarCadastroInvalido() throws Exception {
    String idTest = "teste";
    Cadastro cadastroComCamposInvalidos = new Cadastro();
    cadastroComCamposInvalidos.setName("a");
    cadastroComCamposInvalidos.setEmail("emailInvalido");
    cadastroComCamposInvalidos.setTelefone("88559999999999999");
    Cadastro cadastroComCamposInvalidosAndId = new Cadastro();
    cadastroComCamposInvalidosAndId.setName("a");
    cadastroComCamposInvalidosAndId.setEmail("emailInvalido");
    cadastroComCamposInvalidosAndId.setTelefone("88559999999999999");
    cadastroComCamposInvalidosAndId.setId(idTest);

    Mockito.when(this.cadastroService.create(cadastroComCamposInvalidos))
        .thenReturn(cadastroComCamposInvalidosAndId);

    Gson gsonWithOutNulls = new Gson();

    String jsonSendedApi = gsonWithOutNulls.toJson(cadastroComCamposInvalidos);

    this.mockMvc.perform(
        post("/cadastro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonSendedApi)
            .accept(MediaType.APPLICATION_JSON)
    )
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void putTeste() throws Exception {
    String idTest = "0ybsc";
    Cadastro result = this.cadastroExpectedWithId(idTest);

    Gson gson = new Gson();
    String jsonExpected = gson.toJson(result);

    Mockito.when(cadastroService.put(cadastroExpectedWithId(idTest))).thenReturn(cadastroExpectedWithId(idTest));

    this.mockMvc.perform(
        put("/cadastro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonExpected)
            .accept(MediaType.APPLICATION_JSON)
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonExpected));
  }

  @Test
  void deleteTeste() throws Exception {
    String idTest = "0ybsc";

    doAnswer(invocation -> null)
        .when(cadastroService).delete(any(String.class));

    this.mockMvc.perform(delete("/cadastro/{id}", idTest))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}