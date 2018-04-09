package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.UsuarioMunicipio;

public interface UsuarioMunicipioController {

	public List<UsuarioMunicipio> getAll(@PathVariable("idUsuario") String idUsuario);

	public UsuarioMunicipio getById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUEntidade") String idUEntidade);

	public UsuarioMunicipio insert(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioMunicipio usuarioEntidade);

	public UsuarioMunicipio update(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioMunicipio usuarioEntidade);

	public UsuarioMunicipio deleteById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUEntidade") String idUEntidade);
}
