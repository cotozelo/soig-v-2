package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Usuario;

public interface UsuarioController {
	public List<Usuario> getAll();

	public Usuario getById(@PathVariable("id") String id);

	public Usuario insert(@RequestBody Usuario usuario);

	public Usuario update(@RequestBody Usuario usuario);

	public Usuario deleteById(@PathVariable("id") String id);
}
