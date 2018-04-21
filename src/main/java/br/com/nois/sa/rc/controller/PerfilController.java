package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.PerfilJSON;

public interface PerfilController {
	public ResponseEntity<Response<List<PerfilJSON>>> getAll(@PathVariable("username") String userName);

	public ResponseEntity<Response<List<PerfilJSON>>> update(@PathVariable("username") String userName,
			@RequestBody List<PerfilJSON> perfilsJSON);

	public ResponseEntity<Response<PerfilJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("id") String id);

}
