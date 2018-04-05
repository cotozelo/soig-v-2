package br.com.nois.sa.rc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Log;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
	Log findById(String id);

	Log findByVersaoGlobal(long versaoGlobal);

	List<Log> findAll();

}
