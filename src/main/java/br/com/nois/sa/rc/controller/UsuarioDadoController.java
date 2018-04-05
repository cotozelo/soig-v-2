package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.UsuarioDado;

public interface UsuarioDadoController {
	public List<UsuarioDado> getAll(@PathVariable("idUsuario") String idUsuario);

	public UsuarioDado getById(@PathVariable("idUsuario") String idUsuario, @PathVariable("idUDado") String idUDado);

	public UsuarioDado insert(@PathVariable("idUsuario") String idUsuario, @RequestBody UsuarioDado usuarioDado);

	public UsuarioDado update(@PathVariable("idUsuario") String idUsuario, @RequestBody UsuarioDado usuarioDado);

	public UsuarioDado deleteById(@PathVariable("idUsuario") String idUsuario, @PathVariable("idUDado") String idUDado);
}
