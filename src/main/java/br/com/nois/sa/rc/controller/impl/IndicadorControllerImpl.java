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

import br.com.nois.sa.rc.controller.IndicadorController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Indicador;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/indicador")
public class IndicadorControllerImpl implements IndicadorController {
	private IndicadorRepository indicadorRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public IndicadorControllerImpl(IndicadorRepository indicadorRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.indicadorRepository = indicadorRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/all")
	public List<Indicador> getAll() {
		List<Indicador> indicadores = this.indicadorRepository.findAll();

		this.logController.insert(new Log(new Constantes().INDICADOR_GETALL,
				indicadores == null ? "" : new Util().ListColectionToString(new ArrayList<Object>(indicadores))));

		return indicadores;
	}

	@GetMapping("/id/{id}")
	public Indicador getById(@PathVariable("id") String id) {
		Indicador indicador = this.indicadorRepository.findById(id);
		this.logController
				.insert(new Log(new Constantes().INDICADOR_GETBYID, indicador == null ? "" : indicador.toString()));
		return indicador;
	}

	@GetMapping("/sigla/{sigla}")
	public Indicador getBySigla(@PathVariable("sigla") String sigla) {
		Indicador indicador = this.indicadorRepository.findBySigla(sigla);
		this.logController
				.insert(new Log(new Constantes().INDICADOR_GETBYID, indicador == null ? "" : indicador.toString()));
		return indicador;
	}

	@PutMapping("/insert")
	public Indicador insert(@RequestBody Indicador indicador) {
		try {
			this.logController.insert(new Log(new Constantes().INDICADOR_INSERT, indicador.toString()));
			this.indicadorRepository.insert(indicador);
			return indicador;
		} catch (Exception e) {
			String error = "Erro: CxIxCx00011 ";
			this.logController.insert(new Log(new Constantes().INDICADOR_INSERT, error + e.getMessage()));
			System.out.println(error + e.getMessage());
			return new Indicador(error);
		}
	}

	@PostMapping("/update")
	public Indicador update(@RequestBody Indicador indicador) {
		try {

			Indicador indOdl = this.indicadorRepository.findById(indicador.getId());
			if (indOdl == null) {
				String error = "Erro: CxIxUx00012 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().INDICADOR_UPDATE, error));
				return new Indicador(error);
			}
			indOdl.update(indicador);
			this.logController.insert(new Log(new Constantes().INDICADOR_UPDATE, indOdl.toString()));

			indOdl = this.indicadorRepository.save(indOdl);
			return indOdl;
		} catch (Exception e) {
			String error = "Erro: CxIxUx00013 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().INDICADOR_UPDATE, error + e.getMessage()));

			return new Indicador(error);
		}
	}

	/// TODO talvez seja interessante colocar o como inativo ao invez
	/// de deleta-lo, avaliar quando implementar o CRUD
	@DeleteMapping("/delete/{id}")
	public Indicador deleteById(@PathVariable("id") String id) {
		try {
			Indicador indicador = this.indicadorRepository.findById(id);
			if (indicador == null) {
				String error = "Erro: CxIxDx00014 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().INDICADOR_DELETEBYID, error));
				return new Indicador(error);
			}

			this.logController.insert(new Log(new Constantes().INDICADOR_DELETEBYID, indicador.toString()));

			this.indicadorRepository.delete(indicador);
			return indicador;
		} catch (Exception e) {
			String error = "Erro: CxIxDx00015 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().INDICADOR_DELETEBYID, error + e.getMessage()));
			return new Indicador(error);
		}
	}

}
