package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.PerfilTO;

@Repository
public interface PerfilRepository extends MongoRepository<PerfilTO, String> {
	PerfilTO findById(String id);

	PerfilTO findByNome(String nome);

}
