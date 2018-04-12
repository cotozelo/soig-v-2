package br.com.nois.sa.rc.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.UsuarioController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.UsuarioJSON;
import br.com.nois.sa.rc.model.to.ErroTO;
import br.com.nois.sa.rc.model.to.ErroTO.ErroEnum;
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

	@PostMapping("/insertAll/{username}")
	public Object insert(@PathVariable("username") String username, @RequestBody UsuarioJSON usuarioJSON) {
		try {
			UsuarioTO usurioTO = new UsuarioTO(usuarioJSON);

			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, usurioTO.toString()));
			this.usuarioRepository.insert(usurioTO);

			usuarioJSON = new UsuarioJSON(usurioTO);

			return usuarioJSON;
		} catch (Exception ex) {
			return new ErroTO(ex, ErroEnum.INVALIDO, this.getClass().getName() + "/insertAll/" + username);
		}
	}

	@GetMapping("/informacao/{username}")
	public Object getInformacao(@PathVariable("username") String userName) {

		try {
			UsuarioTO usuarioTO = this.usuarioRepository.findByNomeDeUsuario(userName);
			if (usuarioTO != null) {
				this.logController.insert(
						new Log(new Constantes().USUARIO_GETALL, usuarioTO == null ? "" : usuarioTO.toString()));

				UsuarioJSON usuarioJSON = new UsuarioJSON(usuarioTO);

				PerfilTO perfilTO = this.perfilRepository.findById(usuarioTO.getPerfilId());

				if (perfilTO != null) {
					usuarioJSON.setUsuarioFuncionalidades(perfilTO.getFuncionalidades());
				}

				return usuarioJSON;
			} else {
				return new ErroTO(ErroEnum.GET_VAZIO, "VxUxRx00001", "",
						this.getClass().getName() + "/informacao/" + userName);
			}
		} catch (Exception ex) {
			return new ErroTO(ex, ErroEnum.GET, this.getClass().getName() + "/informacao/" + userName);
		}
	}

	/*
	 * 
	 * @GetMapping("/all") public List<Usuario> getAll() { List<Usuario>
	 * usuarios = this.usuarioRepository.findAll();
	 * 
	 * this.logController.insert(new Log(new Constantes().USUARIO_GETALL,
	 * usuarios == null ? "" : new Util().ListColectionToString(new
	 * ArrayList<Object>(usuarios))));
	 * 
	 * return usuarios; }
	 * 
	 * @GetMapping("/id/{id}") public Usuario getById(@PathVariable("id") String
	 * id) { Usuario usuario = this.usuarioRepository.findById(id);
	 * this.logController.insert(new Log(new Constantes().USUARIO_GETBYID,
	 * usuario == null ? "" : usuario.toString())); return usuario; }
	 * 
	 * @GetMapping("/informacao/{nomeDeUsuario}") public Usuario
	 * getByNomeDeUsuario(@PathVariable("nomeDeUsuario") String nomeDeUsuario) {
	 * Usuario usuario =
	 * this.usuarioRepository.findByNomeDeUsuario(nomeDeUsuario);
	 * this.logController.insert(new Log(new
	 * Constantes().USUARIO_GETBYNOMEDEUSUARIO, usuario == null ? "" :
	 * usuario.toString())); return usuario; }
	 * 
	 * @PutMapping("/insert") public Usuario insert(@RequestBody Usuario
	 * usuario) { try { this.logController.insert(new Log(new
	 * Constantes().USUARIO_INSERT, usuario.toString()));
	 * this.usuarioRepository.insert(usuario); return usuario; } catch
	 * (Exception e) { String error = "Erro: CxUxCx00020 ";
	 * System.out.println(error + e.getMessage()); this.logController.insert(new
	 * Log(new Constantes().USUARIO_INSERT, error + e.getMessage())); return new
	 * Usuario(error); } }
	 * 
	 * @PostMapping("/update") public Usuario update(@RequestBody Usuario
	 * usuario) { try { this.logController.insert(new Log(new
	 * Constantes().USUARIO_UPDATE, usuario.toString())); return
	 * this.usuarioRepository.save(usuario); } catch (Exception e) { String
	 * error = "Erro: CxUxUx00021"; System.out.println(error + e.getMessage());
	 * this.logController.insert(new Log(new Constantes().USUARIO_UPDATE, error
	 * + e.getMessage()));
	 * 
	 * return new Usuario(error); } }
	 * 
	 * /// TODO talvez seja interessante colocar o como inativo ao invez /// de
	 * deleta-lo, avaliar quando implementar o CRUD
	 * 
	 * @DeleteMapping("/delete/{id}") public Usuario deleteById(String id) { try
	 * { Usuario usuario = this.usuarioRepository.findById(id);
	 * 
	 * if (usuario == null) { String error = "Erro: CxUxDx00022 ";
	 * System.out.println(error); this.logController.insert(new Log(new
	 * Constantes().USUARIO_DELETEBYID, error)); return new Usuario(error); }
	 * 
	 * this.logController.insert(new Log(new Constantes().USUARIO_DELETEBYID,
	 * usuario.toString()));
	 * 
	 * this.usuarioRepository.delete(usuario); return usuario; } catch
	 * (Exception e) { String error = "Erro: CxUxDx00023 ";
	 * System.out.println(error + e.getMessage()); this.logController.insert(new
	 * Log(new Constantes().USUARIO_DELETEBYID, error + e.getMessage())); return
	 * new Usuario(error); } }
	 */
}
