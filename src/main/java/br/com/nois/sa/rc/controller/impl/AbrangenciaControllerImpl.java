package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.AbrangenciaController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.AbrangenciaJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.AbrangenciaTO;
import br.com.nois.sa.rc.repository.AbrangenciaRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/abrangencia")
public class AbrangenciaControllerImpl implements AbrangenciaController {

	private AbrangenciaRepository abrangenciaRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public AbrangenciaControllerImpl(AbrangenciaRepository abrangenciaRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.abrangenciaRepository = abrangenciaRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.abrangenciaRepository.countByNome(nome);
		this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<AbrangenciaJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<AbrangenciaJSON>> response = new Response<List<AbrangenciaJSON>>();
		try {
			List<AbrangenciaTO> abrangencias = this.abrangenciaRepository.findAll();
			if (!abrangencias.isEmpty()) {

				List<AbrangenciaJSON> abrangenciasJSON = new ArrayList<AbrangenciaJSON>();

				for (AbrangenciaTO to : abrangencias) {
					abrangenciasJSON.add(new AbrangenciaJSON(to));
				}

				this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(abrangenciasJSON))));

				response.setData(abrangenciasJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setError(new ErroJSON("VxFxRx00001", this.getClass().getName() + "/all/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/all/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/nome/{username}/{nome}")
	public ResponseEntity<Response<AbrangenciaJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome) {
		Response<AbrangenciaJSON> response = new Response<AbrangenciaJSON>();
		try {
			AbrangenciaTO abrangenciaTO = this.abrangenciaRepository.findByNome(nome);
			if (abrangenciaTO != null) {
				AbrangenciaJSON abrangenciaJSON = new AbrangenciaJSON(abrangenciaTO);

				this.logController.insert(
						new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_GETBYNOME, abrangenciaJSON.toString()));
				response.setData(abrangenciaJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setError(new ErroJSON("VxFxRx00002", this.getClass().getName() + "/" + userName + "/" + nome));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/nome/" + userName + "/" + nome));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<AbrangenciaJSON>> insert(@PathVariable("username") String userName,
			@RequestBody AbrangenciaJSON abrangenciaJSON) {
		Response<AbrangenciaJSON> response = new Response<AbrangenciaJSON>();
		try {

			AbrangenciaTO abrangenciaTO = new AbrangenciaTO(abrangenciaJSON);

			abrangenciaTO = this.abrangenciaRepository.insert(abrangenciaTO);
			this.logController
					.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_INSERT, abrangenciaTO.toString()));
			response.setData(abrangenciaJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/inserts/{username}")
	public ResponseEntity<Response<List<AbrangenciaJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<AbrangenciaJSON> abrangenciasJSON) {
		Response<List<AbrangenciaJSON>> response = new Response<List<AbrangenciaJSON>>();
		try {

			List<AbrangenciaTO> abrangenciasTO = new ArrayList<AbrangenciaTO>();
			for (AbrangenciaJSON abrangenciaJSON : abrangenciasJSON) {
				abrangenciasTO.add(new AbrangenciaTO(abrangenciaJSON));
			}

			this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(abrangenciasTO))));
			abrangenciasTO = this.abrangenciaRepository.insert(abrangenciasTO);

			abrangenciasJSON.clear();
			for (AbrangenciaTO abrangenciaTO : abrangenciasTO) {
				abrangenciasJSON.add(new AbrangenciaJSON(abrangenciaTO));
			}

			response.setData(abrangenciasJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	// TODO colocar os metodos de carga dentro do pacote rc.carga
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert("carga", new AbrangenciaJSON(item));
		}
		this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_INSERTTXT, itens.toString()));

	}

	@SuppressWarnings("unchecked")
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_CARGA, "Carga"));
		if (((List<AbrangenciaJSON>) this.getAll("carga")).size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert("carga", new AbrangenciaJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}
}
