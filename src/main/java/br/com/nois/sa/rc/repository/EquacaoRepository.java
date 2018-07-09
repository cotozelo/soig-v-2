package br.com.nois.sa.rc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.to.EquacaoTO;

@Repository
public interface EquacaoRepository extends MongoRepository<EquacaoTO, String> {
	List<EquacaoTO> findByFormulaLike(String formula);
}
