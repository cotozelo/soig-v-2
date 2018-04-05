package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.InclinacaoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Inclinacao;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.InclinacaoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/inclinacao")
public class InclinacaoControllerImpl implements InclinacaoController {

	private InclinacaoRepository inclinacaoRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public InclinacaoControllerImpl(InclinacaoRepository inclinacaoRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		super();
		this.inclinacaoRepository = inclinacaoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	public Long countByNome(String nome) {
		Long qtde = this.inclinacaoRepository.countByNome(nome);
		this.logController.insert(new Log(new Constantes().LOG_INCLINACAO_CONTROLLER_COUNTBYNOME, nome));
		return qtde;
	}

	@GetMapping("/all")
	public List<Inclinacao> getAll() {
		List<Inclinacao> inclinacaos = this.inclinacaoRepository.findAll();
		this.logController.insert(new Log(new Constantes().LOG_INCLINACAO_CONTROLLER_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(inclinacaos))));
		return inclinacaos;
	}

	@PutMapping("/insert")
	public Inclinacao insert(Inclinacao inclinacao) {
		try {
			this.inclinacaoRepository.insert(inclinacao);
			this.logController
					.insert(new Log(new Constantes().LOG_INCLINACAO_CONTROLLER_INSERT, inclinacao.toString()));
			return inclinacao;
		} catch (Exception e) {
			System.out.println("Erro: CxIxCx00001 " + e.getMessage());
			return null;
		}
	}

	@Override
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert(new Inclinacao(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_INCLINACAO_CONTROLLER_INSERTTXT, itens.toString()));
	}

	@Override
	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_INCLINACAO_CONTROLLER_CARGA, "Carga"));
		if (this.getAll().size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert(new Inclinacao(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
