package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.EixoTO;

@Repository
public interface EixoRepository extends MongoRepository<EixoTO, String> {
	EixoTO findByNome(String nome);

	Long countByNome(String nome);
}