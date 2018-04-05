package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nois.sa.rc.model.Grupo;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
	Long countByNome(String nome);
}
