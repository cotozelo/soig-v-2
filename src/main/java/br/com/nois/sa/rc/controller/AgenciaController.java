package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Agencia;

public interface AgenciaController {

	public List<Agencia> getAll();

	public Agencia getById(@PathVariable("id") String id);

	public Agencia insert(@RequestBody Agencia agencia);

	public Agencia update(@RequestBody Agencia agencia);

	public Agencia deleteById(@PathVariable("id") String id);
}
