package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.FuncionalidadeJSON;

public interface FuncionalidadeController {
	public Long countByNome(@PathVariable("nome") String nome);

	public ResponseEntity<Response<List<FuncionalidadeJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<FuncionalidadeJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome);

	public ResponseEntity<Response<FuncionalidadeJSON>> insert(@PathVariable("username") String userName,
			@RequestBody FuncionalidadeJSON funcionalidade);

	public ResponseEntity<Response<List<FuncionalidadeJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<FuncionalidadeJSON> funcionalidades);
}
