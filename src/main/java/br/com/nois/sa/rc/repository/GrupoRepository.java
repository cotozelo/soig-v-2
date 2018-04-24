package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nois.sa.rc.model.to.GrupoTO;

public interface GrupoRepository extends MongoRepository<GrupoTO, String> {
	Long countByNome(String nome);
}
