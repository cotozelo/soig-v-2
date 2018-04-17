package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.IndicadorTO;

@Repository
public interface IndicadorRepository extends MongoRepository<IndicadorTO, String> {
	IndicadorTO findById(String id);

	IndicadorTO findBySigla(String sigla);

}
