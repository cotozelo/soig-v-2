package br.com.nois.sa.rc.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.controller.UsuarioController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.UsuarioJSON;
import br.com.nois.sa.rc.model.to.PerfilTO;
import br.com.nois.sa.rc.model.to.UsuarioTO;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.PerfilRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllerImpl implements UsuarioController {
	private UsuarioRepository usuarioRepository;
	private PerfilRepository perfilRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UsuarioControllerImpl(UsuarioRepository usuarioRepository, LogRepository logRepository,
			PerfilRepository perfilRepository, VersaoRepository versaoRepository) {

		this.usuarioRepository = usuarioRepository;
		this.perfilRepository = perfilRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@PutMapping("/atribuir/{username}")
	public ResponseEntity<Response<UsuarioJSON>> update(@PathVariable("username") String userName,
			@RequestBody UsuarioJSON usuarioJSON) {
		Response<UsuarioJSON> response = new Response<UsuarioJSON>();
		try {
			UsuarioTO usuarioTO = this.usuarioRepository.findById(usuarioJSON.getId());
			usuarioJSON.setSenha(usuarioTO.getSenha());
			usuarioTO = new UsuarioTO(usuarioJSON);

			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, usuarioTO.toString()));
			usuarioTO = this.usuarioRepository.save(usuarioTO);

			usuarioJSON = new UsuarioJSON(usuarioTO);
			usuarioJSON.setSenha(null);
			response.setData(usuarioJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/atribuir/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<UsuarioJSON>> insert(@PathVariable("username") String userName,
			@RequestBody UsuarioJSON usuarioJSON) {
		Response<UsuarioJSON> response = new Response<UsuarioJSON>();
		try {
			UsuarioTO usurioTO = new UsuarioTO(usuarioJSON);

			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, usurioTO.toString()));
			usurioTO = this.usuarioRepository.insert(usurioTO);

			usuarioJSON = new UsuarioJSON(usurioTO);
			usuarioJSON.setSenha(null);
			response.setData(usuarioJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/informacao/{username}")
	public ResponseEntity<Response<UsuarioJSON>> getInformacao(@PathVariable("username") String userName) {
		Response<UsuarioJSON> response = new Response<UsuarioJSON>();
		try {
			UsuarioTO usuarioTO = this.usuarioRepository.findByNomeDeUsuario(userName);
			if (usuarioTO != null) {
				usuarioTO.setSenha(null);
				this.logController.insert(
						new Log(new Constantes().USUARIO_INFORMACAO, usuarioTO == null ? "" : usuarioTO.toString()));

				UsuarioJSON usuarioJSON = new UsuarioJSON(usuarioTO);

				PerfilTO perfilTO = this.perfilRepository.findById(usuarioTO.getPerfilId());

				if (perfilTO != null) {
					usuarioJSON.setUsuarioFuncionalidades(perfilTO.getFuncionalidades());
				}

				response.setData(usuarioJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.setError(new ErroJSON("VxUxRx00001", this.getClass().getName() + "/informacao/" + userName));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/informacao/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
