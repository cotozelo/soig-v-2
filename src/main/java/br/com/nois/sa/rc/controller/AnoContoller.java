package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.AnoJSON;

public interface AnoContoller {
	ResponseEntity<Response<List<AnoJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId);

	public ResponseEntity<Response<AnoJSON>> insert(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@RequestBody AnoJSON anoJSON);

	public ResponseEntity<Response<AnoJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@RequestBody AnoJSON anoJSON);

	public ResponseEntity<Response<AnoJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@PathVariable("dadoId") String dadoId);
}
