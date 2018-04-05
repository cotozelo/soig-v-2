package br.com.nois.sa.rc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Grupo;

public interface GrupoController {
	public Long countByNome(@PathVariable("nome") String nome);
	public List<Grupo> getAll();
	public Grupo insert(@RequestBody Grupo grupo);
	public void insertTxt(@RequestBody ArrayList<String> itens);
	public int rotinaCarga(ArrayList<String> itens);
}
