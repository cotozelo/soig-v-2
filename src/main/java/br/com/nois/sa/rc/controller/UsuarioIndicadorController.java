package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.UsuarioIndicador;

public interface UsuarioIndicadorController {

	public List<UsuarioIndicador> getAll(@PathVariable("idUsuario") String idUsuario);

	public UsuarioIndicador getById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUIndicador") String idUIndicador);

	public UsuarioIndicador insert(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioIndicador usuarioIndicador);

	public UsuarioIndicador update(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioIndicador usuarioIndicador);

	public UsuarioIndicador deleteById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUIndicador") String idUIndicador);
}
