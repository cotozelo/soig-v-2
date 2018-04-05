package br.com.nois.sa.rc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Funcionalidade;

public interface FuncionalidadeController {
	public Long countByNome(@PathVariable("nome") String nome);
	public List<Funcionalidade> getAll();
	public Funcionalidade getByNome(@PathVariable("nome") String nome);
	public Funcionalidade insert(@RequestBody Funcionalidade funcionalidade);
	public List<Funcionalidade> inserts(@RequestBody List<Funcionalidade> funcionalidades);
	public void insertTxt(@RequestBody ArrayList<String> itens);
	public int rotinaCarga(ArrayList<String> itens);
}
