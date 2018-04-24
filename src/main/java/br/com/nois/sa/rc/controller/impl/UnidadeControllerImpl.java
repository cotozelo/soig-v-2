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

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.controller.UnidadeController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.UnidadeJSON;
import br.com.nois.sa.rc.model.to.UnidadeTO;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UnidadeRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/unidade")
public class UnidadeControllerImpl implements UnidadeController {

	private UnidadeRepository unidadeRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UnidadeControllerImpl(UnidadeRepository unidadeRepository, LogRepository logRepository,
			VersaoRepository versaoRespository) {
		super();
		this.unidadeRepository = unidadeRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRespository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.unidadeRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}
	
	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<UnidadeJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<UnidadeJSON>> response = new Response<List<UnidadeJSON>>();
		try {
			List<UnidadeTO> unidades = this.unidadeRepository.findAll();
			if (!unidades.isEmpty()) {
				List<UnidadeJSON> unidadesJSON = new ArrayList<UnidadeJSON>();
				for (UnidadeTO to : unidades) {
					unidadesJSON.add(new UnidadeJSON(to));
				}
				this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(unidadesJSON))));

				response.setData(unidadesJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);

			} else {
				response.setError(new ErroJSON("CxIxRx00001", this.getClass().getName() + "/all/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/all/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<UnidadeJSON>> insert(@PathVariable("username") String userName,
			@RequestBody UnidadeJSON unidadeJSON) {
		Response<UnidadeJSON> response = new Response<UnidadeJSON>();

		try {
			UnidadeTO unidadeTO = new UnidadeTO(unidadeJSON);
			unidadeTO = this.unidadeRepository.insert(unidadeTO);
			this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_INSERT, unidadeTO.toString()));
			response.setData(unidadeJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert("carga", new UnidadeJSON(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_INSERTTXT, itens.toString()));
	}

	@Override
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_CARGA, "Carga"));
		if (((List<UnidadeJSON>) this.getAll("carga")).size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert("carga", new UnidadeJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
