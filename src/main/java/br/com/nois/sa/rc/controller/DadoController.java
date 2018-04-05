package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Dado;

public interface DadoController {

	public List<Dado> getAll();

	public Dado getById(@PathVariable("id") String id);

	public Dado insert(@RequestBody Dado dado);

	public Dado update(@RequestBody Dado dado);

	public Dado deleteById(@PathVariable("id") String id);
}
