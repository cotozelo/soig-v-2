package br.com.nois.sa.rc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.InclinacaoJSON;

public interface InclinacaoController {
	public Long countByNome(@PathVariable("nome") String nome);

	public void insertTxt(@RequestBody ArrayList<String> itens);

	public int rotinaCarga(ArrayList<String> itens);

	public ResponseEntity<Response<List<InclinacaoJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<InclinacaoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody InclinacaoJSON InclinacaoJSON);

}
