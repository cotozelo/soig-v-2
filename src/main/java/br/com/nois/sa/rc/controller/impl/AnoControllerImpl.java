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

import br.com.nois.sa.rc.controller.AnoContoller;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.model.Prestadora;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/ano")
public class AnoControllerImpl implements AnoContoller {
	private MunicipioRepository municipioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public AnoControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			VersaoRepository versaoRepository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all/{idMunicipio}/{idPrestadora}")
	public List<Ano> getAll(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("idPrestadora") String idPrestadora) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxRx00078 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_GETALL, error));
			return ((List<Ano>) new Ano(error));
		}

		Prestadora prestadora = municipio.getPrestadora(idPrestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxRx00078 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, error));
			return (List<Ano>) new Ano(error);
		}

		List<Ano> anos = prestadora.getAnos();
		if (anos == null) {
			String error = "Erro: CxAxRx00079 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_GETALL, error));
			return (List<Ano>) new Ano(error);
		}

		this.logController.insert(new Log(new Constantes().ANO_GETALL, anos.toString()));
		return anos;
	}

	@GetMapping("/id/{idMunicipio}/{idPrestadora}/{idAno}")
	public Ano getId(@PathVariable("idMunicipio") String idMunicipio, @PathVariable("idPrestadora") String idPrestadora,
			@PathVariable("idAno") String idAno) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxRx00081 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_GETBYID, error));
			return new Ano(error);
		}

		Prestadora prestadora = municipio.getPrestadora(idPrestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxRx00078 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, error));
			return new Ano(error);
		}

		Ano ano = prestadora.getAno(idAno);
		if (ano == null) {
			String error = "Erro: CxAxRx00082 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_GETBYID, error));
			return new Ano(error);
		}

		this.logController.insert(new Log(new Constantes().ANO_GETBYID, ano.toString()));
		return ano;
	}

	@PutMapping("/insert/{idMunicipio}/{idPrestadora}")
	public Ano insert(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("idPrestadora") String idPrestadora, @RequestBody Ano ano) {
		try {
			Municipio municipio = this.municipioRepository.findById(idMunicipio);
			if (municipio == null) {
				String error = "Erro: CxAxCx00084 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().ANO_INSERT, error));
				return new Ano(error);
			}

			Prestadora prestadora = municipio.getPrestadora(idPrestadora);
			if (prestadora == null) {
				String error = "Erro: CxAxRx00078 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, error));
				return new Ano(error);
			}

			if (ano.getId() == null) {
				ano.setId();
			}
			ano = prestadora.setAno(ano);
			if (ano == null) {
				String error = "Erro: CxAxCx00085 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().ANO_INSERT, error));
				return new Ano(error);
			}
			prestadora = municipio.setPrestadora(prestadora);
			if (prestadora == null) {
				String error = "Erro: CxAxCx00086 ";
				System.out.println(error);
				this.logController.insert(new Log(new Constantes().ANO_INSERT, error));
				return new Ano(error);
			}

			this.logController.insert(new Log(new Constantes().ANO_INSERT, municipio.toString()));
			municipio = this.municipioRepository.save(municipio);

			return ano;
		} catch (Exception ex) {
			return null;
		}
	}

	@PostMapping("/update/{idMunicipio}/{idPrestadora}")
	public Ano update(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("idPrestadora") String idPrestadora, @RequestBody Ano ano) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxUx00088 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_UPDATE, error));
			return new Ano(error);
		}
		Prestadora prestadora = municipio.getPrestadora(idPrestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxRx00078 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, error));
			return new Ano(error);
		}

		ano = prestadora.setAno(ano);
		if (ano == null) {
			String error = "Erro: CxAxUx00089 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_UPDATE, error));
			return new Ano(error);
		}
		prestadora = municipio.setPrestadora(prestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxCx00086 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_INSERT, error));
			return new Ano(error);
		}

		this.logController.insert(new Log(new Constantes().ANO_UPDATE, municipio.toString()));
		municipio = this.municipioRepository.save(municipio);

		return ano;
	}

	@DeleteMapping("/delete/{idMunicipio}/{idPrestadora}/{idAno}")
	public Ano deleteById(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("idPrestadora") String idPrestadora, @PathVariable("idAno") String idAno) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxDx00092 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_DELETEBYID, error));
			return new Ano(error);
		}
		Prestadora prestadora = municipio.getPrestadora(idPrestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxRx00078 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, error));
			return new Ano(error);
		}
		Ano ano = prestadora.getAno(idAno);
		if (ano == null) {
			String error = "Erro: CxAxDx00093 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_DELETEBYID, error));
			return new Ano(error);
		}

		prestadora.removeAno(idAno);
		prestadora = municipio.setPrestadora(prestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxCx00086 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().ANO_INSERT, error));
			return new Ano(error);
		}

		this.logController.insert(new Log(new Constantes().ANO_DELETEBYID, municipio.toString()));
		municipio = this.municipioRepository.save(municipio);

		return ano;
	}

}
