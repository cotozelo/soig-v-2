package br.com.nois.sa.rc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.UnidadeJSON;

public interface UnidadeController {
	public Long countByNome(@PathVariable("nome") String nome);

	public void insertTxt(@RequestBody ArrayList<String> itens);

	public int rotinaCarga(ArrayList<String> itens);

	public ResponseEntity<Response<List<UnidadeJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<UnidadeJSON>> insert(@PathVariable("username") String userName,
			@RequestBody UnidadeJSON unidadeJSON);

}
