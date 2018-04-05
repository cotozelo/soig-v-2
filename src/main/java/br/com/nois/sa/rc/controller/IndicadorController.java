package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Indicador;

public interface IndicadorController {
	public List<Indicador> getAll();

	public Indicador getById(@PathVariable("id") String id);

	public Indicador getBySigla(@PathVariable("sigla") String sigla);

	public Indicador insert(@RequestBody Indicador indicador);

	public Indicador update(@RequestBody Indicador indicador);

	public Indicador deleteById(@PathVariable("idIndicador") String idIndicador);
}
