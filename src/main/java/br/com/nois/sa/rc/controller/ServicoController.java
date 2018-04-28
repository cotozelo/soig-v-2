package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.ServicoJSON;

public interface ServicoController {
	public Long countByNome(@PathVariable("nome") String nome);

	public ResponseEntity<Response<List<ServicoJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<ServicoJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome);

	public ResponseEntity<Response<ServicoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody ServicoJSON servico);

	public ResponseEntity<Response<List<ServicoJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<ServicoJSON> servicos);
}
