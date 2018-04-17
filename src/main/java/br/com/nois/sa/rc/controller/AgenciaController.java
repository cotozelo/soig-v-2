package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.AgenciaJSON;

public interface AgenciaController {

	public ResponseEntity<Response<List<AgenciaJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<AgenciaJSON>> insert(@PathVariable("username") String userName,
			@RequestBody AgenciaJSON agencia);

	public ResponseEntity<Response<AgenciaJSON>> update(@PathVariable("username") String userName,
			@RequestBody AgenciaJSON agencia);

	public ResponseEntity<Response<AgenciaJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("id") String id);
}
