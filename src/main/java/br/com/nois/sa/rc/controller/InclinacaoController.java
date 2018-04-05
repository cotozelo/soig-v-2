package br.com.nois.sa.rc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Inclinacao;

public interface InclinacaoController {
	public Long countByNome(@PathVariable("nome") String nome);
	public List<Inclinacao> getAll();
	public Inclinacao insert(@RequestBody Inclinacao inclinacao);
	public void insertTxt(@RequestBody ArrayList<String> itens);
	public int rotinaCarga(ArrayList<String> itens);
}
