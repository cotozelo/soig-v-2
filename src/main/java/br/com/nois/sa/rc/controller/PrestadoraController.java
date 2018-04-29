package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.BooleanJSON;
import br.com.nois.sa.rc.model.json.PrestadoraJSON;

public interface PrestadoraController {

	public BooleanJSON unicidadeNome(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("nome") String nome);

	public BooleanJSON unicidadeCodigo(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("codigo") String codigo);

	public ResponseEntity<Response<List<PrestadoraJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId);

	public ResponseEntity<Response<PrestadoraJSON>> insert(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @RequestBody PrestadoraJSON prestadoraJSON);

	public ResponseEntity<Response<PrestadoraJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @RequestBody PrestadoraJSON prestadoraJSON);

	public ResponseEntity<Response<PrestadoraJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId);
}
