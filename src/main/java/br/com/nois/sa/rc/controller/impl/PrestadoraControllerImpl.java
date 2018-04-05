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
import br.com.nois.sa.rc.controller.PrestadoraController;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.model.Prestadora;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;

@RestController
@RequestMapping("/prestadora")
public class PrestadoraControllerImpl implements PrestadoraController {
	private MunicipioRepository municipioRepository;
	private LogRepository logRepository;

	@Autowired
	LogController logController;

	public PrestadoraControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			VersaoRepository versaoRespository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRespository);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all/{idMunicipio}")
	public List<Prestadora> getAll(@PathVariable("idMunicipio") String idMunicipio) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxRx00078 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, error));
			return (List<Prestadora>) new Prestadora(error);
		}

		List<Prestadora> prestadoras = municipio.getPrestadoras();
		if (prestadoras == null) {
			String error = "Erro: CxAxRx00079 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, error));
			return (List<Prestadora>) new Prestadora(error);
		}

		this.logController.insert(new Log(new Constantes().PRESTADORA_GETALL, prestadoras.toString()));
		return prestadoras;
	}

	@GetMapping("/idCodigo/{idMunicipio}/{codigoPrestadora}")
	public Prestadora getIdCodigo(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("codigoPrestadora") String codigoPrestadora) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxRx00081 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETBYID, error));
			return new Prestadora(error);
		}

		Prestadora prestadora = municipio.getPrestadoraCodigo(codigoPrestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxRx00082 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETBYCODIGO, error));
			return new Prestadora(error);
		}

		this.logController.insert(new Log(new Constantes().PRESTADORA_GETBYCODIGO, prestadora.toString()));
		return prestadora;
	}

	@GetMapping("/codigoCodigo/{codigoMunicipio}/{codigoPrestadora}")
	public Prestadora getCodigoCodigo(@PathVariable("codigoMunicipio") String codigoMunicipio,
			@PathVariable("codigoPrestadora") String codigoPrestadora) {

		Municipio municipio = this.municipioRepository.findByCodigo(codigoMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxRx00081 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETBYCODIGO, error));
			return new Prestadora(error);
		}

		Prestadora prestadora = municipio.getPrestadoraCodigo(codigoPrestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxRx00082 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_GETBYCODIGO, error));
			return new Prestadora(error);
		}

		this.logController.insert(new Log(new Constantes().PRESTADORA_GETBYCODIGO, prestadora.toString()));
		return prestadora;
	}

	@PutMapping("/insert/{idMunicipio}")
	public Prestadora insert(@PathVariable("idMunicipio") String idMunicipio, @RequestBody Prestadora prestadora) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxCx00084 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_INSERT, error));
			return new Prestadora(error);
		}

		if (prestadora.getId() == null) {
			prestadora.setId();
		}
		prestadora = municipio.setPrestadora(prestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxCx00085 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_INSERT, error));
			return new Prestadora(error);
		}

		this.logController.insert(new Log(new Constantes().PRESTADORA_INSERT, municipio.toString()));
		municipio = this.municipioRepository.save(municipio);

		return prestadora;
	}

	@PostMapping("/update/{idMunicipio}")
	public Prestadora update(@PathVariable("idMunicipio") String idMunicipio, @RequestBody Prestadora prestadora) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxUx00088 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_UPDATE, error));
			return new Prestadora(error);
		}

		prestadora = municipio.setPrestadora(prestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxUx00089 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_UPDATE, error));
			return new Prestadora(error);
		}

		this.logController.insert(new Log(new Constantes().PRESTADORA_UPDATE, municipio.toString()));
		municipio = this.municipioRepository.save(municipio);

		return prestadora;
	}

	@DeleteMapping("/delete/{idMunicipio}/{idPrestadora}")
	public Prestadora deleteById(@PathVariable("idMunicipio") String idMunicipio,
			@PathVariable("idPrestadora") String idPrestadora) {

		Municipio municipio = this.municipioRepository.findById(idMunicipio);
		if (municipio == null) {
			String error = "Erro: CxAxDx00092 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_DELETEBYID, error));
			return new Prestadora(error);
		}

		Prestadora prestadora = municipio.getPrestadora(idPrestadora);
		if (prestadora == null) {
			String error = "Erro: CxAxDx00093 ";
			System.out.println(error);
			this.logController.insert(new Log(new Constantes().PRESTADORA_DELETEBYID, error));
			return new Prestadora(error);
		}

		municipio.removePrestadora(idPrestadora);

		this.logController.insert(new Log(new Constantes().PRESTADORA_DELETEBYID, municipio.toString()));
		municipio = this.municipioRepository.save(municipio);

		return prestadora;
	}
}
