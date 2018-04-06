package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
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
import br.com.nois.sa.rc.controller.UsuarioController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllerImpl implements UsuarioController {
	private UsuarioRepository usuarioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UsuarioControllerImpl(UsuarioRepository usuarioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/all")
	public List<Usuario> getAll() {
		List<Usuario> usuarios = this.usuarioRepository.findAll();

		this.logController.insert(new Log(new Constantes().USUARIO_GETALL,
				usuarios == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(usuarios))));

		return usuarios;
	}

	@GetMapping("/id/{id}")
	public Usuario getById(@PathVariable("id") String id) {
		Usuario usuario = this.usuarioRepository.findById(id);
		this.logController.insert(new Log(new Constantes().USUARIO_GETBYID, usuario == null ? "" : usuario.toString()));
		return usuario;
	}
	
	@GetMapping("/informacao/{nomeDeUsuario}")
	public Usuario getByNomeDeUsuario(@PathVariable("nomeDeUsuario") String nomeDeUsuario) {
		Usuario usuario = this.usuarioRepository.findByNomeDeUsuario(nomeDeUsuario);
		this.logController.insert(new Log(new Constantes().USUARIO_GETBYNOMEDEUSUARIO, usuario == null ? "" : usuario.toString()));
		return usuario;
	}

	@PutMapping("/insert")
	public Usuario insert(@RequestBody Usuario usuario) {
		try {
			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, usuario.toString()));
			this.usuarioRepository.insert(usuario);
			return usuario;
		} catch (Exception e) {
			String error = "Erro: CxUxCx00020 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, error + e.getMessage()));
			return new Usuario(error);
		}
	}

	@PostMapping("/update")
	public Usuario update(@RequestBody Usuario usuario) {
		try {
			this.logController.insert(new Log(new Constantes().USUARIO_UPDATE, usuario.toString()));
			return this.usuarioRepository.save(usuario);
		} catch (Exception e) {
			String error = "Erro: CxUxUx00021";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIO_UPDATE, error + e.getMessage()));

			return new Usuario(error);
		}
	}

	/// TODO talvez seja interessante colocar o como inativo ao invez
	/// de deleta-lo, avaliar quando implementar o CRUD
	@DeleteMapping("/delete/{id}")
	public Usuario deleteById(String id) {
		try {
			Usuario usuario = this.usuarioRepository.findById(id);

			if (usuario == null) {
				String error = "Erro: CxUxDx00022 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().USUARIO_DELETEBYID, error));
				return new Usuario(error);
			}

			this.logController.insert(new Log(new Constantes().USUARIO_DELETEBYID, usuario.toString()));

			this.usuarioRepository.delete(usuario);
			return usuario;
		} catch (Exception e) {
			String error = "Erro: CxUxDx00023 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIO_DELETEBYID, error + e.getMessage()));
			return new Usuario(error);
		}
	}
}
