package br.com.nois.sa.rc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Municipio;

@Repository
public interface MunicipioRepository extends MongoRepository<Municipio, String> {

	Municipio findById(String id);

	Municipio findByNome(String nome);

	List<Municipio> findByIdAgencia(String idAgencia);

	Municipio findByCodigo(String codigo);
}