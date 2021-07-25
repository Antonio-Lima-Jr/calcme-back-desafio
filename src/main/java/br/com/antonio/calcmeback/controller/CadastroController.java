package br.com.antonio.calcmeback.controller;

import br.com.antonio.calcmeback.model.Cadastro;
import br.com.antonio.calcmeback.service.impl.CadastroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador responsável por receber as requisições e configurações de rotas
 * e encaminhar para a camada (service) regras de negócio .
 *
 * @author Antônio Lima Jr
 */
@RestController
@RequestMapping("/cadastro")
public class CadastroController {

  @Autowired
  private CadastroServiceImpl cadastroService;

  @GetMapping
  public ResponseEntity<List<Cadastro>> listAll() {
    return ResponseEntity.ok(this.cadastroService.listAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cadastro> getById(@PathVariable String id) {
    return ResponseEntity.ok(this.cadastroService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Cadastro> create(@Valid @RequestBody Cadastro cadastro) {
    return new ResponseEntity<>(
        this.cadastroService.create(cadastro),
        HttpStatus.CREATED
    );
  }

  @PutMapping
  public ResponseEntity<Cadastro> put(@Valid @RequestBody Cadastro cadastro) {
    return ResponseEntity.ok(this.cadastroService.put(cadastro));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    this.cadastroService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}