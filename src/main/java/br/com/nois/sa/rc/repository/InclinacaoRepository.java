package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nois.sa.rc.model.Inclinacao;

public interface InclinacaoRepository extends MongoRepository<Inclinacao, String> {
	Long countByNome(String nome);
}
