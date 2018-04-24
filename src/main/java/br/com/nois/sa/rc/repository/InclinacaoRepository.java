package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nois.sa.rc.model.to.InclinacaoTO;


public interface InclinacaoRepository extends MongoRepository<InclinacaoTO, String> {
	Long countByNome(String nome);
}
