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

import br.com.nois.sa.rc.controller.GrupoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.GrupoJSON;
import br.com.nois.sa.rc.model.to.GrupoTO;
import br.com.nois.sa.rc.repository.GrupoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/grupo")
public class GrupoControllerImpl implements GrupoController {

	private GrupoRepository grupoRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public GrupoControllerImpl(GrupoRepository grupoRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		super();
		this.grupoRepository = grupoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.grupoRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<GrupoJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<GrupoJSON>> response = new Response<List<GrupoJSON>>();
		try {
			List<GrupoTO> grupos = this.grupoRepository.findAll();
			if (!grupos.isEmpty()) {
				List<GrupoJSON> gruposJSON = new ArrayList<GrupoJSON>();
				for (GrupoTO to : grupos) {
					gruposJSON.add(new GrupoJSON(to));
				}
				this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(gruposJSON))));

				response.setData(gruposJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);

			} else {
				response.setError(new ErroJSON("CxGxRx00001", this.getClass().getName() + "/all/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/all/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<GrupoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody GrupoJSON grupoJSON) {
		Response<GrupoJSON> response = new Response<GrupoJSON>();

		try {
			GrupoTO grupoTO = new GrupoTO(grupoJSON);
			grupoTO = this.grupoRepository.insert(grupoTO);
			this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_INSERT, grupoTO.toString()));
			response.setData(grupoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert("carga", new GrupoJSON(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_INSERTTXT, itens.toString()));
	}

	@Override
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_CARGA, "Carga"));
		if (((List<GrupoJSON>) this.getAll("carga")).size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert("carga", new GrupoJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
