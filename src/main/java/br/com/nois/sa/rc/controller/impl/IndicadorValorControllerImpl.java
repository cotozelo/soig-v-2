package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.IndicadorValorController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.AnoIndicadorValoresJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.IndicadorTO;
import br.com.nois.sa.rc.model.to.IndicadorValorTO;
import br.com.nois.sa.rc.model.to.MunicipioTO;
import br.com.nois.sa.rc.model.to.PrestadoraTO;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/indicadorvalor")
public class IndicadorValorControllerImpl implements IndicadorValorController {
	private MunicipioRepository municipioRepository;
	private IndicadorRepository indicadorRepository;
	private LogRepository logRepository;
	private List<IndicadorTO> indicadoresTO;

	@Autowired
	LogController logController;

	public IndicadorValorControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			IndicadorRepository indicadorRepository, VersaoRepository versaoRespository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.indicadorRepository = indicadorRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRespository);
	}

	@Override
	@GetMapping("/listagem/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<List<AnoIndicadorValoresJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId) {

		Response<List<AnoIndicadorValoresJSON>> response = new Response<List<AnoIndicadorValoresJSON>>();
		try {
			this.indicadoresTO = this.indicadorRepository.findAll();
			if (this.indicadoresTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			Map<String, String> inclinacaoNome = new HashMap<String, String>();
			Map<String, String> inclinacaoId = new HashMap<String, String>();
			for (IndicadorTO indicadorTO : this.indicadoresTO) {
				inclinacaoNome.put(indicadorTO.getSigla(), indicadorTO.getInclinacaoNome());
				inclinacaoId.put(indicadorTO.getSigla(), indicadorTO.getInclinacaoId());
			}

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null || !municipioTO.getAgenciaId().equals(agenciaId)) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			List<AnoTO> anosTO = prestadoraTO.getAnos();
			if (anosTO == null || anosTO.isEmpty()) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			List<AnoIndicadorValoresJSON> anosJSON = new ArrayList<AnoIndicadorValoresJSON>();
			for (AnoTO anoTO : anosTO) {
				if (anoTO.getIndicadorValores() != null) {
					AnoIndicadorValoresJSON anoIndicadorValorJSON = new AnoIndicadorValoresJSON(anoTO);
					anoIndicadorValorJSON.setInclinacaoNome(inclinacaoNome);
					anoIndicadorValorJSON.setInclinacaoId(inclinacaoId);
					anosJSON.add(anoIndicadorValorJSON);
				}
			}

			this.logController.insert(new Log(Constantes.PRESTADORA_LISTAGEM,
					new Util().ListColectionToString(new ArrayList<Object>(anosJSON))));

			response.setData(anosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/lisgatem/" + userName + "/" + agenciaId
					+ "/" + municipioId + "/" + prestadoraId));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	public String getResultado(Map<String, Double> variaveis, String formula) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		for (String key : variaveis.keySet()) {
			engine.put(key, variaveis.get(key));
		}

		engine.eval("resultado = " + formula);
		return "" + engine.get("resultado");
	}

	public Boolean updateByDado(String agenciaId, String municipioId, String prestadoraId, String ano, String mes,
			Map<String, String> equacoes, Map<String, Double> variaveis) throws ScriptException {

		MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);
		if (municipioTO == null || !municipioTO.getAgenciaId().equals(agenciaId)) {
			System.out.println("municipio");
			return false;
		}

		PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
		if (prestadoraTO == null) {
			System.out.println("prestadora");
			return false;
		}

		AnoTO anoTO = prestadoraTO.getAno(ano);
		if (anoTO == null) {
			System.out.println("ano");
			return false;
		}
		
		try {
			List<IndicadorValorTO> novos = new ArrayList<IndicadorValorTO>();
			for (IndicadorValorTO indicadorValor : anoTO.getIndicadorValores()) {
				if (equacoes.containsKey(indicadorValor.getSigla())) {
					indicadorValor.setMes(mes, getResultado(variaveis, equacoes.get(indicadorValor.getSigla())));
				}
				novos.add(indicadorValor);
			}
			anoTO.setIndicadorValores(novos);
			prestadoraTO.setAno(anoTO);
			municipioTO.setPrestadora(prestadoraTO);
			this.municipioRepository.save(municipioTO);
			return true;
		} catch (Exception ex) {
			System.out.println("antes calculo");
			return false;
		}
	}
}