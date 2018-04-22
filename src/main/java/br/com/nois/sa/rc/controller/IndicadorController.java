package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.IndicadorJSON;

public interface IndicadorController {
	public ResponseEntity<Response<List<IndicadorJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<IndicadorJSON>> insert(@PathVariable("username") String userName,
			@RequestBody IndicadorJSON indicadorJSON);

	public ResponseEntity<Response<IndicadorJSON>> update(@PathVariable("username") String userName,
			@RequestBody IndicadorJSON indicadorJSON);

	public ResponseEntity<Response<IndicadorJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("indicadorid") String indicadorId);

	public ResponseEntity<Response<String>> clonar(@PathVariable("username") String userName,
			@PathVariable("anoorigem") String anoOrigem, @PathVariable("anodestino") String anoDestino);
}
