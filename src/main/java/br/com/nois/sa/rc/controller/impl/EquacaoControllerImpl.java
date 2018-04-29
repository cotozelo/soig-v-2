package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.EquacaoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.EquacaoJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.EquacaoTO;
import br.com.nois.sa.rc.model.to.IndicadorTO;
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
	@GetMapping("/listagem/{userName}")
	public ResponseEntity<Response<Map<String, Object>>> getAll(@PathVariable("userName") String userName) {
		Response<Map<String, Object>> response = new Response<Map<String, Object>>();

		List<IndicadorTO> indicadoresTO = this.indicadorRepository.findAll();
		if (indicadoresTO == null || indicadoresTO.isEmpty()) {
			response.setError(new ErroJSON("VxExRx00001", this.getClass().getName() + "/listagem/" + userName));
			response.setData(new HashMap<>());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}

		Map<String, Object> mEquacaoJSON = new HashMap<>();

		for (IndicadorTO indicadorTO : indicadoresTO) {
			if (indicadorTO.getEquacaoAtiva() != null && !indicadorTO.getEquacaoAtiva().isEmpty()) {
				for (EquacaoTO equacaoTO : indicadorTO.getEquacaoAtiva()) {

					EquacaoJSON equacao = new EquacaoJSON();
					equacao.setAno(equacaoTO.getAno());
					equacao.setEquacaoId(equacaoTO.getId());
					equacao.setFormula(equacaoTO.getFormula());
					equacao.setIndicadorId(indicadorTO.getId());
					equacao.setIndicadorSigla(indicadorTO.getSigla());

					if (mEquacaoJSON.containsKey(equacaoTO.getAno())) {
						((List<EquacaoJSON>) mEquacaoJSON.get(equacaoTO.getAno())).add((EquacaoJSON) equacao);
					} else {
						List<EquacaoJSON> equacoesJSON = new ArrayList<EquacaoJSON>();
						equacoesJSON.add(equacao);
						mEquacaoJSON.put(equacaoTO.getAno(), equacoesJSON);
					}
				}
			}
		}

		response.setData(mEquacaoJSON);
		// this.logController.insert(new Log(Constantes.EQUACAO_GETALL,
		// equacoesJSON.toString()));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/{userName}")
	public ResponseEntity<Response<EquacaoJSON>> update(@PathVariable("userName") String userName,
			@RequestBody EquacaoJSON equacaoJSON) {

		Response<EquacaoJSON> response = new Response<EquacaoJSON>();
		try {
			IndicadorTO indicador = this.indicadorRepository.findById(equacaoJSON.getIndicadorId());
			if (indicador == null) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			long versaoGlobal = this.logController.getVersaoGlogal();

			EquacaoTO equacaoTO = new EquacaoTO();
			equacaoTO.setPaiId(equacaoJSON.getEquacaoId());
			equacaoTO.setAtiva(true);
			equacaoTO.setFormula(equacaoJSON.getFormula().toUpperCase());
			equacaoTO.setVersaoGlobal(versaoGlobal);
			equacaoTO.setId();
			equacaoTO.setAno(equacaoJSON.getAno());

			for (EquacaoTO equacao : indicador.getEquacaoAtiva()) {
				// indicador.getEquacaoAtiva().setAtiva(false);
				if (equacao.getAno().equals(equacaoJSON.getAno())) {
					equacao.setAtiva(false);
				}
			}
			indicador.setEquacao(equacaoTO);

			this.logController.insert(new Log(Constantes.EQUACAO_UPDATE, equacaoJSON.toString(), versaoGlobal));
			indicador = this.indicadorRepository.save(indicador);

			response.setData(equacaoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
