package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.DadoTO;

@Repository
public interface DadoRepository extends MongoRepository<DadoTO, String> {

	DadoTO findById(String id);

	DadoTO findBySigla(String sigla);

}
