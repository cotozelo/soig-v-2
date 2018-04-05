package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Indicador;

@Repository
public interface IndicadorRepository extends MongoRepository<Indicador, String> {
	Indicador findById(String id);

	Indicador findBySigla(String sigla);

}
