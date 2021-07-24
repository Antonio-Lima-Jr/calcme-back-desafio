package br.com.antonio.calcmeback.repository;

import br.com.antonio.calcmeback.model.Cadastro;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository ou Data Access Objects que disponibiliza query's ja implementadas pela API do Spring,
 * e facilita a criação de novas query's.
 *
 * @author Antônio Lima Jr
 */
public interface CadastroRepository extends MongoRepository<Cadastro, String> {
}

