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

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.PrestadoraController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.BooleanJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.PrestadoraJSON;
import br.com.nois.sa.rc.model.to.MunicipioTO;
import br.com.nois.sa.rc.model.to.PrestadoraTO;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/prestadora")
public class PrestadoraControllerImpl implements PrestadoraController {

	private MunicipioRepository municipioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public PrestadoraControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			VersaoRepository versaoRespository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRespository);
	}

	@Override
	@GetMapping("/unicidade/nome/{username}/{municipioId}/{nome}")
	public BooleanJSON unicidadeNome(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("nome") String nome) {
		BooleanJSON retorno = new BooleanJSON();

		retorno.setChave("nome");
		retorno.setValor(nome);
		retorno.setExite(false);

		try {
			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);
			if (municipioTO != null) {
				for (PrestadoraTO prestadoraTO : municipioTO.getPrestadoras()) {
					if (prestadoraTO.getNome().equalsIgnoreCase(nome)) {
						retorno.setExite(true);
						return retorno;
					}
				}
			}
		} catch (Exception ex) {
			retorno.setExite(false);
		}
		return retorno;
	}

	@Override
	@GetMapping("/unicidade/codigo/{username}/{municipioId}/{codigo}")
	public BooleanJSON unicidadeCodigo(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("codigo") String codigo) {
		BooleanJSON retorno = new BooleanJSON();

		retorno.setChave("codigo");
		retorno.setValor(codigo);
		retorno.setExite(false);

		try {
			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);
			if (municipioTO != null) {
				for (PrestadoraTO prestadoraTO : municipioTO.getPrestadoras()) {
					if (prestadoraTO.getCodigo().equalsIgnoreCase(codigo)) {
						retorno.setExite(true);
						return retorno;
					}
				}
			}
		} catch (Exception ex) {
			retorno.setExite(false);
		}
		return retorno;
	}

	@Override
	@GetMapping("/listagem/{username}/{municipioId}")
	public ResponseEntity<Response<List<PrestadoraJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId) {

		Response<List<PrestadoraJSON>> response = new Response<List<PrestadoraJSON>>();
		MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

		if (municipioTO == null) {
			response.setError(new ErroJSON("VxAxRx00001",
					this.getClass().getName() + "/lisgatem/" + userName + "/" + municipioId));
			response.setData(new ArrayList<>());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}

		List<PrestadoraJSON> prestadorasJSON = new ArrayList<PrestadoraJSON>();
		for (PrestadoraTO to : municipioTO.getPrestadoras()) {
			prestadorasJSON.add(new PrestadoraJSON(to));
		}

		this.logController.insert(new Log(Constantes.PRESTADORA_LISTAGEM,
				new Util().ListColectionToString(new ArrayList<Object>(prestadorasJSON))));

		response.setData(prestadorasJSON);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@Override
	@PostMapping("/insert/{username}/{municipioId}")
	public ResponseEntity<Response<PrestadoraJSON>> insert(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @RequestBody PrestadoraJSON prestadoraJSON) {

		Response<PrestadoraJSON> response = new Response<PrestadoraJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxAxRx00001",
						this.getClass().getName() + "/lisgatem/" + userName + "/" + municipioId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			PrestadoraTO prestadoraTO = new PrestadoraTO(prestadoraJSON);
			this.logController.insert(new Log(Constantes.PRESTADORA_INSERT, prestadoraTO.toString()));
			municipioTO.setPrestadora(prestadoraTO);

			this.municipioRepository.save(municipioTO);

			response.setData(new PrestadoraJSON(municipioTO.getPrestadora(prestadoraTO.getId())));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@PutMapping("/update/{username}/{municipioId}")
	public ResponseEntity<Response<PrestadoraJSON>> update(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @RequestBody PrestadoraJSON prestadoraJSON) {

		Response<PrestadoraJSON> response = new Response<PrestadoraJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxAxRx00001",
						this.getClass().getName() + "/lisgatem/" + userName + "/" + municipioId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			PrestadoraTO prestadoraTO = new PrestadoraTO(prestadoraJSON);
			prestadoraTO.setAnos(municipioTO.getPrestadora(prestadoraJSON.getId()).getAnos());
			this.logController.insert(new Log(Constantes.PRESTADORA_UPDATE, prestadoraTO.toString()));
			municipioTO.setPrestadora(prestadoraTO);

			this.municipioRepository.save(municipioTO);

			response.setData(new PrestadoraJSON(municipioTO.getPrestadora(prestadoraTO.getId())));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@DeleteMapping("/delete/{username}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<PrestadoraJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId) {

		Response<PrestadoraJSON> response = new Response<PrestadoraJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxAxRx00001",
						this.getClass().getName() + "/lisgatem/" + userName + "/" + municipioId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			municipioTO.removePrestadora(prestadoraId);

			this.logController.insert(new Log(Constantes.PRESTADORA_DELETE, prestadoraTO.toString()));
			this.municipioRepository.save(municipioTO);

			response.setData(new PrestadoraJSON(prestadoraTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/delete/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
