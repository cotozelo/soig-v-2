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

import br.com.nois.sa.rc.controller.EixoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.EixoJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.EixoTO;
import br.com.nois.sa.rc.repository.EixoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/eixo")
public class EixoControllerImpl implements EixoController {

	private EixoRepository eixoRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public EixoControllerImpl(EixoRepository eixoRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.eixoRepository = eixoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.eixoRepository.countByNome(nome);
		this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<EixoJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<EixoJSON>> response = new Response<List<EixoJSON>>();
		try {
			List<EixoTO> eixos = this.eixoRepository.findAll();
			if (!eixos.isEmpty()) {

				List<EixoJSON> eixosJSON = new ArrayList<EixoJSON>();

				for (EixoTO to : eixos) {
					eixosJSON.add(new EixoJSON(to));
				}

				this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(eixosJSON))));

				response.setData(eixosJSON);
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
	public ResponseEntity<Response<EixoJSON>> getByNome(@PathVariable("username") String userName,
			@PathVariable("nome") String nome) {
		Response<EixoJSON> response = new Response<EixoJSON>();
		try {
			EixoTO eixoTO = this.eixoRepository.findByNome(nome);
			if (eixoTO != null) {
				EixoJSON eixoJSON = new EixoJSON(eixoTO);

				this.logController
						.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_GETBYNOME, eixoJSON.toString()));
				response.setData(eixoJSON);
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
	public ResponseEntity<Response<EixoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody EixoJSON eixoJSON) {
		Response<EixoJSON> response = new Response<EixoJSON>();
		try {

			EixoTO eixoTO = new EixoTO(eixoJSON);

			eixoTO = this.eixoRepository.insert(eixoTO);
			this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_INSERT, eixoTO.toString()));
			response.setData(eixoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/inserts/{username}")
	public ResponseEntity<Response<List<EixoJSON>>> inserts(@PathVariable("username") String userName,
			@RequestBody List<EixoJSON> eixosJSON) {
		Response<List<EixoJSON>> response = new Response<List<EixoJSON>>();
		try {

			List<EixoTO> eixosTO = new ArrayList<EixoTO>();
			for (EixoJSON eixoJSON : eixosJSON) {
				eixosTO.add(new EixoTO(eixoJSON));
			}

			this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(eixosTO))));
			eixosTO = this.eixoRepository.insert(eixosTO);

			eixosJSON.clear();
			for (EixoTO eixoTO : eixosTO) {
				eixosJSON.add(new EixoJSON(eixoTO));
			}

			response.setData(eixosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert("carga", new EixoJSON(item));
		}
		this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_INSERTTXT, itens.toString()));

	}

	@SuppressWarnings("unchecked")
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(Constantes.LOG_FUNCIONALIDADE_CONTROLLER_CARGA, "Carga"));
		if (((List<EixoJSON>) this.getAll("carga")).size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert("carga", new EixoJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}
}
