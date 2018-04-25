package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.EixoJSON;

public interface EixoController {
	public Long countByNome(@PathVariable("nome") String nome);

	public ResponseEntity<Response<List<EixoJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<EixoJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome);

	public ResponseEntity<Response<EixoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody EixoJSON eixo);

	public ResponseEntity<Response<List<EixoJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<EixoJSON> eixos);
}
