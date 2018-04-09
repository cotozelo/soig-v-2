package br.com.nois.sa.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nois.sa.rc.model.Equacao;

public interface EquacaoController {
	public List<Equacao> getAll(@PathVariable("idIndicador") String idIndicador);

	public Equacao getById(@PathVariable("idIndicador") String idIndicador,
			@PathVariable("idEquacao") String idEquacao);

	public Equacao insert(@PathVariable("idIndicador") String idIndicador, @RequestBody Equacao equacao);

	public Equacao update(@PathVariable("idIndicador") String idIndicador, @RequestBody Equacao equacao);

	public Equacao deleteById(@PathVariable("idIndicador") String idIndicador,
			@PathVariable("idEquacao") String idEquacao);

	/*
	 * public Double result(MunicipioRepository municipioRepository,
	 * DadoRepository dadoRepository, LogRepository logRepository,
	 * VersaoRepository versaoRepository, String formula) throws
	 * ScriptException;
	 */
}
