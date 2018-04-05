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
import br.com.nois.sa.rc.controller.UsuarioEntidadeController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.model.UsuarioEntidade;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/usuarioentidade")
public class UsuarioEntidadeControllerImpl implements UsuarioEntidadeController {
	private UsuarioRepository usuarioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UsuarioEntidadeControllerImpl(UsuarioRepository usuarioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all/{idUsuario}")
	public List<UsuarioEntidade> getAll(@PathVariable("idUsuario") String idUsuario) {
		Usuario usuario = this.usuarioRepository.findById(idUsuario);
		if (usuario == null) {
			String error = "Erro: CxUxRx00025 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_GETALL, error));
			return (List<UsuarioEntidade>) new UsuarioEntidade(error);
		}
		List<UsuarioEntidade> usuarioEntidades = usuario.getUsuarioEntidades();

		this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_GETALL,
				usuarioEntidades == null ? "" : usuarioEntidades.toString()));

		return usuarioEntidades;
	}

	@GetMapping("/id/{idUsuario}/{idUEntidade}")
	public UsuarioEntidade getById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUEntidade") String idUEntidade) {
		Usuario usuario = this.usuarioRepository.findById(idUsuario);
		if (usuario == null) {
			String error = "Erro: CxUxRx00026 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_GETBYID, error));
			return new UsuarioEntidade(error);
		}
		UsuarioEntidade usuarioEntidade = usuario.getUsuarioEntidade(idUEntidade);
		if (usuarioEntidade == null) {
			String error = "Erro: CxUxRx00027 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_GETBYID, error));
			return new UsuarioEntidade(error);
		}
		this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_GETBYID, usuarioEntidade.toString()));
		return usuarioEntidade;
	}

	@PutMapping("/insert/{idUsuario}")
	public UsuarioEntidade insert(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioEntidade usuarioEntidade) {
		Usuario usuario = this.usuarioRepository.findById(idUsuario);
		if (usuario == null) {
			String error = "Erro: CxUxCx00028 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_INSERT, error));
			return new UsuarioEntidade(error);
		}
		if (usuarioEntidade.getId() == null) {
			usuarioEntidade.setId();
		}
		usuarioEntidade = usuario.setUsuarioEntidade(usuarioEntidade);
		if (usuarioEntidade == null) {
			String error = "Erro: CxUxCx00029 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_INSERT, error));
			return new UsuarioEntidade(error);
		}
		this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_INSERT, usuarioEntidade.toString()));

		usuario = this.usuarioRepository.save(usuario);
		return usuarioEntidade;
	}

	@PostMapping("/update/{idUsuario}")
	public UsuarioEntidade update(@PathVariable("idUsuario") String idUsuario,
			@RequestBody UsuarioEntidade usuarioEntidade) {
		try {
			Usuario usuario = this.usuarioRepository.findById(idUsuario);
			if (usuario == null) {
				String error = "Erro: CxUxUx00030 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_UPDATE, error));
				return new UsuarioEntidade(error);
			}
			usuarioEntidade = usuario.setUsuarioEntidade(usuarioEntidade);
			if (usuarioEntidade == null) {
				String error = "Erro: CxUxUx00031 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_UPDATE, error));
				return new UsuarioEntidade(error);
			}

			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_UPDATE, usuarioEntidade.toString()));
			usuario = this.usuarioRepository.save(usuario);

			return usuarioEntidade;
		} catch (Exception e) {
			String error = "Erro: CxUxUx00032 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_UPDATE, error + e.getMessage()));
			return new UsuarioEntidade(error);
		}
	}

	@DeleteMapping("/delete/{idUsuario}/{idUEntidade}")
	public UsuarioEntidade deleteById(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("idUEntidade") String idUEntidade) {
		try {
			Usuario usuario = this.usuarioRepository.findById(idUsuario);
			if (usuario == null) {
				String error = "Erro: CxUxDx00033 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_DELETEBYID, error));
				return new UsuarioEntidade(error);
			}
			UsuarioEntidade usuarioEntidade = usuario.getUsuarioEntidade(idUEntidade);
			if (usuarioEntidade == null) {
				String error = "Erro: CxUxDx00034 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_DELETEBYID, error));
				return new UsuarioEntidade(error);
			}

			usuario.removeUsuarioEntidade(usuarioEntidade);

			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_DELETEBYID, usuarioEntidade.toString()));
			this.usuarioRepository.save(usuario);

			return usuarioEntidade;
		} catch (Exception e) {
			String error = "Erro: CxUxDx00035 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIOENTIDADE_DELETEBYID, error + e.getMessage()));
			return new UsuarioEntidade(error);
		}
	}

}
