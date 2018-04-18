package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.AnoContoller;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.AnoJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.DadoTO;
import br.com.nois.sa.rc.model.to.IndicadorTO;
import br.com.nois.sa.rc.model.to.MunicipioTO;
import br.com.nois.sa.rc.model.to.PrestadoraTO;
import br.com.nois.sa.rc.repository.DadoRepository;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/ano")
public class AnoControllerImpl implements AnoContoller {
	private MunicipioRepository municipioRepository;
	private LogRepository logRepository;
	private DadoRepository dadoRepository;
	private IndicadorRepository indicadorRepository;

	@Autowired
	LogController logController;

	public AnoControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			DadoRepository dadoRepository, IndicadorRepository indicadorRepository, VersaoRepository versaoRepository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.dadoRepository = dadoRepository;
		this.indicadorRepository = indicadorRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/listagem/{username}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<List<AnoJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId) {

		Response<List<AnoJSON>> response = new Response<List<AnoJSON>>();
		MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

		if (municipioTO == null) {
			response.setError(new ErroJSON("VxPxRx00001",
					this.getClass().getName() + "/listagem/" + userName + "/" + municipioId + "/" + prestadoraId));
			response.setData(new ArrayList<>());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
		if (prestadoraTO == null) {
			response.setError(new ErroJSON("VxPxRx00002",
					this.getClass().getName() + "/lisgatem/" + userName + "/" + municipioId + "/" + prestadoraId));
			response.setData(new ArrayList<>());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}

		List<AnoJSON> anosJSON = new ArrayList<AnoJSON>();
		for (AnoTO to : prestadoraTO.getAnos()) {
			anosJSON.add(new AnoJSON(to));
		}

		this.logController.insert(new Log(new Constantes().ANO_LISTAGEM,
				new Util().ListColectionToString(new ArrayList<Object>(anosJSON))));

		response.setData(anosJSON);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/insert/{username}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<AnoJSON>> insert(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@RequestBody AnoJSON anoJSON) {

		Response<AnoJSON> response = new Response<AnoJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxPxRx00001",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxPxRx00002",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			AnoTO anoTO = new AnoTO(anoJSON);

			List<DadoTO> dadosTO = this.dadoRepository.findAll();
			List<IndicadorTO> indicadoresTO = this.indicadorRepository.findAll();

			anoTO.setDado(dadosTO);
			anoTO.setIndicador(indicadoresTO);

			prestadoraTO.setAno(anoTO);
			this.municipioRepository.save(municipioTO);

			this.logController.insert(new Log(new Constantes().ANO_INSERT, prestadoraTO.toString()));
			response.setData(new AnoJSON(anoTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/update/{username}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<AnoJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@RequestBody AnoJSON anoJSON) {

		Response<AnoJSON> response = new Response<AnoJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxPxRx00001",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxPxRx00002",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			AnoTO anoTO = prestadoraTO.getAnoById(anoJSON.getId());
			anoTO.update(anoJSON);

			this.municipioRepository.save(municipioTO);

			this.logController.insert(new Log(new Constantes().ANO_UPDATE, prestadoraTO.toString()));
			response.setData(new AnoJSON(anoTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/delete/{username}/{municipioId}/{prestadoraId}/{dadoId}")
	public ResponseEntity<Response<AnoJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@PathVariable("dadoId") String dadoId) {

		Response<AnoJSON> response = new Response<AnoJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxPxRx00001",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxPxRx00002",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			AnoJSON anoJSON = new AnoJSON(prestadoraTO.getAnoById(dadoId));
			prestadoraTO.removeAno(dadoId);

			this.municipioRepository.save(municipioTO);

			this.logController.insert(new Log(new Constantes().ANO_DELETE, anoJSON.toString()));
			response.setData(anoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
