package br.com.nois.sa.rc.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.UsuarioMunicipioController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.UsuarioMunicipio;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/usuarioentidade")
public class UsuarioMunicipioControllerImpl implements UsuarioMunicipioController {
	private UsuarioRepository usuarioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UsuarioMunicipioControllerImpl(UsuarioRepository usuarioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/all/{idUsuario}")
	public List<UsuarioMunicipio> getAll(@PathVariable("idUsuario") String idUsuario) {
		/*
		 * Usuario usuario = this.usuarioRepository.findById(idUsuario); if
		 * (usuario == null) { String error = "Erro: CxUxRx00025 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_GETALL, error)); return
		 * (List<UsuarioMunicipio>) new UsuarioMunicipio(error); }
		 * List<UsuarioMunicipio> usuarioMunicipios =
		 * usuario.getUsuarioMunicipios();
		 * 
		 * this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_GETALL, usuarioMunicipios == null ? "" :
		 * usuarioMunicipios.toString()));
		 * 
		 * return usuarioMunicipios;
		 */
		return null;
	}

	@GetMapping("/id/{idUsuario}/{idUMunicipio}")
	public UsuarioMunicipio getById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUMunicipio") String idUMunicipio) {
		/*
		 * Usuario usuario = this.usuarioRepository.findById(idUsuario); if
		 * (usuario == null) { String error = "Erro: CxUxRx00026 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_GETBYID, error)); return new
		 * UsuarioMunicipio(error); } UsuarioMunicipio usuarioMunicipio =
		 * usuario.getUsuarioMunicipio(idUMunicipio); if (usuarioMunicipio ==
		 * null) { String error = "Erro: CxUxRx00027 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_GETBYID, error)); return new
		 * UsuarioMunicipio(error); } this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_GETBYID, usuarioMunicipio.toString()));
		 * return usuarioMunicipio;
		 */
		return null;
	}

	@PutMapping("/insert/{idUsuario}")
	public UsuarioMunicipio insert(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioMunicipio usuarioMunicipio) {
		/*
		 * Usuario usuario = this.usuarioRepository.findById(idUsuario); if
		 * (usuario == null) { String error = "Erro: CxUxCx00028 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_INSERT, error)); return new
		 * UsuarioMunicipio(error); } if (usuarioMunicipio.getId() == null) {
		 * usuarioMunicipio.setId(); } usuarioMunicipio =
		 * usuario.setUsuarioMunicipio(usuarioMunicipio); if (usuarioMunicipio
		 * == null) { String error = "Erro: CxUxCx00029 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_INSERT, error)); return new
		 * UsuarioMunicipio(error); } this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_INSERT, usuarioMunicipio.toString()));
		 * 
		 * usuario = this.usuarioRepository.save(usuario); return
		 * usuarioMunicipio;
		 */
		return null;
	}

	@PostMapping("/update/{idUsuario}")
	public UsuarioMunicipio update(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioMunicipio usuarioMunicipio) {
		/*
		 * try { Usuario usuario = this.usuarioRepository.findById(idUsuario);
		 * if (usuario == null) { String error = "Erro: CxUxUx00030 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_UPDATE, error)); return new
		 * UsuarioMunicipio(error); } usuarioMunicipio =
		 * usuario.setUsuarioMunicipio(usuarioMunicipio); if (usuarioMunicipio
		 * == null) { String error = "Erro: CxUxUx00031 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_UPDATE, error)); return new
		 * UsuarioMunicipio(error); }
		 * 
		 * this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_UPDATE, usuarioMunicipio.toString()));
		 * usuario = this.usuarioRepository.save(usuario);
		 * 
		 * return usuarioMunicipio; } catch (Exception e) { String error =
		 * "Erro: CxUxUx00032 "; System.out.println(error + e.getMessage());
		 * this.logController.insert(new Log(new
		 * Constantes().USUARIOENTIDADE_UPDATE, error + e.getMessage())); return
		 * new UsuarioMunicipio(error); }
		 */
		return null;
	}

	@DeleteMapping("/delete/{idUsuario}/{idUMunicipio}")
	public UsuarioMunicipio deleteById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUMunicipio") String idUMunicipio) {
		try {
			/*
			 * Usuario usuario = this.usuarioRepository.findById(idUsuario); if
			 * (usuario == null) { String error = "Erro: CxUxDx00033 ";
			 * System.out.println(error); this.logController.insert(new Log(new
			 * Constantes().USUARIOENTIDADE_DELETEBYID, error)); return new
			 * UsuarioMunicipio(error); } UsuarioMunicipio usuarioMunicipio =
			 * usuario.getUsuarioMunicipio(idUMunicipio); if (usuarioMunicipio
			 * == null) { String error = "Erro: CxUxDx00034 ";
			 * System.out.println(error); this.logController.insert(new Log(new
			 * Constantes().USUARIOENTIDADE_DELETEBYID, error)); return new
			 * UsuarioMunicipio(error); }
			 * 
			 * usuario.removeUsuarioMunicipio(usuarioMunicipio);
			 * 
			 * this.logController .insert(new Log(new
			 * Constantes().USUARIOENTIDADE_DELETEBYID,
			 * usuarioMunicipio.toString()));
			 * this.usuarioRepository.save(usuario);
			 * 
			 * return usuarioMunicipio;
			 */
			return null;
		} catch (Exception e) {
			String error = "Erro: CxUxDx00035 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_DELETEBYID, error + e.getMessage()));
			return new UsuarioMunicipio(error);
		}
	}
}
