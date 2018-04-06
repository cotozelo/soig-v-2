package br.com.nois.sa.rc.controller.impl;

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
import br.com.nois.sa.rc.controller.MunicipioController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/municipio")
public class MunicipioControllerImpl implements MunicipioController {
	private MunicipioRepository municipioRepository;
	private LogRepository logRepository;
	private VersaoRepository versaoRepository;

	@Autowired
	LogController logController;

	public MunicipioControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.versaoRepository = versaoRepository;

		this.logController = new LogControllerImpl(this.logRepository, this.versaoRepository);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all")
	public List<Municipio> getAll() {

		List<Municipio> municipios = this.municipioRepository.findAll();
		if (municipios == null) {
			String error = "Warning: CxExRx00066 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ENTIDADE_GETALL, error));
			return ((List<Municipio>) new Municipio(error));
		}

		this.logController.insert(new Log(new Constantes().ENTIDADE_GETALL, municipios.toString()));
		return municipios;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/idAgencia/{idAgencia}")
	public List<Municipio> getByIdAgencia(@PathVariable("idAgencia") String idAgencia) {

		List<Municipio> municipios = this.municipioRepository.findByIdAgencia(idAgencia);
		if (municipios == null) {
			String error = "Erro: CxExRx00068 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ENTIDADE_GETBYID, error));
			return (List<Municipio>) new Municipio(error);
		}

		this.logController.insert(new Log(new Constantes().ENTIDADE_GETBYID, municipios.toString()));
		return municipios;
	}

	@GetMapping("/codigo/{codigo}")
	public Municipio getByCodigo(@PathVariable("codigo") String codigo) {

		Municipio municipio = this.municipioRepository.findByCodigo(codigo);
		if (municipio == null) {
			String error = "Warning: CxExRx00068 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ENTIDADE_GETBYCODIGO, error));
			return new Municipio(error);
		}

		this.logController.insert(new Log(new Constantes().ENTIDADE_GETBYCODIGO, municipio.toString()));
		return municipio;
	}

	@GetMapping("/nome/{nome}")
	public Municipio getByNome(@PathVariable("nome") String nome) {
		
		Municipio municipio = this.municipioRepository.findByNome(nome);
		if (municipio == null) {
			String error = "Warning: CxExRx00069 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ENTIDADE_GETBYNOME, error));
			return new Municipio(error);
		}

		this.logController.insert(new Log(new Constantes().ENTIDADE_GETBYNOME, municipio.toString()));
		return municipio;
	}

	@PutMapping("/insert")
	public Municipio insert(@RequestBody Municipio municipio) {

		municipio = this.municipioRepository.insert(municipio);
		if (municipio == null) {
			String error = "Erro: CxExCx00070 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ENTIDADE_INSERT, error));
			return new Municipio(error);
		}

		this.logController.insert(new Log(new Constantes().ENTIDADE_INSERT, municipio.toString()));

		return municipio;
	}

	@PostMapping("/update")
	public Municipio update(@RequestBody Municipio municipio) {

		try {

			municipio = this.municipioRepository.save(municipio);
			if (municipio == null) {
				String error = "Erro: CxExUx00072 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().ENTIDADE_UPDATE, error));
				return new Municipio(error);
			}

			this.logController.insert(new Log(new Constantes().ENTIDADE_UPDATE, municipio.toString()));
			return municipio;

		} catch (Exception e) {
			String error = "Erro: CxExUx00073 ";
			System.out.println(error + e.getMessage());
			this.logController.insert(new Log(new Constantes().ENTIDADE_UPDATE, error + e.getMessage()));
			return new Municipio(error);
		}
	}

	@DeleteMapping("/delete/{idMunicipio}")
	public Municipio deleteById(@PathVariable("idMunicipio") String idMunicipio) {
		try {

			Municipio municipio = this.municipioRepository.findById(idMunicipio);
			this.municipioRepository.delete(municipio);

			if (municipio == null) {
				String error = "Erro: CxExDx00075 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().ENTIDADE_DELETEBYID, error));
				return new Municipio(error);
			}
			this.logController.insert(new Log(new Constantes().ENTIDADE_DELETEBYID, municipio.toString()));

			return municipio;
		} catch (Exception e) {
			System.out.println("Erro: CxExDx00076 " + e.getMessage());
			this.logController
					.insert(new Log(new Constantes().ENTIDADE_DELETEBYID, "Erro: CxAxDx00030 " + e.getMessage()));
			return null;
		}
	}
}
