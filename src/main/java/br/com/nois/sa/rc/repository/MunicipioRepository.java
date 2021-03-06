package br.com.nois.sa.rc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.MunicipioTO;

@Repository
public interface MunicipioRepository extends MongoRepository<MunicipioTO, String> {

	// @Query(fields = "{ 'nome': 1, 'agenciaId': 1, 'codigo': 1, 'cidade': 1,
	// 'estado': 1, 'contatoTelefone': 1, 'contatoNome': 1, 'contatoEmail': 1,
	// 'ativo':1}")

	List<MunicipioTO> findAll();
	
	@Query(fields = "{ 'nome': 1, 'agenciaId': 1}")
	List<MunicipioTO> findByAtivo(boolean ativo);
	
	MunicipioTO findById(String id);

	MunicipioTO findByIdAndAgenciaIdAndAtivo(String id, String agenciaId, boolean ativo);
	
	MunicipioTO findByNome(String nome);

	@Query(fields = "{ 'nome': 1, 'agenciaId': 1, 'codigo': 1, 'cidade': 1, 'estado': 1, 'contatoTelefone': 1, 'contatoNome': 1, 'contatoEmail': 1, 'ativo':1}")
	List<MunicipioTO> findByAgenciaId(String agenciaId);	
	
	@Query(value = "{'agenciaId' : ?0, 'ativo' : ?1}",fields = "{ 'nome': 1, 'agenciaId': 1, 'codigo': 1, 'cidade': 1, 'estado': 1, 'contatoTelefone': 1, 'contatoNome': 1, 'contatoEmail': 1, 'ativo':1, 'prestadoras':1}")
	List<MunicipioTO> findByAgenciaIdAndAtivo(String agenciaId, boolean ativo);

	@Query(fields = "{ 'nome': 1, 'agenciaId': 1, 'codigo': 1, 'cidade': 1, 'estado': 1, 'contatoTelefone': 1, 'contatoNome': 1, 'contatoEmail': 1, 'ativo':1}")
	MunicipioTO findByIdAndAgenciaId(String municipioId, String agenciaId);

	MunicipioTO findByCodigo(String codigo);

	@Query(fields = "{ 'nome': 1, 'ativo':1}")
	MunicipioTO findByNomeStartingWithIgnoreCase(String nome);

	@Query(fields = "{ 'nome': 1, 'codigo':1,  'ativo':1}")
	MunicipioTO findByCodigoStartingWithIgnoreCase(String codigo);
}