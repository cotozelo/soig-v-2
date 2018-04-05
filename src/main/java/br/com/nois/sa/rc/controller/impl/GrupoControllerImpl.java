package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.GrupoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Grupo;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.GrupoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/grupo")
public class GrupoControllerImpl implements GrupoController {

	private GrupoRepository grupoRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public GrupoControllerImpl(GrupoRepository grupoRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		super();
		this.grupoRepository = grupoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.grupoRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all")
	public List<Grupo> getAll() {
		List<Grupo> grupos = this.grupoRepository.findAll();
		this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(grupos))));
		return grupos;
	}

	@PutMapping("/insert")
	public Grupo insert(Grupo grupo) {
		try {
			this.grupoRepository.insert(grupo);
			this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_INSERT, grupo.toString()));
			return grupo;
		} catch (Exception e) {
			System.out.println("Erro: CxGxCx00001 " + e.getMessage());
			return null;
		}
	}

	@Override
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert(new Grupo(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_INSERTTXT, itens.toString()));
	}

	@Override
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_GRUPO_CONTROLLER_CARGA, "Carga"));
		if (this.getAll().size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert(new Grupo(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
