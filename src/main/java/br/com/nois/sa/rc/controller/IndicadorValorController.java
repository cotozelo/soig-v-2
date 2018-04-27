package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.nois.sa.rc.model.json.AnoIndicadorValorJSON;

public interface IndicadorValorController {
	public ResponseEntity<Response<List<AnoIndicadorValorJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId);

}
