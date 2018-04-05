package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Prestadora;

public interface PrestadoraController {
	public List<Prestadora> getAll(@PathVariable("idMunicipio") String idMunicipio);

	public Prestadora getIdCodigo(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("codigoPrestadora") String codigoPrestadora);

	public Prestadora getCodigoCodigo(@PathVariable("codigoMunicipio") String codigoMunicipio,
			@PathVariable("codigoPrestadora") String codigoPrestadora);

	public Prestadora insert(@PathVariable("idMunicipio") String idMunicipio, @RequestBody Prestadora prestadora);

	public Prestadora update(@PathVariable("idMunicipio") String idMunicipio, @RequestBody Prestadora prestadora);

	public Prestadora deleteById(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("idPrestadora") String idPrestadora);
}
