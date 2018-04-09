package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.json.FuncionalidadeJSON;

public interface FuncionalidadeController {
	public Long countByNome(@PathVariable("nome") String nome);

	public List<FuncionalidadeJSON> getAll();

	public FuncionalidadeJSON getByNome(@PathVariable("nome") String nome);

	public FuncionalidadeJSON insert(@RequestBody FuncionalidadeJSON funcionalidade);

	public List<FuncionalidadeJSON> inserts(@RequestBody List<FuncionalidadeJSON> funcionalidades);
}
