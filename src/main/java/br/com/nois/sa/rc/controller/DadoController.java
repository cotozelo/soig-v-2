package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.DadoJSON;

public interface DadoController {

	public ResponseEntity<Response<List<DadoJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<DadoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody DadoJSON dadoJSON);

	public ResponseEntity<Response<DadoJSON>> update(@PathVariable("username") String userName,
			@RequestBody DadoJSON dadoJSON);

	public ResponseEntity<Response<DadoJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("dadoid") String dadoId);
}
