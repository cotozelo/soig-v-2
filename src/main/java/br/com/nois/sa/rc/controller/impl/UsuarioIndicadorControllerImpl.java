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
import br.com.nois.sa.rc.controller.UsuarioIndicadorController;
import br.com.nois.sa.rc.model.UsuarioIndicador;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;

@RestController
@RequestMapping("/usuarioindicador")
public class UsuarioIndicadorControllerImpl implements UsuarioIndicadorController {
	private UsuarioRepository usuarioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UsuarioIndicadorControllerImpl(UsuarioRepository usuarioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/all/{idUsuario}")
	public List<UsuarioIndicador> getAll(@PathVariable("idUsuario") String idUsuario) {
		/*
		 * Usuario usuario = this.usuarioRepository.findById(idUsuario); if
		 * (usuario == null) { String error = "Erro: CxUxRx00036 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_GETALL, error)); return
		 * (List<UsuarioIndicador>) new UsuarioIndicador(error); }
		 * List<UsuarioIndicador> usuarioIndicadors =
		 * usuario.getUsuarioIndicadores();
		 * 
		 * this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_GETALL, usuarioIndicadors == null ? ""
		 * : usuarioIndicadors.toString()));
		 * 
		 * return usuarioIndicadors;
		 */
		return null;
	}

	@GetMapping("/id/{idUsuario}/{idUIndicador}")
	public UsuarioIndicador getById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUIndicador") String idUIndicador) {
		/*
		 * Usuario usuario = this.usuarioRepository.findById(idUsuario); if
		 * (usuario == null) { String error = "Erro: CxUxRx00037 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_GETBYID, error)); return new
		 * UsuarioIndicador(error); } UsuarioIndicador usuarioIndicador =
		 * usuario.getUsuarioIndicador(idUIndicador); if (usuarioIndicador ==
		 * null) { String error = "Erro: CxUxRx00038 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_GETBYID, error)); return new
		 * UsuarioIndicador(error); } this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_GETBYID, usuarioIndicador.toString()));
		 * return usuarioIndicador;
		 */
		return null;
	}

	@PutMapping("/insert/{idUsuario}")
	public UsuarioIndicador insert(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioIndicador usuarioIndicador) {
		/*
		 * Usuario usuario = this.usuarioRepository.findById(idUsuario); if
		 * (usuario == null) { String error = "Erro: CxUxCx00039 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_INSERT, error)); return new
		 * UsuarioIndicador(error); } if (usuarioIndicador.getId() == null) {
		 * usuarioIndicador.setId(); } usuarioIndicador =
		 * usuario.setUsuarioIndicador(usuarioIndicador); if (usuarioIndicador
		 * == null) { String error = "Erro: CxUxCx00040 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_INSERT, error)); return new
		 * UsuarioIndicador(error); } this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_INSERT, usuarioIndicador.toString()));
		 * 
		 * usuario = this.usuarioRepository.save(usuario); return
		 * usuarioIndicador;
		 */
		return null;
	}

	@PostMapping("/update/{idUsuario}")
	public UsuarioIndicador update(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioIndicador usuarioIndicador) {
		/*
		 * try { Usuario usuario = this.usuarioRepository.findById(idUsuario);
		 * if (usuario == null) { String error = "Erro: CxUxUx00041 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_UPDATE, error)); return new
		 * UsuarioIndicador(error); } usuarioIndicador =
		 * usuario.setUsuarioIndicador(usuarioIndicador); if (usuarioIndicador
		 * == null) { String error = "Erro: CxUxUx00042 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_UPDATE, error)); return new
		 * UsuarioIndicador(error); }
		 * 
		 * this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_UPDATE, usuarioIndicador.toString()));
		 * usuario = this.usuarioRepository.save(usuario);
		 * 
		 * return usuarioIndicador; } catch (Exception e) { String error =
		 * "Erro: CxUxUx00043 "; System.out.println(error + e.getMessage());
		 * this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_UPDATE, error + e.getMessage()));
		 * return new UsuarioIndicador(error); }
		 */
		return null;
	}

	@DeleteMapping("/delete/{idUsuario}/{idUIndicador}")
	public UsuarioIndicador deleteById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUIndicador") String idUIndicador) {
		/*
		 * try { Usuario usuario = this.usuarioRepository.findById(idUsuario);
		 * if (usuario == null) { String error = "Erro: CxUxDx00044 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_DELETEBYID, error)); return new
		 * UsuarioIndicador(error); } UsuarioIndicador usuarioIndicador =
		 * usuario.getUsuarioIndicador(idUIndicador); if (usuarioIndicador ==
		 * null) { String error = "Erro: CxUxDx00045 ";
		 * System.out.println(error); this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_DELETEBYID, error)); return new
		 * UsuarioIndicador(error); }
		 * 
		 * usuario.removeUsuarioIndicador(usuarioIndicador);
		 * 
		 * this.logController .insert(new Log(new
		 * Constantes().USUARIOINDICADOR_DELETEBYID,
		 * usuarioIndicador.toString())); this.usuarioRepository.save(usuario);
		 * 
		 * return usuarioIndicador; } catch (Exception e) { String error =
		 * "Erro: CxUxDx00035 "; System.out.println(error + e.getMessage());
		 * this.logController.insert(new Log(new
		 * Constantes().USUARIOINDICADOR_DELETEBYID, error + e.getMessage()));
		 * return new UsuarioIndicador(error); }
		 */
		return null;
	}

}
