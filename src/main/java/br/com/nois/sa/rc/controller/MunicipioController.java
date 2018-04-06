package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Municipio;

public interface MunicipioController {
	public List<Municipio> getAll();

	public List<Municipio> getByIdAgencia(@PathVariable("idAgencia") String idAgencia);

	public Municipio getByCodigo(@PathVariable("codigo") String codigo);
	
	public Municipio getByNome(@PathVariable("nome") String nome);

	public Municipio insert(@RequestBody Municipio municipio);

	public Municipio update(@RequestBody Municipio municipio);

	public Municipio deleteById(@PathVariable("idMunicipio") String idMunicipio);
}
