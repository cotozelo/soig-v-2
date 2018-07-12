package br.com.nois.sa.rc.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.EquacaoJSON;

public interface EquacaoController {
	public ResponseEntity<Response<Map<String, Object>>> getAll(@PathVariable("idIndicador") String idIndicador);
	public ResponseEntity<Response<EquacaoJSON>> update(@PathVariable("userName") String userName, @RequestBody EquacaoJSON equacaoJSON);
}
