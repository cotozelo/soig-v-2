package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.nois.sa.rc.model.json.TipoDadoJSON;

public interface TipoDadoController {
	public ResponseEntity<Response<List<TipoDadoJSON>>> getAll(@PathVariable("username") String userName);

}
