package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.UsuarioEntidade;

public interface UsuarioEntidadeController {

	public List<UsuarioEntidade> getAll(@PathVariable("idUsuario") String idUsuario);

	public UsuarioEntidade getById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUEntidade") String idUEntidade);

	public UsuarioEntidade insert(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioEntidade usuarioEntidade);

	public UsuarioEntidade update(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioEntidade usuarioEntidade);

	public UsuarioEntidade deleteById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUEntidade") String idUEntidade);
}
