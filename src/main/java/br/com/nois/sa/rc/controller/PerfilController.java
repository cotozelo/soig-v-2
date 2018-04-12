package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.PerfilJSON;

public interface PerfilController {
	public Object getAll(@PathVariable("username") String userName);

	public Object update(@PathVariable("username") String userName, @RequestBody List<PerfilJSON> perfilsJSON);

	public Object insert(@PathVariable("username") String userName, @RequestBody PerfilJSON perfil);

	public Object deleteById(@PathVariable("username") String userName, @PathVariable("id") String id);

}
