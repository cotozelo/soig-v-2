package br.com.nois.sa.rc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.UsuarioJSON;

public interface UsuarioController {

	public ResponseEntity<Response<UsuarioJSON>> insert(@PathVariable("username") String username,
			@RequestBody UsuarioJSON usuarioJSON);

	public ResponseEntity<Response<UsuarioJSON>> getInformacao(@PathVariable("username") String username);

	/*
	 * public List<UsuarioJSON> getAll();
	 * 
	 * public UsuarioJSON getById(@PathVariable("id") String id);
	 * 
	 * public UsuarioJSON getByNomeDeUsuario(@PathVariable("nomeDeUsuario")
	 * String nomeDeUsuario);
	 * 
	 * public UsuarioJSON update(@RequestBody UsuarioJSON usuario);
	 * 
	 * public UsuarioJSON deleteById(@PathVariable("id") String id);
	 */
}
