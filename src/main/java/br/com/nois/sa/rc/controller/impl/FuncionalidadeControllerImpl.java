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

import br.com.nois.sa.rc.controller.FuncionalidadeController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.FuncionalidadeJSON;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;
import br.com.nois.sa.rc.repository.FuncionalidadeRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/funcionalidade")
public class FuncionalidadeControllerImpl implements FuncionalidadeController {

	private FuncionalidadeRepository funcionalidadeRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public FuncionalidadeControllerImpl(FuncionalidadeRepository funcionalidadeRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.funcionalidadeRepository = funcionalidadeRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.funcionalidadeRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<FuncionalidadeJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<FuncionalidadeJSON>> response = new Response<List<FuncionalidadeJSON>>();
		try {
			List<FuncionalidadeTO> funcionalidades = this.funcionalidadeRepository.findAll();
			if (!funcionalidades.isEmpty()) {

				List<FuncionalidadeJSON> funcionalidadesJSON = new ArrayList<FuncionalidadeJSON>();

				for (FuncionalidadeTO to : funcionalidades) {
					funcionalidadesJSON.add(new FuncionalidadeJSON(to));
				}

				this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(funcionalidadesJSON))));

				response.setData(funcionalidadesJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setError(new ErroJSON("VxFxRx00001", this.getClass().getName() + "/all/" + userName));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/all/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/nome/{username}/{nome}")
	public ResponseEntity<Response<FuncionalidadeJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome) {
		Response<FuncionalidadeJSON> response = new Response<FuncionalidadeJSON>();
		try {
			FuncionalidadeTO funcionalidadeTO = this.funcionalidadeRepository.findByNome(nome);
			if (funcionalidadeTO != null) {
				FuncionalidadeJSON funcionalidadeJSON = new FuncionalidadeJSON(funcionalidadeTO);

				this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETBYNOME,
						funcionalidadeJSON.toString()));
				response.setData(funcionalidadeJSON);
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
	public ResponseEntity<Response<FuncionalidadeJSON>> insert(@PathVariable("username") String userName,
			@RequestBody FuncionalidadeJSON funcionalidadeJSON) {
		Response<FuncionalidadeJSON> response = new Response<FuncionalidadeJSON>();
		try {

			FuncionalidadeTO funcionalidadeTO = new FuncionalidadeTO(funcionalidadeJSON);

			funcionalidadeTO = this.funcionalidadeRepository.insert(funcionalidadeTO);
			this.logController.insert(
					new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERT, funcionalidadeTO.toString()));
			response.setData(funcionalidadeJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/inserts/{username}")
	public ResponseEntity<Response<List<FuncionalidadeJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<FuncionalidadeJSON> funcionalidadesJSON) {
		Response<List<FuncionalidadeJSON>> response = new Response<List<FuncionalidadeJSON>>();
		try {

			List<FuncionalidadeTO> funcionalidadesTO = new ArrayList<FuncionalidadeTO>();
			for (FuncionalidadeJSON funcionalidadeJSON : funcionalidadesJSON) {
				funcionalidadesTO.add(new FuncionalidadeTO(funcionalidadeJSON));
			}

			this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(funcionalidadesTO))));
			funcionalidadesTO = this.funcionalidadeRepository.insert(funcionalidadesTO);

			funcionalidadesJSON.clear();
			for (FuncionalidadeTO funcionalidadeTO : funcionalidadesTO) {
				funcionalidadesJSON.add(new FuncionalidadeJSON(funcionalidadeTO));
			}

			response.setData(funcionalidadesJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	// TODO colocar os metodos de carga dentro do pacote rc.carga
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert("carga", new FuncionalidadeJSON(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTTXT, itens.toString()));

	}

	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_CARGA, "Carga"));
		if (((List<FuncionalidadeJSON>) this.getAll("carga")).size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert("carga", new FuncionalidadeJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}
}
