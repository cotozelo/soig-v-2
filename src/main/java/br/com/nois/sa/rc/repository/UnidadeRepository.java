package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nois.sa.rc.model.to.UnidadeTO;

public interface UnidadeRepository extends MongoRepository<UnidadeTO, String> {
	Long countByNome(String nome);
}
