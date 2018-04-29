package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.MunicipioController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.BooleanJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.MunicipioJSON;
import br.com.nois.sa.rc.model.to.MunicipioTO;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/municipio")
public class MunicipioControllerImpl implements MunicipioController {
	private MunicipioRepository municipioRepository;
	private LogRepository logRepository;
	private VersaoRepository versaoRepository;

	@Autowired
	LogController logController;

	public MunicipioControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.versaoRepository = versaoRepository;

		this.logController = new LogControllerImpl(this.logRepository, this.versaoRepository);
	}

	public Page<MunicipioTO> getAllByPage(@RequestParam(defaultValue = "0") int pagina,
			@RequestParam(defaultValue = "10") int porPagina, @RequestParam(defaultValue = "nome") String ordenacao,
			@RequestParam(defaultValue = "ASC") Sort.Direction direcao) {
		return this.municipioRepository.findAll(new PageRequest(pagina, porPagina, new Sort(direcao, ordenacao)));
	}

	@Override
	@GetMapping("/unicidade/nome/{username}/{nome}")
	public BooleanJSON unicidadeNome(@PathVariable("username") String userName, @PathVariable("nome") String nome) {
		BooleanJSON retorno = new BooleanJSON();

		retorno.setChave("nome");
		retorno.setValor(nome);
		retorno.setExite(false);

		try {
			if (this.municipioRepository.findByNomeStartingWithIgnoreCase(nome) != null) {
				retorno.setExite(true);
			}
		} catch (Exception ex) {
			retorno.setExite(false);
		}
		return retorno;
	}

	@Override
	@GetMapping("/unicidade/codigo/{username}/{codigo}")
	public BooleanJSON unicidadeCodigo(@PathVariable("username") String userName,
			@PathVariable("codigo") String codigo) {
		BooleanJSON retorno = new BooleanJSON();

		retorno.setChave("codigo");
		retorno.setValor(codigo);
		retorno.setExite(false);

		try {
			if (this.municipioRepository.findByCodigoStartingWithIgnoreCase(codigo) != null) {
				retorno.setExite(true);
			}
		} catch (Exception ex) {
			retorno.setExite(false);
		}
		return retorno;
	}

	@Override
	@GetMapping("/listagem/{username}/{agenciaId}")
	public ResponseEntity<Response<List<MunicipioJSON>>> getByAgenciaId(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId) {
		Response<List<MunicipioJSON>> response = new Response<List<MunicipioJSON>>();
		try {
			List<MunicipioTO> municipiosTO = this.municipioRepository.findByAgenciaId(agenciaId);
			if (municipiosTO == null || municipiosTO.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			this.logController.insert(new Log(Constantes.MUNICIPIO_LISTAGEM, municipiosTO.toString()));
			List<MunicipioJSON> municipiosJSON = new ArrayList<MunicipioJSON>();
			for (MunicipioTO to : municipiosTO) {
				municipiosJSON.add(new MunicipioJSON(to));
			}
			response.setData(municipiosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<MunicipioJSON>> insert(@PathVariable("username") String userName,
			@RequestBody MunicipioJSON municipioJSON) {

		Response<MunicipioJSON> response = new Response<MunicipioJSON>();
		try {
			MunicipioTO municipioTO = new MunicipioTO(municipioJSON);

			this.logController.insert(new Log(Constantes.MUNICIPIO_INSERT, municipioTO.toString()));
			municipioTO = this.municipioRepository.insert(municipioTO);

			response.setData(new MunicipioJSON(municipioTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@PutMapping("/update/{username}")
	public ResponseEntity<Response<MunicipioJSON>> update(@PathVariable("username") String userName,
			@RequestBody MunicipioJSON municipioJSON) {
		Response<MunicipioJSON> response = new Response<MunicipioJSON>();
		try {
			MunicipioTO municipioTO = new MunicipioTO(municipioJSON);
			this.logController.insert(new Log(Constantes.MUNICIPIO_UPDATE, municipioTO.toString()));

			municipioTO = this.municipioRepository.save(municipioTO);
			municipioJSON = new MunicipioJSON(municipioTO);

			response.setData(municipioJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/mover/{username}/{municipioid}/{agenciaidorigem}/{agenciaiddestino}")
	public ResponseEntity<Response<MunicipioJSON>> move(@PathVariable("username") String userName,
			@PathVariable("municipioid") String municipioId, @PathVariable("agenciaidorigem") String agenciaIdOrigem,
			@PathVariable("agenciaiddestino") String agenciaIdDestino) {
		Response<MunicipioJSON> response = new Response<MunicipioJSON>();
		try {
			MunicipioTO municipioTO = this.municipioRepository.findByIdAndAgenciaId(municipioId, agenciaIdOrigem);

			this.logController.insert(new Log(Constantes.MUNICIPIO_UPDATE, municipioTO.toString()));

			municipioTO.setAgenciaId(agenciaIdDestino);

			municipioTO = this.municipioRepository.save(municipioTO);
			MunicipioJSON municipioJSON = new MunicipioJSON(municipioTO);

			response.setData(municipioJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@DeleteMapping("/delete/{username}/{id}")
	public ResponseEntity<Response<MunicipioJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("id") String id) {
		Response<MunicipioJSON> response = new Response<MunicipioJSON>();
		try {
			MunicipioTO municipioTO = this.municipioRepository.findById(id);
			if (municipioTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/delete/" + userName));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			this.logController.insert(new Log(Constantes.MUNICIPIO_DELETE, municipioTO.toString()));
			this.municipioRepository.delete(id);

			response.setData(new MunicipioJSON(municipioTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/delete/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
