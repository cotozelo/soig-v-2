package br.com.nois.sa.rc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Agencia;

@Repository
public interface AgenciaRepository extends MongoRepository<Agencia, String> {

	Agencia findById(String id);

	Agencia findByNome(String nome);

	@Query(value = "{'ativo' : ?0}", fields = "{entidades : 0}")
	public List<Agencia> findAtivoReturnNoEntidadesQuery(Boolean ativo);
}