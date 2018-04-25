package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.AbrangenciaJSON;

public interface AbrangenciaController {
	public Long countByNome(@PathVariable("nome") String nome);

	public ResponseEntity<Response<List<AbrangenciaJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<AbrangenciaJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome);

	public ResponseEntity<Response<AbrangenciaJSON>> insert(@PathVariable("username") String userName,
			@RequestBody AbrangenciaJSON abrangencia);

	public ResponseEntity<Response<List<AbrangenciaJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<AbrangenciaJSON> abrangencias);
}
