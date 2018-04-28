package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.AnoListagem;
import br.com.nois.sa.rc.model.json.AnoIndicadorValoresJSON;

public interface AnoContoller {
	ResponseEntity<Response<List<AnoListagem>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraIdd);

	public ResponseEntity<Response<AnoIndicadorValoresJSON>> insert(@PathVariable("username") String userName,
			@RequestBody AnoIndicadorValoresJSON anoJSON);

	public ResponseEntity<Response<AnoIndicadorValoresJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@RequestBody AnoIndicadorValoresJSON anoJSON);

	public ResponseEntity<Response<AnoIndicadorValoresJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@PathVariable("dadoId") String dadoId);
}
