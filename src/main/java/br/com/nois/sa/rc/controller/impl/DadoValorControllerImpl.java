package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.DadoValorController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.AnoDadoValorJSON;
import br.com.nois.sa.rc.model.json.AnoDadoValoresJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.DadoValorTO;
import br.com.nois.sa.rc.model.to.MunicipioTO;
import br.com.nois.sa.rc.model.to.PrestadoraTO;
import br.com.nois.sa.rc.repository.DadoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/dadovalor")
public class DadoValorControllerImpl implements DadoValorController {
	private MunicipioRepository municipioRepository;
	// private DadoRepository dadoRepository;
	private LogRepository logRepository;
	// private List<DadoTO> dadosTO;

	@Autowired
	LogController logController;
	
	static Random gerador = new Random();

	public DadoValorControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			DadoRepository dadoRepository, VersaoRepository versaoRespository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		// this.dadoRepository = dadoRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRespository);
		// this.dadosTO = this.dadoRepository.findAll();
	}

	@Override
	@GetMapping("/listagem/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<List<AnoDadoValoresJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId) {
		Response<List<AnoDadoValoresJSON>> response = new Response<List<AnoDadoValoresJSON>>();
		try {

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

			List<AnoDadoValoresJSON> anosJSON = new ArrayList<AnoDadoValoresJSON>();
			for (AnoTO anoTO : anosTO) {
				AnoDadoValoresJSON anoDadoValorJSON = new AnoDadoValoresJSON(anoTO);
				anosJSON.add(anoDadoValorJSON);
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

	@PutMapping("/update/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<AnoDadoValorJSON>> update(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId, @RequestBody AnoDadoValorJSON anoDadoValorJSON) {

		Response<AnoDadoValorJSON> response = new Response<AnoDadoValorJSON>();
		try {
			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null || !municipioTO.getAgenciaId().equals(agenciaId)) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/update/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/update/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			AnoTO anoTO = prestadoraTO.getAno(anoDadoValorJSON.getAno());
			if (anoTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/update/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			for (DadoValorTO dadoValorTO : anoTO.getDadoValores()) {
				if (dadoValorTO.getSigla().equalsIgnoreCase(anoDadoValorJSON.getDadoValor().getSigla())) {
					dadoValorTO.update(anoDadoValorJSON.getDadoValor());
					
					if(dadoValorTO.getMes01() == null || dadoValorTO.getMes01().isEmpty()) {
						dadoValorTO.setMes01(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes02() == null || dadoValorTO.getMes02().isEmpty()) {
						dadoValorTO.setMes02(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes03() == null || dadoValorTO.getMes03().isEmpty()) {
						dadoValorTO.setMes03(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes04() == null || dadoValorTO.getMes04().isEmpty()) {
						dadoValorTO.setMes04(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes05() == null || dadoValorTO.getMes05().isEmpty()) {
						dadoValorTO.setMes05(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes06() == null || dadoValorTO.getMes06().isEmpty()) {
						dadoValorTO.setMes06(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes07() == null || dadoValorTO.getMes07().isEmpty()) {
						dadoValorTO.setMes07(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes08() == null || dadoValorTO.getMes08().isEmpty()) {
						dadoValorTO.setMes08(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes09() == null || dadoValorTO.getMes09().isEmpty()) {
						dadoValorTO.setMes09(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes10() == null || dadoValorTO.getMes10().isEmpty()) {
						dadoValorTO.setMes10(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes11() == null || dadoValorTO.getMes11().isEmpty()) {
						dadoValorTO.setMes11(String.valueOf(gerador.nextInt()));
					}
					if(dadoValorTO.getMes12() == null || dadoValorTO.getMes12().isEmpty()) {
						dadoValorTO.setMes12(String.valueOf(gerador.nextInt()));
					}
					
					this.municipioRepository.save(municipioTO);

					response.setData(anoDadoValorJSON);
					return ResponseEntity.status(HttpStatus.OK).body(response);
				}
			}
			response.setError(new ErroJSON("não achou dado", this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
