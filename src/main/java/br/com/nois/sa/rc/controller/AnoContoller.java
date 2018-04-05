package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Ano;

public interface AnoContoller {
	public List<Ano> getAll(@PathVariable("idEntidade") String idEntidade,
			@PathVariable("idPrestadora") String idPrestadora);

	public Ano getId(@PathVariable("idEntidade") String idEntidade, @PathVariable("idPrestadora") String idPrestadora,
			@PathVariable("idAno") String idAno);

	public Ano insert(@PathVariable("idEntidade") String idEntidade, @PathVariable("idPrestadora") String idPrestadora,
			@RequestBody Ano ano);

	public Ano update(@PathVariable("idEntidade") String idEntidade, @PathVariable("idPrestadora") String idPrestadora,
			@RequestBody Ano ano);

	public Ano deleteById(@PathVariable("idEntidade") String idEntidade,
			@PathVariable("idPrestadora") String idPrestadora, @PathVariable("idAno") String idAno);
}
