package br.com.nois.sa.rc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.AgenciaTO;

@Repository
public interface AgenciaRepository extends MongoRepository<AgenciaTO, String> {

	AgenciaTO findById(String id);

	AgenciaTO findByNome(String nome);

	@Query(value = "{'ativo' : ?0}", fields = "{entidades : 0}")
	public List<AgenciaTO> findAtivoReturnNoEntidadesQuery(Boolean ativo);

	public List<AgenciaTO> findByNomeStartingWithIgnoreCase(String nome);
}