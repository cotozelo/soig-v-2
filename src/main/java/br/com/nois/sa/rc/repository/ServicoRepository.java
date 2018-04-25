package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.ServicoTO;

@Repository
public interface ServicoRepository extends MongoRepository<ServicoTO, String> {
	ServicoTO findByNome(String nome);

	Long countByNome(String nome);
}