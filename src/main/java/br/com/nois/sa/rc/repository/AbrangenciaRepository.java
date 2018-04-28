package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.AbrangenciaTO;

@Repository
public interface AbrangenciaRepository extends MongoRepository<AbrangenciaTO, String> {
	AbrangenciaTO findByNome(String nome);

	Long countByNome(String nome);
}
