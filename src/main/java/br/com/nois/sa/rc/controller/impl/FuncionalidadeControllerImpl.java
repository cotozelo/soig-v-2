package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.FuncionalidadeController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Funcionalidade;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.FuncionalidadeRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/funcionalidade")
public class FuncionalidadeControllerImpl implements FuncionalidadeController {

	private FuncionalidadeRepository funcionalidadeRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public FuncionalidadeControllerImpl(FuncionalidadeRepository funcionalidadeRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.funcionalidadeRepository = funcionalidadeRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.funcionalidadeRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all")
	public List<Funcionalidade> getAll() {
		List<Funcionalidade> funcionalidades = this.funcionalidadeRepository.findAll();

		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(funcionalidades))));

		return funcionalidades;
	}

	@GetMapping("/nome/{nome}")
	public Funcionalidade getByNome(@PathVariable("nome") String nome) {
		Funcionalidade funcionalidade = this.funcionalidadeRepository.findByNome(nome);
		this.logController
				.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETBYNOME, funcionalidade.toString()));
		return funcionalidade;
	}

	@PutMapping("/insert")
	public Funcionalidade insert(Funcionalidade funcionalidade) {
		try {
			this.funcionalidadeRepository.insert(funcionalidade);
			this.logController
					.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERT, funcionalidade.toString()));
			return funcionalidade;
		} catch (Exception e) {
			System.out.println("Erro: CxFxCx00001 " + e.getMessage());
			return null;
		}
	}

	@PutMapping("/inserts")
	public List<Funcionalidade> inserts(@RequestBody List<Funcionalidade> funcionalidades) {

		try {
			this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(funcionalidades))));
			this.funcionalidadeRepository.insert(funcionalidades);
			return funcionalidades;
		} catch (Exception e) {
			System.out.println("Erro: CxFxCx00002 " + e.getMessage());
			this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(funcionalidades))));
			return null;
		}
	}

	@Override
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert(new Funcionalidade(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTTXT, itens.toString()));

	}

	@Override
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_CARGA, "Carga"));
		if (this.getAll().size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert(new Funcionalidade(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
