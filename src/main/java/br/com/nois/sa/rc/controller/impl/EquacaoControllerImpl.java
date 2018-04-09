package br.com.nois.sa.rc.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.EquacaoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Equacao;
import br.com.nois.sa.rc.model.Indicador;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/equacao")
public class EquacaoControllerImpl implements EquacaoController {
	private IndicadorRepository indicadorRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public EquacaoControllerImpl(IndicadorRepository indicadorRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.indicadorRepository = indicadorRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all/{idIndicador}")
	public List<Equacao> getAll(@PathVariable("idIndicador") String idIndicador) {
		Indicador indicador = this.indicadorRepository.findById(idIndicador);
		if (indicador == null) {
			String error = "Erro: CxExRx00024 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().EQUACAO_GETALL, error));
			return (List<Equacao>) new Equacao(error);
		}
		List<Equacao> equacoes = indicador.getEquacoes();
		for (Equacao equacao : equacoes) {
			if (equacao.getAtiva() == false) {
				equacoes.remove(equacao);
			}
		}

		this.logController
				.insert(new Log(new Constantes().EQUACAO_GETALL, equacoes == null ? "" : equacoes.toString()));

		return equacoes;
	}

	@GetMapping("/id/{idIndicador}/{idEquacao}")
	public Equacao getById(@PathVariable("idIndicador") String idIndicador,
			@PathVariable("idEquacao") String idEquacao) {
		Indicador indicador = this.indicadorRepository.findById(idIndicador);
		if (indicador == null) {
			String error = "Erro: CxExRx00001 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().EQUACAO_GETBYID, error));
			return new Equacao(error);
		}
		Equacao equacao = indicador.getEquacao(idEquacao);
		if (equacao == null) {
			String error = "Erro: CxExRx00002 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().EQUACAO_GETBYID, error));
			return new Equacao(error);
		}
		this.logController.insert(new Log(new Constantes().EQUACAO_GETBYID, equacao.toString()));
		return equacao;
	}

	@PutMapping("/insert/{idIndicador}")
	public Equacao insert(@PathVariable("idIndicador") String idIndicador, @RequestBody Equacao equacao) {
		Indicador indicador = this.indicadorRepository.findById(idIndicador);
		if (indicador == null) {
			String error = "Erro: CxExCx00003 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().EQUACAO_INSERT, error));
			return new Equacao(error);
		}
		if (equacao.getId() == null) {
			equacao.setId();
		}
		long versaoGlobal = this.logController.getVersaoGlogal();
		equacao.setVersaoGlobal(versaoGlobal);
		equacao = indicador.setEquacao(equacao);
		if (equacao == null) {
			String error = "Erro: CxExCx00004 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().EQUACAO_INSERT, error));
			return new Equacao(error);
		}
		this.logController.insert(new Log(new Constantes().EQUACAO_INSERT, equacao.toString(), versaoGlobal));

		indicador = this.indicadorRepository.save(indicador);
		return equacao;
	}

	@PostMapping("/update/{idIndicador}")
	public Equacao update(@PathVariable("idIndicador") String idIndicador, @RequestBody Equacao equacao) {

		try {
			Indicador indicador = this.indicadorRepository.findById(idIndicador);
			if (indicador == null) {
				String error = "Erro: CxExUx00005 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().EQUACAO_UPDATE, error));
				return new Equacao(error);
			}
			long versaoGlobal = this.logController.getVersaoGlogal();
			equacao.setVersaoGlobal(versaoGlobal);

			equacao = indicador.setEquacao(equacao);
			if (equacao == null) {
				String error = "Erro: CxExUx00006 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().EQUACAO_UPDATE, error));
				return new Equacao(error);
			}

			this.logController.insert(new Log(new Constantes().EQUACAO_UPDATE, equacao.toString(), versaoGlobal));
			indicador = this.indicadorRepository.save(indicador);

			return equacao;
		} catch (Exception e) {
			String error = "Erro: CxExUx00007 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().EQUACAO_UPDATE, error + e.getMessage()));
			return new Equacao(error);
		}
	}

	@DeleteMapping("/delete/{idIndicador}/{idEquacao}")
	public Equacao deleteById(@PathVariable("idIndicador") String idIndicador,
			@PathVariable("idEquacao") String idEquacao) {
		try {
			Indicador indicador = this.indicadorRepository.findById(idIndicador);
			if (indicador == null) {
				String error = "Erro: CxExDx00008 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().EQUACAO_DELETEBYID, error));
				return new Equacao(error);
			}
			Equacao equacao = indicador.getEquacao(idEquacao);
			if (equacao == null) {
				String error = "Erro: CxExDx00009 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().EQUACAO_DELETEBYID, error));
				return new Equacao(error);
			}

			equacao.setAtiva(false);
			indicador.setEquacao(equacao);

			this.logController.insert(new Log(new Constantes().EQUACAO_DELETEBYID, equacao.toString()));
			this.indicadorRepository.save(indicador);

			return equacao;
		} catch (Exception e) {
			String error = "Erro: CxExDx00010 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().EQUACAO_DELETEBYID, error + e.getMessage()));
			return new Equacao(error);
		}
	}

	/*
	 * public Double result(Map<String, Double> variaveis, String formula)
	 * throws ScriptException { ScriptEngineManager manager = new
	 * ScriptEngineManager(); ScriptEngine engine =
	 * manager.getEngineByName("JavaScript");
	 * 
	 * for (String key : variaveis.keySet()) { engine.put(key,
	 * variaveis.get(key)); }
	 * 
	 * engine.eval("resultado = " + formula + ";");
	 * 
	 * return Double.parseDouble("" + engine.get("resultado")); }
	 */
}
