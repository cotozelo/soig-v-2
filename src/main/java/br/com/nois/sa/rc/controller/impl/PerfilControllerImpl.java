package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.PerfilController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.PerfilJSON;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;
import br.com.nois.sa.rc.model.to.PerfilTO;
import br.com.nois.sa.rc.repository.FuncionalidadeRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.PerfilRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/perfil")
public class PerfilControllerImpl implements PerfilController {

	private PerfilRepository perfilRepository;
	private LogRepository logRepository;
	private FuncionalidadeRepository funcionalidadeRepository;

	@Autowired
	LogController logController;
	private Map<String, String> funcionalidades = new HashMap<String, String>();

	public PerfilControllerImpl(PerfilRepository perfilRepository, FuncionalidadeRepository funcionalidadeRepository,
			LogRepository logRepository, VersaoRepository versaoRepository) {

		this.perfilRepository = perfilRepository;
		this.funcionalidadeRepository = funcionalidadeRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);

		for (FuncionalidadeTO funcionalidade : this.funcionalidadeRepository.findAll()) {
			this.funcionalidades.put(funcionalidade.getId(), funcionalidade.getNome());
		}
	}

	@Override
	@GetMapping("/all/{username}")
	public ResponseEntity<Response<List<PerfilJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<PerfilJSON>> response = new Response<List<PerfilJSON>>();
		try {
			List<PerfilTO> perfilsTO = this.perfilRepository.findAll();
			if (!perfilsTO.isEmpty()) {

				List<PerfilJSON> perfilsJSON = new ArrayList<PerfilJSON>();

				for (PerfilTO perfilTO : perfilsTO) {
					perfilsJSON.add(new PerfilJSON(perfilTO));
				}

				this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
						new Util().ListColectionToString(new ArrayList<Object>(perfilsJSON))));

				response.setData(perfilsJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setError(new ErroJSON("VxPxRx00001", this.getClass().getName() + "/all/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/all/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@PutMapping("/update/{username}")
	public ResponseEntity<Response<List<PerfilJSON>>> update(@PathVariable("username") String userName,
			@RequestBody List<PerfilJSON> perfilsJSON) {
		Response<List<PerfilJSON>> response = new Response<List<PerfilJSON>>();
		try {
			List<PerfilJSON> perfilsJSONsave = new ArrayList<PerfilJSON>();
			for (PerfilJSON perfilJSON : perfilsJSON) {
				PerfilTO perfilTO = new PerfilTO(perfilJSON);
				for (FuncionalidadeTO funcionalidade : perfilTO.getFuncionalidades()) {
					funcionalidade.setNome(this.funcionalidades.get(funcionalidade.getId()));
				}
				this.logController
						.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL, perfilTO.toString()));

				perfilTO = this.perfilRepository.save(perfilTO);
				perfilsJSONsave.add(new PerfilJSON(perfilTO));
			}
			response.setData(perfilsJSONsave);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName + "/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	@DeleteMapping("/{username}/{id}")
	public ResponseEntity<Response<PerfilJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("id") String id) {
		Response<PerfilJSON> response = new Response<PerfilJSON>();
		try {
			PerfilTO perfilTO = this.perfilRepository.findById(id);

			this.logController
					.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL, perfilTO.toString()));

			this.perfilRepository.delete(perfilTO);

			response.setData(new PerfilJSON(perfilTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/" + userName + "/" + id));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
