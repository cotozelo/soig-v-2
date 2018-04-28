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
import br.com.nois.sa.rc.controller.ServicoController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.ServicoJSON;
import br.com.nois.sa.rc.model.to.ServicoTO;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.ServicoRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/servico")
public class ServicoControllerImpl implements ServicoController {

	private ServicoRepository servicoRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public ServicoControllerImpl(ServicoRepository servicoRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.servicoRepository = servicoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.servicoRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<ServicoJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<ServicoJSON>> response = new Response<List<ServicoJSON>>();
		try {
			List<ServicoTO> servicos = this.servicoRepository.findAll();
			if (!servicos.isEmpty()) {

				List<ServicoJSON> servicosJSON = new ArrayList<ServicoJSON>();

				for (ServicoTO to : servicos) {
					servicosJSON.add(new ServicoJSON(to));
				}

				this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(servicosJSON))));

				response.setData(servicosJSON);
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
	public ResponseEntity<Response<ServicoJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome) {
		Response<ServicoJSON> response = new Response<ServicoJSON>();
		try {
			ServicoTO servicoTO = this.servicoRepository.findByNome(nome);
			if (servicoTO != null) {
				ServicoJSON servicoJSON = new ServicoJSON(servicoTO);

				this.logController.insert(
						new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETBYNOME, servicoJSON.toString()));
				response.setData(servicoJSON);
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
	public ResponseEntity<Response<ServicoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody ServicoJSON servicoJSON) {
		Response<ServicoJSON> response = new Response<ServicoJSON>();
		try {

			ServicoTO servicoTO = new ServicoTO(servicoJSON);

			servicoTO = this.servicoRepository.insert(servicoTO);
			this.logController
					.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERT, servicoTO.toString()));
			response.setData(servicoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/inserts/{username}")
	public ResponseEntity<Response<List<ServicoJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<ServicoJSON> servicosJSON) {
		Response<List<ServicoJSON>> response = new Response<List<ServicoJSON>>();
		try {

			List<ServicoTO> servicosTO = new ArrayList<ServicoTO>();
			for (ServicoJSON servicoJSON : servicosJSON) {
				servicosTO.add(new ServicoTO(servicoJSON));
			}

			this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(servicosTO))));
			servicosTO = this.servicoRepository.insert(servicosTO);

			servicosJSON.clear();
			for (ServicoTO servicoTO : servicosTO) {
				servicosJSON.add(new ServicoJSON(servicoTO));
			}

			response.setData(servicosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	// TODO colocar os metodos de carga dentro do pacote rc.carga
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert("carga", new ServicoJSON(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTTXT, itens.toString()));

	}

	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_CARGA, "Carga"));
		if (((List<ServicoJSON>) this.getAll("carga")).size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert("carga", new ServicoJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}
}
