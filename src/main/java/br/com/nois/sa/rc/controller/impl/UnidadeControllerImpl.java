package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.UnidadeController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.Unidade;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.UnidadeRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/unidade")
public class UnidadeControllerImpl implements UnidadeController {

	private UnidadeRepository unidadeRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public UnidadeControllerImpl(UnidadeRepository unidadeRepository, LogRepository logRepository,
			VersaoRepository versaoRespository) {
		super();
		this.unidadeRepository = unidadeRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRespository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.unidadeRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all")
	public List<Unidade> getAll() {
		List<Unidade> unidades = this.unidadeRepository.findAll();
		this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(unidades))));
		return unidades;
	}

	@PutMapping("/insert")
	public Unidade insert(Unidade unidade) {
		try {
			this.unidadeRepository.insert(unidade);
			this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_INSERT, unidade.toString()));
			return unidade;
		} catch (Exception e) {
			System.out.println("Erro: CxUnxCx00001 " + e.getMessage());
			return null;
		}
	}

	@Override
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert(new Unidade(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_INSERTTXT, itens.toString()));
	}

	@Override
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_UNIDADE_CONTROLLER_CARGA, "Carga"));
		if (this.getAll().size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert(new Unidade(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
