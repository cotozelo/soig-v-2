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

import br.com.nois.sa.rc.controller.DadoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.BooleanJSON;
import br.com.nois.sa.rc.model.json.DadoJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.DadoTO;
import br.com.nois.sa.rc.repository.DadoRepository;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/dado")
public class DadoControllerImpl implements DadoController {
	private DadoRepository dadoRepository;
	private LogRepository logRepository;
	private IndicadorRepository indicadorRepository;

	@Autowired
	LogController logController;

	public DadoControllerImpl(DadoRepository dadoRepository, IndicadorRepository indicadorRepository,
			LogRepository logRepository, VersaoRepository versaoRepository) {
		this.indicadorRepository = indicadorRepository;
		this.dadoRepository = dadoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@Override
	@GetMapping("/unicidade/sigla/{username}/{sigla}")
	public BooleanJSON unicidadeSigla(@PathVariable("username") String userName, @PathVariable("sigla") String sigla) {
		BooleanJSON retorno = new BooleanJSON();

		retorno.setChave("sigla");
		retorno.setValor(sigla);
		retorno.setExite(false);

		try {
			if (this.indicadorRepository.findBySiglaStartingWithIgnoreCase(sigla) == null) {
				if (this.dadoRepository.findBySiglaStartingWithIgnoreCase(sigla) != null) {
					retorno.setExite(true);
				}
			} else {
				retorno.setExite(true);
			}
		} catch (Exception ex) {
			retorno.setExite(false);
		}
		return retorno;
	}

	@Override
	@GetMapping("/listagem/{username}")
	public ResponseEntity<Response<List<DadoJSON>>> getAll(@PathVariable("username") String userName) {

		Response<List<DadoJSON>> response = new Response<List<DadoJSON>>();
		try {
			List<DadoTO> dadosTO = this.dadoRepository.findAll();
			if (dadosTO == null || dadosTO.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			List<DadoJSON> dadosJSON = new ArrayList<DadoJSON>();

			for (DadoTO dadoTO : dadosTO) {
				dadosJSON.add(new DadoJSON(dadoTO));
			}

			this.logController.insert(new Log(Constantes.DADO_GETALL,
					dadosJSON == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(dadosJSON))));
			response.setData(dadosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<DadoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody DadoJSON dadoJSON) {

		Response<DadoJSON> response = new Response<DadoJSON>();
		try {
			DadoTO dadoTO = new DadoTO(dadoJSON);

			this.logController.insert(new Log(Constantes.INDICADOR_INSERT, dadoTO.toString()));
			dadoTO = this.dadoRepository.insert(dadoTO);

			response.setData(new DadoJSON(dadoTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@PutMapping("/update/{username}")
	public ResponseEntity<Response<DadoJSON>> update(@PathVariable("username") String userName,
			@RequestBody DadoJSON dadoJSON) {

		Response<DadoJSON> response = new Response<DadoJSON>();
		try {
			DadoTO dadoTO = this.dadoRepository.findById(dadoJSON.getId());
			if (dadoTO == null) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/update/" + userName));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			dadoTO.update(dadoJSON);

			this.logController.insert(new Log(Constantes.DADO_UPDATE, dadoTO.toString()));
			dadoTO = this.dadoRepository.save(dadoTO);

			response.setData(new DadoJSON(dadoTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/// TODO talvez seja interessante colocar o como inativo ao invez
	/// de deleta-lo, avaliar quando implementar o CRUD
	@Override
	@DeleteMapping("/delete/{username}/{dadoid}")
	public ResponseEntity<Response<DadoJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("dadoid") String dadoId) {

		Response<DadoJSON> response = new Response<DadoJSON>();
		try {
			DadoTO dadoTO = this.dadoRepository.findById(dadoId);
			if (dadoTO == null) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			DadoJSON dadoJSON = new DadoJSON(dadoTO);
			this.dadoRepository.delete(dadoTO);

			this.logController.insert(new Log(Constantes.DADO_DELETEBYID, dadoTO.toString()));

			response.setData(dadoJSON);
			response.setData(new DadoJSON(dadoTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
