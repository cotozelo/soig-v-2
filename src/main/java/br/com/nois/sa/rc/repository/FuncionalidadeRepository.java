package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Funcionalidade;

@Repository
public interface FuncionalidadeRepository extends MongoRepository<Funcionalidade, String> {
	Funcionalidade findByNome(String nome);
	Long countByNome(String nome);
}