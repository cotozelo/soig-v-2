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

import br.com.nois.sa.rc.controller.AgenciaController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.AgenciaJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.AgenciaTO;
import br.com.nois.sa.rc.repository.AgenciaRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/agencia")
public class AgenciaControllerImpl implements AgenciaController {
	private AgenciaRepository agenciaRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public AgenciaControllerImpl(AgenciaRepository agenciaRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.agenciaRepository = agenciaRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/listagem/{username}")
	public ResponseEntity<Response<List<AgenciaJSON>>> getAll(@PathVariable("username") String userName) {
		Response<List<AgenciaJSON>> response = new Response<List<AgenciaJSON>>();
		List<AgenciaTO> agenciasTO = this.agenciaRepository.findAll();

		if (agenciasTO == null) {
			response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		List<AgenciaJSON> agenciasJSON = new ArrayList<AgenciaJSON>();
		for (AgenciaTO to : agenciasTO) {
			agenciasJSON.add(new AgenciaJSON(to));
		}

		this.logController.insert(new Log(new Constantes().AGENCIA_LISTAGEM,
				new Util().ListColectionToString(new ArrayList<Object>(agenciasJSON))));

		response.setData(agenciasJSON);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<AgenciaJSON>> insert(@PathVariable("username") String userName,
			@RequestBody AgenciaJSON agenciaJSON) {
		Response<AgenciaJSON> response = new Response<AgenciaJSON>();
		try {
			AgenciaTO agenciaTO = new AgenciaTO(agenciaJSON);
			this.logController.insert(new Log(new Constantes().AGENCIA_INSERT, agenciaTO.toString()));
			this.agenciaRepository.insert(agenciaTO);

			agenciaJSON = new AgenciaJSON(agenciaTO);
			response.setData(agenciaJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<Response<AgenciaJSON>> update(@PathVariable("username") String userName,
			@RequestBody AgenciaJSON agenciaJSON) {
		Response<AgenciaJSON> response = new Response<AgenciaJSON>();
		try {
			AgenciaTO agenciaTO = new AgenciaTO(agenciaJSON);
			this.logController.insert(new Log(new Constantes().AGENCIA_UPDATE, agenciaTO.toString()));

			agenciaTO = this.agenciaRepository.save(agenciaTO);
			agenciaJSON = new AgenciaJSON(agenciaTO);

			response.setData(agenciaJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/// TODO talvez seja interessante colocar o como inativo ao invez
	/// de deleta-lo, avaliar quando implementar o CRUD
	@DeleteMapping("/delete/{username}/{id}")
	public ResponseEntity<Response<AgenciaJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("id") String id) {
		Response<AgenciaJSON> response = new Response<AgenciaJSON>();
		try {
			AgenciaTO agenciaTO = this.agenciaRepository.findById(id);
			if (agenciaTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/delete/" + userName));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			this.logController.insert(new Log(new Constantes().AGENCIA_DELETE, agenciaTO.toString()));
			this.agenciaRepository.delete(id);

			response.setData(new AgenciaJSON(agenciaTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/delete/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
