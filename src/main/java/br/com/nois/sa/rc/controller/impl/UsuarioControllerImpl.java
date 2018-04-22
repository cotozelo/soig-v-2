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

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.controller.UsuarioController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.UsuarioJSON;
import br.com.nois.sa.rc.model.to.PerfilTO;
import br.com.nois.sa.rc.model.to.UsuarioAgenciaTO;
import br.com.nois.sa.rc.model.to.UsuarioMunicipioTO;
import br.com.nois.sa.rc.model.to.UsuarioPrestadoraTO;
import br.com.nois.sa.rc.model.to.UsuarioTO;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.PerfilRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

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
	public ResponseEntity<Response<UsuarioJSON>> atribuir(@PathVariable("username") String userName,
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

	@PutMapping("/update/{username}")
	public ResponseEntity<Response<UsuarioJSON>> update(@PathVariable("username") String userName,
			@RequestBody UsuarioJSON usuarioJSON) {
		Response<UsuarioJSON> response = new Response<UsuarioJSON>();
		try {
			UsuarioTO usuarioTO = this.usuarioRepository.findById(usuarioJSON.getId());
			usuarioJSON.setSenha(usuarioTO.getSenha());
			usuarioTO.update(usuarioJSON);

			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, usuarioTO.toString()));
			usuarioTO = this.usuarioRepository.save(usuarioTO);

			usuarioJSON = new UsuarioJSON(usuarioTO);
			usuarioJSON.setSenha(null);
			response.setData(usuarioJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<UsuarioJSON>> insert(@PathVariable("username") String userName,
			@RequestBody UsuarioJSON usuarioJSON) {
		Response<UsuarioJSON> response = new Response<UsuarioJSON>();
		try {
			UsuarioTO usuarioTO = new UsuarioTO(usuarioJSON);

			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, usuarioTO.toString()));
			usuarioTO = this.usuarioRepository.insert(usuarioTO);

			usuarioJSON = new UsuarioJSON(usuarioTO);
			usuarioJSON.setSenha(null);
			response.setData(usuarioJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/delete/{username}/{usuarioid}")
	public ResponseEntity<Response<UsuarioJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("usuarioid") String usuarioId) {
		Response<UsuarioJSON> response = new Response<UsuarioJSON>();
		try {
			UsuarioTO usuarioTO = this.usuarioRepository.findById(usuarioId);
			if (usuarioTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/delete/" + userName));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			this.logController.insert(new Log(new Constantes().USUARIO_DELETE, usuarioTO.toString()));
			this.usuarioRepository.delete(usuarioId);

			response.setData(new UsuarioJSON(usuarioTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/delete/" + userName));
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
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/informacao/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/listagem/{username}")
	public ResponseEntity<Response<List<UsuarioJSON>>> getAll(@PathVariable("username") String userName) {

		Response<List<UsuarioJSON>> response = new Response<List<UsuarioJSON>>();
		try {

			List<UsuarioJSON> usuariosJSON = this.getUsuarios("", "", "");
			if (usuariosJSON == null || usuariosJSON.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			this.logController.insert(new Log(new Constantes().INDICADOR_GETALL,
					usuariosJSON == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(usuariosJSON))));
			response.setData(usuariosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/listagem/{username}/{agenciaid}")
	public ResponseEntity<Response<List<UsuarioJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaid") String agenciaId) {

		Response<List<UsuarioJSON>> response = new Response<List<UsuarioJSON>>();
		try {

			List<UsuarioJSON> usuariosJSON = this.getUsuarios(agenciaId, "", "");
			if (usuariosJSON == null || usuariosJSON.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			this.logController.insert(new Log(new Constantes().INDICADOR_GETALL,
					usuariosJSON == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(usuariosJSON))));
			response.setData(usuariosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/listagem/{username}/{agenciaid}/{municipioid}")
	public ResponseEntity<Response<List<UsuarioJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaid") String agenciaId, @PathVariable("municipioid") String municipioId) {

		Response<List<UsuarioJSON>> response = new Response<List<UsuarioJSON>>();
		try {

			List<UsuarioJSON> usuariosJSON = this.getUsuarios(agenciaId, municipioId, "");
			if (usuariosJSON == null || usuariosJSON.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			this.logController.insert(new Log(new Constantes().INDICADOR_GETALL,
					usuariosJSON == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(usuariosJSON))));
			response.setData(usuariosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/listagem/{username}/{agenciaid}/{municipioid}/{prestadoraid}")
	public ResponseEntity<Response<List<UsuarioJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaid") String agenciaId, @PathVariable("municipioid") String municipioId,
			@PathVariable("prestadoraid") String prestadoraId) {

		Response<List<UsuarioJSON>> response = new Response<List<UsuarioJSON>>();
		try {

			List<UsuarioJSON> usuariosJSON = this.getUsuarios(agenciaId, municipioId, prestadoraId);
			if (usuariosJSON == null || usuariosJSON.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			this.logController.insert(new Log(new Constantes().INDICADOR_GETALL,
					usuariosJSON == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(usuariosJSON))));
			response.setData(usuariosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	private List<UsuarioJSON> getUsuarios(String agenciaId, String municipioId, String prestadoraId) {
		try {
			List<UsuarioTO> usuariosTO = this.usuarioRepository.findAll();
			if (usuariosTO == null || usuariosTO.isEmpty()) {
				return null;
			}

			List<UsuarioJSON> usuariosJSON = new ArrayList<UsuarioJSON>();

			for (UsuarioTO usuarioTO : usuariosTO) {
				usuarioTO.setSenha("***");
				if (agenciaId.isEmpty()) {
					usuariosJSON.add(new UsuarioJSON(usuarioTO));
				} else {
					if (municipioId.isEmpty()) {
						for (UsuarioAgenciaTO agenciaTO : usuarioTO.getUsuarioAgencias()) {
							if (agenciaTO.getAgenciaId().equals(agenciaId)) {
								usuariosJSON.add(new UsuarioJSON(usuarioTO));
								break;
							}
						}
					} else {
						if (prestadoraId.isEmpty()) {
							for (UsuarioAgenciaTO agenciaTO : usuarioTO.getUsuarioAgencias()) {
								if (agenciaTO.getAgenciaId().equals(agenciaId)) {
									for (UsuarioMunicipioTO municipioTO : agenciaTO.getUsuarioMunicipios()) {
										if (municipioTO.getMunicipioId().equals(municipioId)) {
											usuariosJSON.add(new UsuarioJSON(usuarioTO));
											break;
										}
									}
								}
							}
						} else {
							for (UsuarioAgenciaTO agenciaTO : usuarioTO.getUsuarioAgencias()) {
								if (agenciaTO.getAgenciaId().equals(agenciaId)) {
									for (UsuarioMunicipioTO municipioTO : agenciaTO.getUsuarioMunicipios()) {
										if (municipioTO.getMunicipioId().equals(municipioId)) {
											for (UsuarioPrestadoraTO prestadoraTO : municipioTO
													.getUsuarioPrestadoras()) {
												if (prestadoraTO.getPrestadoraId().equals(prestadoraId)) {
													usuariosJSON.add(new UsuarioJSON(usuarioTO));
													break;
												}
											}
										}
									}
								}
							}
						}
					}
				}

			}

			return usuariosJSON;
		} catch (Exception ex) {
			return null;
		}
	}

}
