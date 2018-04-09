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
import br.com.nois.sa.rc.controller.PerfilController;
import br.com.nois.sa.rc.model.Log;
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
	private List<FuncionalidadeTO> funcionalidades;

	public PerfilControllerImpl(PerfilRepository perfilRepository, FuncionalidadeRepository funcionalidadeRepository,
			LogRepository logRepository, VersaoRepository versaoRepository) {

		this.perfilRepository = perfilRepository;
		this.funcionalidadeRepository = funcionalidadeRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);

		funcionalidades = this.funcionalidadeRepository.findAll();
	}

	@Override
	@GetMapping("/all/{username}")
	public List<PerfilJSON> getAll(@PathVariable("username") String userName) {
		List<PerfilTO> perfilsTO = this.perfilRepository.findAll();
		List<PerfilJSON> perfilsJSON = new ArrayList<PerfilJSON>();

		for (PerfilTO perfilTO : perfilsTO) {
			perfilsJSON.add(PerfilToJson(perfilTO));
		}

		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(perfilsJSON))));

		return perfilsJSON;
	}

	@Override
	@PutMapping("/update/{username}")
	public List<PerfilJSON> update(@PathVariable("username") String userName,
			@RequestBody List<PerfilJSON> perfilsJSON) {

		try {
			for (PerfilJSON perfilJSON : perfilsJSON) {
				PerfilTO perfilTO = new PerfilTO(perfilJSON);

				this.logController
						.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL, perfilTO.toString()));

				this.perfilRepository.save(perfilTO);
			}
			return perfilsJSON;
		} catch (Exception ex) {

		}
		return null;
	}

	@Override
	@DeleteMapping("/{username}/{id}")
	public PerfilJSON deleteById(@PathVariable("username") String userName, @PathVariable("id") String id) {
		PerfilTO perfil = this.perfilRepository.findById(id);

		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL, perfil.toString()));

		this.perfilRepository.delete(perfil);

		return PerfilToJson(perfil);
	}

	@Override
	@PostMapping("/insert/{username}")
	public PerfilJSON insert(@PathVariable("username") String userName, @RequestBody PerfilJSON perfil) {
		try {
			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, perfil.toString()));
			this.perfilRepository.insert(new PerfilTO(perfil));
			return perfil;
		} catch (Exception e) {
			String error = "Erro: CxUxCx00020 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().USUARIO_INSERT, error + e.getMessage()));
			return null;
		}
	}

	private PerfilJSON PerfilToJson(PerfilTO to) {

		PerfilJSON perfilsJSON = new PerfilJSON(to);
		perfilsJSON.sincFuncionalidades(this.funcionalidades);
		return perfilsJSON;
	}
}
