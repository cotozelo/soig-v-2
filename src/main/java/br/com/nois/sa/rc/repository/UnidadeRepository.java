package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nois.sa.rc.model.Unidade;

public interface UnidadeRepository extends MongoRepository<Unidade, String> {
	Long countByNome(String nome);
}
