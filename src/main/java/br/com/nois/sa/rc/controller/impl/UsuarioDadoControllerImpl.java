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
import br.com.nois.sa.rc.controller.UsuarioDadoController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.model.UsuarioDado;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/usuariodado")
public class UsuarioDadoControllerImpl implements UsuarioDadoController {
	private UsuarioRepository usuarioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UsuarioDadoControllerImpl(UsuarioRepository usuarioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all/{idUsuario}")
	public List<UsuarioDado> getAll(@PathVariable("idUsuario") String idUsuario) {
		Usuario usuario = this.usuarioRepository.findById(idUsuario);
		if (usuario == null) {
			String error = "Erro: CxUxRx00047 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIODADO_GETALL, error));
			return (List<UsuarioDado>) new UsuarioDado(error);
		}
		List<UsuarioDado> usuarioDados = usuario.getUsuarioDados();

		this.logController.insert(
				new Log(new Constantes().USUARIODADO_GETALL, usuarioDados == null ? "" : usuarioDados.toString()));

		return usuarioDados;
	}

	@GetMapping("/id/{idUsuario}/{idUDado}")
	public UsuarioDado getById(@PathVariable("idUsuario") String idUsuario, @PathVariable("idUDado") String idUDado) {
		Usuario usuario = this.usuarioRepository.findById(idUsuario);
		if (usuario == null) {
			String error = "Erro: CxUxRx00048 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIODADO_GETBYID, error));
			return new UsuarioDado(error);
		}
		UsuarioDado usuarioDado = usuario.getUsuarioDado(idUDado);
		if (usuarioDado == null) {
			String error = "Erro: CxUxRx00049 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIODADO_GETBYID, error));
			return new UsuarioDado(error);
		}
		this.logController.insert(new Log(new Constantes().USUARIODADO_GETBYID, usuarioDado.toString()));
		return usuarioDado;
	}

	@PutMapping("/insert/{idUsuario}")
	public UsuarioDado insert(@PathVariable("idUsuario") String idUsuario, @RequestBody UsuarioDado usuarioDado) {
		Usuario usuario = this.usuarioRepository.findById(idUsuario);
		if (usuario == null) {
			String error = "Erro: CxUxCx00050 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIODADO_INSERT, error));
			return new UsuarioDado(error);
		}
		if (usuarioDado.getId() == null) {
			usuarioDado.setId();
		}
		usuarioDado = usuario.setUsuarioDado(usuarioDado);
		if (usuarioDado == null) {
			String error = "Erro: CxUxCx00051 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIODADO_INSERT, error));
			return new UsuarioDado(error);
		}
		this.logController.insert(new Log(new Constantes().USUARIODADO_INSERT, usuarioDado.toString()));

		usuario = this.usuarioRepository.save(usuario);
		return usuarioDado;
	}

	@PostMapping("/update/{idUsuario}")
	public UsuarioDado update(@PathVariable("idUsuario") String idUsuario, @RequestBody UsuarioDado usuarioDado) {
		try {
			Usuario usuario = this.usuarioRepository.findById(idUsuario);
			if (usuario == null) {
				String error = "Erro: CxUxUx00052 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIODADO_UPDATE, error));
				return new UsuarioDado(error);
			}
			usuarioDado = usuario.setUsuarioDado(usuarioDado);
			if (usuarioDado == null) {
				String error = "Erro: CxUxUx00053 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIODADO_UPDATE, error));
				return new UsuarioDado(error);
			}

			this.logController.insert(new Log(new Constantes().USUARIODADO_UPDATE, usuarioDado.toString()));
			usuario = this.usuarioRepository.save(usuario);

			return usuarioDado;
		} catch (Exception e) {
			String error = "Erro: CxUxUx00054 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIODADO_UPDATE, error + e.getMessage()));
			return new UsuarioDado(error);
		}
	}

	@DeleteMapping("/delete/{idUsuario}/{idUDado}")
	public UsuarioDado deleteById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUDado") String idUDado) {
		try {
			Usuario usuario = this.usuarioRepository.findById(idUsuario);
			if (usuario == null) {
				String error = "Erro: CxUxDx00055 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIODADO_DELETEBYID, error));
				return new UsuarioDado(error);
			}
			UsuarioDado usuarioDado = usuario.getUsuarioDado(idUDado);
			if (usuarioDado == null) {
				String error = "Erro: CxUxDx00056 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIODADO_DELETEBYID, error));
				return new UsuarioDado(error);
			}

			usuario.removeUsuarioDado(usuarioDado);

			this.logController.insert(new Log(new Constantes().USUARIODADO_DELETEBYID, usuarioDado.toString()));
			this.usuarioRepository.save(usuario);

			return usuarioDado;
		} catch (Exception e) {
			String error = "Erro: CxUxDx00057 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIODADO_DELETEBYID, error + e.getMessage()));
			return new UsuarioDado(error);
		}
	}

}
