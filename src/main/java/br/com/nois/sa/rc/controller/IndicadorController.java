package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.to.IndicadorTO;

public interface IndicadorController {
	public List<IndicadorTO> getAll();

	public IndicadorTO getById(@PathVariable("id") String id);

	public IndicadorTO getBySigla(@PathVariable("sigla") String sigla);

	public IndicadorTO insert(@RequestBody IndicadorTO indicador);

	public IndicadorTO update(@RequestBody IndicadorTO indicador);

	public IndicadorTO deleteById(@PathVariable("idIndicador") String idIndicador);
}
