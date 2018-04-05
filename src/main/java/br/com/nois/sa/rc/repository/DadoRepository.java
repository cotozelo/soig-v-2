package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Dado;

@Repository
public interface DadoRepository extends MongoRepository<Dado, String> {
	Dado findById(String id);

	Dado findBySigla(String sigla);

}
