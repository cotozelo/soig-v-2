package br.com.nois.sa.rc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nois.sa.rc.model.Versao;

@Repository
public interface VersaoRepository extends MongoRepository<Versao, String> {

}