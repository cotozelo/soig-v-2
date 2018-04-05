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

import br.com.nois.sa.rc.controller.AgenciaController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Agencia;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.repository.AgenciaRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/agencia")
public class AgenciaControllerImpl implements AgenciaController {
	private AgenciaRepository agenciaRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public AgenciaControllerImpl(AgenciaRepository agenciaRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.agenciaRepository = agenciaRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all")
	public List<Agencia> getAll() {
		List<Agencia> agencias = this.agenciaRepository.findAll();

		if (agencias == null) {
			String error = "Erro: CxAxRx00058 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().AGENCIA_GETALL, error));
			return (List<Agencia>) new Agencia(error);
		}
		this.logController.insert(new Log(new Constantes().AGENCIA_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(agencias))));

		return agencias;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/allWhitOutEntidade")
	public List<Agencia> getAllWhitOutEntidade() {
		List<Agencia> agencias = this.agenciaRepository.findAtivoReturnNoEntidadesQuery(true);

		if (agencias == null) {
			String error = "Erro: CxAxRx00058 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().AGENCIA_GETALL, error));
			return (List<Agencia>) new Agencia(error);
		}
		this.logController.insert(new Log(new Constantes().AGENCIA_GETALL,
				new Util().ListColectionToString(new ArrayList<Object>(agencias))));

		return agencias;
	}

	@GetMapping("/id/{id}")
	public Agencia getById(@PathVariable("id") String id) {
		Agencia agencia = this.agenciaRepository.findById(id);
		if (agencia == null) {
			String error = "Erro: CxAxRx00059 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().AGENCIA_GETBYID, error));
			return new Agencia(error);
		}
		this.logController.insert(new Log(new Constantes().AGENCIA_GETBYID, agencia.toString()));
		return agencia;
	}

	@PostMapping("/insert")
	public Agencia insert(@RequestBody Agencia agencia) {
		try {
			this.logController.insert(new Log(new Constantes().AGENCIA_INSERT, agencia.toString()));
			this.agenciaRepository.insert(agencia);
			return agencia;
		} catch (Exception e) {
			String error = "Erro: CxAxCx00060 ";
			this.logController.insert(new Log(new Constantes().AGENCIA_INSERT, error + e.getMessage()));
			System.out.println(error + e.getMessage());
			return new Agencia(error);
		}
	}

	@PutMapping("/update")
	public Agencia update(@RequestBody Agencia agencia) {
		try {
			Agencia agenciaIn = this.agenciaRepository.findById(agencia.getId());
			if (agenciaIn == null) {
				String error = "Erro: CxAxUx00064 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().AGENCIA_UPDATE, error));
				return new Agencia(error);
			}
			agenciaIn.update(agencia);

			this.logController.insert(new Log(new Constantes().AGENCIA_UPDATE, agenciaIn.toString()));
			agencia = this.agenciaRepository.save(agenciaIn);
			return agenciaIn;
		} catch (Exception e) {
			String error = "Erro: CxAxUx00061";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().AGENCIA_UPDATE, error + e.getMessage()));

			return new Agencia(error);
		}
	}

	/// TODO talvez seja interessante colocar o como inativo ao invez
	/// de deleta-lo, avaliar quando implementar o CRUD
	@DeleteMapping("/delete/{id}")
	public Agencia deleteById(@PathVariable("id") String id) {
		try {
			Agencia agencia = this.agenciaRepository.findById(id);
			if (agencia == null) {
				String error = "Erro: CxAxDx00062 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().AGENCIA_DELETEBYID, error));
				return new Agencia(error);
			}

			this.logController.insert(new Log(new Constantes().AGENCIA_DELETEBYID, agencia.toString()));

			this.agenciaRepository.delete(agencia);
			return agencia;
		} catch (Exception e) {
			String error = "Erro: CxAxDx00063 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().AGENCIA_DELETEBYID, error + e.getMessage()));
			return new Agencia(error);
		}
	}
}
