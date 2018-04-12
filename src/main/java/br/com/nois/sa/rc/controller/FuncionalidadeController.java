package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.FuncionalidadeJSON;

public interface FuncionalidadeController {
	public Long countByNome(@PathVariable("nome") String nome);

	public Object getAll(@PathVariable("username") String userName);

	public Object getByNome(@PathVariable("username") String userName, @PathVariable("nome") String nome);

	public Object insert(@PathVariable("username") String userName, @RequestBody FuncionalidadeJSON funcionalidade);

	public Object inserts(@PathVariable("username") String userName,
			@RequestBody List<FuncionalidadeJSON> funcionalidades);
}
