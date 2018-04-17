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

import br.com.nois.sa.rc.controller.DadoController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.to.DadoTO;
import br.com.nois.sa.rc.repository.DadoRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/dado")
public class DadoControllerImpl implements DadoController {
	private DadoRepository dadoRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public DadoControllerImpl(DadoRepository dadoRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.dadoRepository = dadoRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/all")
	public List<DadoTO> getAll() {
		List<DadoTO> dados = this.dadoRepository.findAll();

		this.logController.insert(new Log(new Constantes().DADO_GETALL,
				dados == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(dados))));

		return dados;
	}

	@GetMapping("/id/{id}")
	public DadoTO getById(@PathVariable("id") String id) {
		DadoTO dado = this.dadoRepository.findById(id);
		this.logController.insert(new Log(new Constantes().DADO_GETBYID, dado == null ? "" : dado.toString()));
		return dado;
	}

	@PutMapping("/insert")
	public DadoTO insert(@RequestBody DadoTO dado) {
		try {
			this.logController.insert(new Log(new Constantes().DADO_INSERT, dado.toString()));
			this.dadoRepository.insert(dado);
			return dado;
		} catch (Exception e) {
			String error = "Erro: CxDxCx00016 ";
			this.logController.insert(new Log(new Constantes().DADO_INSERT, error + e.getMessage()));
			System.out.println(error + e.getMessage());
			return new DadoTO();
		}
	}

	@PostMapping("/update")
	public DadoTO update(@RequestBody DadoTO dado) {
		try {
			this.logController.insert(new Log(new Constantes().DADO_UPDATE, dado.toString()));
			return this.dadoRepository.save(dado);
		} catch (Exception e) {
			String error = "Erro: CxDxUx00017";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().DADO_UPDATE, error + e.getMessage()));

			return new DadoTO();
		}
	}

	/// TODO talvez seja interessante colocar o como inativo ao invez
	/// de deleta-lo, avaliar quando implementar o CRUD
	@DeleteMapping("/delete/{id}")
	public DadoTO deleteById(@PathVariable("id") String id) {
		try {
			DadoTO dado = this.dadoRepository.findById(id);

			if (dado == null) {
				String error = "Erro: CxDxDx00018 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().DADO_DELETEBYID, error));
				return new DadoTO();
			}

			this.logController.insert(new Log(new Constantes().DADO_DELETEBYID, dado.toString()));

			this.dadoRepository.delete(dado);
			return dado;
		} catch (Exception e) {
			String error = "Erro: CxDxDx00019 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().DADO_DELETEBYID, error + e.getMessage()));
			return new DadoTO();
		}
	}
}
