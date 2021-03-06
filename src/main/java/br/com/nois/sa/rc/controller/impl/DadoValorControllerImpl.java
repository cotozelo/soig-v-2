package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.DadoValorController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.TipoCalculo;
import br.com.nois.sa.rc.model.json.AnoDadoValorJSON;
import br.com.nois.sa.rc.model.json.AnoDadoValoresJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.DadoValorTO;
import br.com.nois.sa.rc.model.to.IndicadorValorTO;
import br.com.nois.sa.rc.model.to.MunicipioTO;
import br.com.nois.sa.rc.model.to.PrestadoraTO;
import br.com.nois.sa.rc.repository.DadoRepository;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Constantes;
import br.com.nois.sa.rc.util.Util;

@RestController
@RequestMapping("/dadovalor")
public class DadoValorControllerImpl implements DadoValorController {
	private MunicipioRepository municipioRepository;
	// private DadoRepository dadoRepository;
	private LogRepository logRepository;
	// private List<DadoTO> dadosTO;
	private VersaoRepository versaoRepository;
	private IndicadorRepository indicadorRepository;

	@Autowired
	LogController logController;

	static Random gerador = new Random();

	public DadoValorControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			DadoRepository dadoRepository, VersaoRepository versaoRepository, IndicadorRepository indicadorRepository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		// this.dadoRepository = dadoRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
		// this.dadosTO = this.dadoRepository.findAll();
		this.versaoRepository = versaoRepository;
		this.indicadorRepository = indicadorRepository;
	}

	@Override
	@GetMapping("/listagem/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<List<AnoDadoValoresJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId) {
		Response<List<AnoDadoValoresJSON>> response = new Response<List<AnoDadoValoresJSON>>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null || !municipioTO.getAgenciaId().equals(agenciaId)) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			List<AnoTO> anosTO = prestadoraTO.getAnos();
			if (anosTO == null || anosTO.isEmpty()) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			List<AnoDadoValoresJSON> anosJSON = new ArrayList<AnoDadoValoresJSON>();
			for (AnoTO anoTO : anosTO) {
				AnoDadoValoresJSON anoDadoValorJSON = new AnoDadoValoresJSON(anoTO);
				anosJSON.add(anoDadoValorJSON);
			}

			this.logController.insert(new Log(Constantes.PRESTADORA_LISTAGEM,
					new Util().ListColectionToString(new ArrayList<Object>(anosJSON))));

			response.setData(anosJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/lisgatem/" + userName + "/" + agenciaId
					+ "/" + municipioId + "/" + prestadoraId));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	public Map<String, Double> getMes(String agenciaId, String municipioId, String prestadoraId, String ano, String mes) {
		Map<String, Double> dadosAnoMes = new HashMap<String, Double>();
		
		MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);
		if (municipioTO == null || !municipioTO.getAgenciaId().equals(agenciaId)) {
			return null;
		}

		PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
		if (prestadoraTO == null) {
			return null;
		}

		List<AnoTO> anosTO = prestadoraTO.getAnos();
		if (anosTO == null || anosTO.isEmpty()) {
			return null;
		}

		for (AnoTO anoTO : anosTO) {
			if (anoTO.getAno().equals(ano)) {
				List<DadoValorTO> dadosValor = anoTO.getDadoValores();
				for (DadoValorTO dadoValor : dadosValor) {
					dadosAnoMes.put(dadoValor.getSigla(), dadoValor.getMes(mes));
				}
			}
		}
		return dadosAnoMes;
	}

	@PutMapping("/update/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<AnoDadoValorJSON>> update(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId, @RequestBody AnoDadoValorJSON anoDadoValorJSON) {

		Response<AnoDadoValorJSON> response = new Response<AnoDadoValorJSON>();
		try {
			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null || !municipioTO.getAgenciaId().equals(agenciaId)) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/update/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/update/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			AnoTO anoTO = prestadoraTO.getAno(anoDadoValorJSON.getAno());
			if (anoTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/update/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			for (DadoValorTO dadoValorTO : anoTO.getDadoValores()) {
				if (dadoValorTO.getSigla().equalsIgnoreCase(anoDadoValorJSON.getDadoValor().getSigla())) {
					dadoValorTO.update(anoDadoValorJSON.getDadoValor());

					this.municipioRepository.save(municipioTO);

					response.setData(anoDadoValorJSON);
					return ResponseEntity.status(HttpStatus.OK).body(response);
				}
			}
			response.setError(new ErroJSON("não achou dado", this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/gerabanco/{username}")
	public ResponseEntity<Response<AnoDadoValorJSON>> gerabanco(@PathVariable("username") String userName) {

		Response<AnoDadoValorJSON> response = new Response<AnoDadoValorJSON>();
		try {
			List<MunicipioTO> municipiosTO = this.municipioRepository.findByAtivo(false);
			for (MunicipioTO municipioTO : municipiosTO) {
				try {
					System.out.println(municipioTO.getNome());
					municipioTO = this.municipioRepository.findById(municipioTO.getId());
					List<PrestadoraTO> prestadorasTO = municipioTO.getPrestadoras();
					for (PrestadoraTO prestadoraTO : prestadorasTO) {

						System.out.println(municipioTO.getNome() + " | " + prestadoraTO.getNome());
						for (AnoTO anoTO : prestadoraTO.getAnos()) {

							System.out.println(
									municipioTO.getNome() + " | " + prestadoraTO.getNome() + " | " + anoTO.getAno());
							for (DadoValorTO valorTO : anoTO.getDadoValores()) {

								if (valorTO.getMes01() == null || valorTO.getMes01().isEmpty()) {
									valorTO.setMes01(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes02() == null || valorTO.getMes02().isEmpty()) {
									valorTO.setMes02(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes03() == null || valorTO.getMes03().isEmpty()) {
									valorTO.setMes03(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes04() == null || valorTO.getMes04().isEmpty()) {
									valorTO.setMes04(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes05() == null || valorTO.getMes05().isEmpty()) {
									valorTO.setMes05(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes06() == null || valorTO.getMes06().isEmpty()) {
									valorTO.setMes06(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes07() == null || valorTO.getMes07().isEmpty()) {
									valorTO.setMes07(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes08() == null || valorTO.getMes08().isEmpty()) {
									valorTO.setMes08(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes09() == null || valorTO.getMes09().isEmpty()) {
									valorTO.setMes09(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes10() == null || valorTO.getMes10().isEmpty()) {
									valorTO.setMes10(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes11() == null || valorTO.getMes11().isEmpty()) {
									valorTO.setMes11(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes12() == null || valorTO.getMes12().isEmpty()) {
									valorTO.setMes12(String.valueOf(gerador.nextInt(99999)));
								}

								int total = Integer.valueOf(valorTO.getMes01());
								total += Integer.valueOf(valorTO.getMes02());
								total += Integer.valueOf(valorTO.getMes03());
								total += Integer.valueOf(valorTO.getMes04());
								total += Integer.valueOf(valorTO.getMes05());
								total += Integer.valueOf(valorTO.getMes06());
								total += Integer.valueOf(valorTO.getMes07());
								total += Integer.valueOf(valorTO.getMes08());
								total += Integer.valueOf(valorTO.getMes09());
								total += Integer.valueOf(valorTO.getMes10());
								total += Integer.valueOf(valorTO.getMes11());
								total += Integer.valueOf(valorTO.getMes12());
								valorTO.setTotal(String.valueOf(total));
								System.out.println(municipioTO.getNome() + " | " + prestadoraTO.getNome() + " | "
										+ anoTO.getAno() + " | " + valorTO.getSigla());
								this.municipioRepository.save(municipioTO);
							}
							for (IndicadorValorTO valorTO : anoTO.getIndicadorValores()) {

								if (valorTO.getMes01() == null || valorTO.getMes01().isEmpty()) {
									valorTO.setMes01(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes02() == null || valorTO.getMes02().isEmpty()) {
									valorTO.setMes02(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes03() == null || valorTO.getMes03().isEmpty()) {
									valorTO.setMes03(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes04() == null || valorTO.getMes04().isEmpty()) {
									valorTO.setMes04(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes05() == null || valorTO.getMes05().isEmpty()) {
									valorTO.setMes05(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes06() == null || valorTO.getMes06().isEmpty()) {
									valorTO.setMes06(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes07() == null || valorTO.getMes07().isEmpty()) {
									valorTO.setMes07(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes08() == null || valorTO.getMes08().isEmpty()) {
									valorTO.setMes08(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes09() == null || valorTO.getMes09().isEmpty()) {
									valorTO.setMes09(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes10() == null || valorTO.getMes10().isEmpty()) {
									valorTO.setMes10(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes11() == null || valorTO.getMes11().isEmpty()) {
									valorTO.setMes11(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes12() == null || valorTO.getMes12().isEmpty()) {
									valorTO.setMes12(String.valueOf(gerador.nextInt(99999)));
								}

								int total = Integer.valueOf(valorTO.getMes01());
								total += Integer.valueOf(valorTO.getMes02());
								total += Integer.valueOf(valorTO.getMes03());
								total += Integer.valueOf(valorTO.getMes04());
								total += Integer.valueOf(valorTO.getMes05());
								total += Integer.valueOf(valorTO.getMes06());
								total += Integer.valueOf(valorTO.getMes07());
								total += Integer.valueOf(valorTO.getMes08());
								total += Integer.valueOf(valorTO.getMes09());
								total += Integer.valueOf(valorTO.getMes10());
								total += Integer.valueOf(valorTO.getMes11());
								total += Integer.valueOf(valorTO.getMes12());
								valorTO.setTotal(String.valueOf(total));

								System.out.println(municipioTO.getNome() + " | " + prestadoraTO.getNome() + " | "
										+ anoTO.getAno() + " | " + valorTO.getSigla() + " | " + TipoCalculo.ACUMULADO);
								valorTO.setTipo(TipoCalculo.ACUMULADO);
								this.municipioRepository.save(municipioTO);
							}
							for (IndicadorValorTO valorTO : anoTO.getIndicadorValores()) {

								if (valorTO.getMes01() == null || valorTO.getMes01().isEmpty()) {
									valorTO.setMes01(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes02() == null || valorTO.getMes02().isEmpty()) {
									valorTO.setMes02(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes03() == null || valorTO.getMes03().isEmpty()) {
									valorTO.setMes03(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes04() == null || valorTO.getMes04().isEmpty()) {
									valorTO.setMes04(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes05() == null || valorTO.getMes05().isEmpty()) {
									valorTO.setMes05(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes06() == null || valorTO.getMes06().isEmpty()) {
									valorTO.setMes06(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes07() == null || valorTO.getMes07().isEmpty()) {
									valorTO.setMes07(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes08() == null || valorTO.getMes08().isEmpty()) {
									valorTO.setMes08(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes09() == null || valorTO.getMes09().isEmpty()) {
									valorTO.setMes09(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes10() == null || valorTO.getMes10().isEmpty()) {
									valorTO.setMes10(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes11() == null || valorTO.getMes11().isEmpty()) {
									valorTO.setMes11(String.valueOf(gerador.nextInt(99999)));
								}
								if (valorTO.getMes12() == null || valorTO.getMes12().isEmpty()) {
									valorTO.setMes12(String.valueOf(gerador.nextInt(99999)));
								}

								int total = Integer.valueOf(valorTO.getMes01());
								total += Integer.valueOf(valorTO.getMes02());
								total += Integer.valueOf(valorTO.getMes03());
								total += Integer.valueOf(valorTO.getMes04());
								total += Integer.valueOf(valorTO.getMes05());
								total += Integer.valueOf(valorTO.getMes06());
								total += Integer.valueOf(valorTO.getMes07());
								total += Integer.valueOf(valorTO.getMes08());
								total += Integer.valueOf(valorTO.getMes09());
								total += Integer.valueOf(valorTO.getMes10());
								total += Integer.valueOf(valorTO.getMes11());
								total += Integer.valueOf(valorTO.getMes12());
								valorTO.setTotal(String.valueOf(total));

								System.out.println(municipioTO.getNome() + " | " + prestadoraTO.getNome() + " | "
										+ anoTO.getAno() + " | " + valorTO.getSigla() + " | " + TipoCalculo.MENSAL);
								valorTO.setTipo(TipoCalculo.MENSAL);
								this.municipioRepository.save(municipioTO);
							}
						}
					}
				} catch (Exception ex) {
					System.out.println("Erro" + ex.getMessage());
				}
			}
			response.setError(new ErroJSON("não achou dado", this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/update/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/aline/{username}/{agenciaId}/{municipioId}/{prestadoraId}/{ano}/{mes}/{dado}")
	public ResponseEntity<Response<String>> aline(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId, @PathVariable("ano") String ano, @PathVariable("mes") String mes, @PathVariable("dado") String dado) {
		Response<String> response = new Response<String>();
		try {
			
			IndicadorValorControllerImpl indicadorValorController = new IndicadorValorControllerImpl(this.municipioRepository, this.logRepository, this.indicadorRepository, this.versaoRepository);
			
			Map<String, Double> variaveis = getMes(agenciaId, municipioId, prestadoraId, ano, mes);
			
			EquacaoControllerImpl equacaoController = new EquacaoControllerImpl(this.indicadorRepository, this.logRepository, this.versaoRepository);
			Map<String, String> equacoes = equacaoController.getIndicadoresByDado(dado, ano);
			
			if(indicadorValorController.updateByDado(agenciaId, municipioId, prestadoraId, ano, mes, equacoes, variaveis)) {
				response.setData("true");
			} else {
				response.setData("false");
			}
			
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/lisgatem/" + userName + "/" + agenciaId
					+ "/" + municipioId + "/" + prestadoraId));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
