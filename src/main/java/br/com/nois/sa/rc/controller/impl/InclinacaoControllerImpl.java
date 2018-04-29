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

import br.com.nois.sa.rc.controller.InclinacaoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.InclinacaoJSON;
import br.com.nois.sa.rc.model.to.InclinacaoTO;
import br.com.nois.sa.rc.repository.InclinacaoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/inclinacao")
public class InclinacaoControllerImpl implements InclinacaoController {

	private InclinacaoRepository inclinacaoRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public InclinacaoControllerImpl(InclinacaoRepository inclinacaoRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		super();
		this.inclinacaoRepository = inclinacaoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.inclinacaoRepository.countByNome(nome);
		this.logController.insert(new Log(Constantes.LOG_INCLINACAO_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<InclinacaoJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<InclinacaoJSON>> response = new Response<List<InclinacaoJSON>>();
		try {
			List<InclinacaoTO> inclinacoes = this.inclinacaoRepository.findAll();
			if (!inclinacoes.isEmpty()) {
				List<InclinacaoJSON> inclinacoesJSON = new ArrayList<InclinacaoJSON>();
				for (InclinacaoTO to : inclinacoes) {
					inclinacoesJSON.add(new InclinacaoJSON(to));
				}
				this.logController.insert(new Log(Constantes.LOG_INCLINACAO_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(inclinacoesJSON))));

				response.setData(inclinacoesJSON);
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
	public ResponseEntity<Response<InclinacaoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody InclinacaoJSON inclinacaoJSON) {
		Response<InclinacaoJSON> response = new Response<InclinacaoJSON>();

		try {
			InclinacaoTO inclinacaoTO = new InclinacaoTO(inclinacaoJSON);
			inclinacaoTO = this.inclinacaoRepository.insert(inclinacaoTO);
			this.logController.insert(new Log(Constantes.LOG_INCLINACAO_CONTROLLER_INSERT, inclinacaoTO.toString()));
			response.setData(inclinacaoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert("carga", new InclinacaoJSON(item));
		}
		this.logController.insert(new Log(Constantes.LOG_INCLINACAO_CONTROLLER_INSERTTXT, itens.toString()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(Constantes.LOG_INCLINACAO_CONTROLLER_CARGA, "Carga"));
		if (((List<InclinacaoJSON>) this.getAll("carga")).size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert("carga", new InclinacaoJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
