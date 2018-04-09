package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.FuncionalidadeController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.FuncionalidadeJSON;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;
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
	public List<FuncionalidadeJSON> getAll() {
		List<FuncionalidadeTO> funcionalidades = this.funcionalidadeRepository.findAll();

		List<FuncionalidadeJSON> funcionalidadesJSON = new ArrayList<FuncionalidadeJSON>();

		for (FuncionalidadeTO to : funcionalidades) {
			funcionalidadesJSON.add(new FuncionalidadeJSON(to));
		}

		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(funcionalidadesJSON))));

		return funcionalidadesJSON;
	}

	@GetMapping("/nome/{nome}")
	public FuncionalidadeJSON getByNome(@PathVariable("nome") String nome) {
		FuncionalidadeTO funcionalidadeTO = this.funcionalidadeRepository.findByNome(nome);
		FuncionalidadeJSON funcionalidadeJSON = new FuncionalidadeJSON(funcionalidadeTO);

		this.logController.insert(
				new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_GETBYNOME, funcionalidadeJSON.toString()));
		return funcionalidadeJSON;
	}

	@PostMapping("/insert")
	public FuncionalidadeJSON insert(@RequestBody FuncionalidadeJSON funcionalidadeJSON) {
		try {

			FuncionalidadeTO funcionalidadeTO = new FuncionalidadeTO(funcionalidadeJSON);

			this.funcionalidadeRepository.insert(funcionalidadeTO);
			this.logController.insert(
					new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERT, funcionalidadeTO.toString()));
			return new FuncionalidadeJSON(funcionalidadeTO);
		} catch (Exception e) {
			System.out.println("Erro: CxFxCx00001 " + e.getMessage());
			return null;
		}
	}

	@PostMapping("/inserts")
	public List<FuncionalidadeJSON> inserts(@RequestBody List<FuncionalidadeJSON> funcionalidadesJSON) {
		try {

			List<FuncionalidadeTO> funcionalidadesTO = new ArrayList<FuncionalidadeTO>();
			for (FuncionalidadeJSON funcionalidadeJSON : funcionalidadesJSON) {
				funcionalidadesTO.add(new FuncionalidadeTO(funcionalidadeJSON));
			}

			this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(funcionalidadesTO))));
			this.funcionalidadeRepository.insert(funcionalidadesTO);

			funcionalidadesJSON.clear();
			for (FuncionalidadeTO funcionalidadeTO : funcionalidadesTO) {
				funcionalidadesJSON.add(new FuncionalidadeJSON(funcionalidadeTO));
			}

			return funcionalidadesJSON;
		} catch (Exception e) {
			System.out.println("Erro: CxFxCx00002 " + e.getMessage());
			this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTS,
					new Util().ListColectionToString(new ArrayList<Object>(funcionalidadesJSON))));
			return null;
		}
	}

	// TODO colocar os metodos de carga dentro do pacote rc.carga
	public void insertTxt(ArrayList<String> itens) {
		for (String item : itens) {
			this.insert(new FuncionalidadeJSON(item));
		}
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_INSERTTXT, itens.toString()));

	}

	public int rotinaCarga(ArrayList<String> itens) {
		int quantide = 0;
		this.logController.insert(new Log(new Constantes().LOG_FUNCIONALIDADE_CONTROLLER_CARGA, "Carga"));
		if (this.getAll().size() == 0) {
			this.insertTxt(itens);
		} else {
			for (String item : itens) {
				if (this.countByNome(item) == 0) {
					this.insert(new FuncionalidadeJSON(item));
					quantide++;
				}
			}
		}
		return quantide;
	}

}
