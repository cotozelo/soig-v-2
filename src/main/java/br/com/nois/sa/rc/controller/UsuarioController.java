package br.com.nois.sa.rc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.UsuarioJSON;

public interface UsuarioController {

	public Object insert(@PathVariable("username") String username, @RequestBody UsuarioJSON usuarioJSON);

	public Object getInformacao(@PathVariable("username") String username);

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
