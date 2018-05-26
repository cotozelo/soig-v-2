package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.AnoJSON;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.DadoTO;
import br.com.nois.sa.rc.model.to.EquacaoTO;
import br.com.nois.sa.rc.model.to.IndicadorTO;
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
@RequestMapping("/ano")
public class AnoControllerImpl implements AnoContoller {
	private MunicipioRepository municipioRepository;
	private LogRepository logRepository;
	private DadoRepository dadoRepository;
	private IndicadorRepository indicadorRepository;
	private VersaoRepository versaoRepository;

	@Autowired
	LogController logController;

	public AnoControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			DadoRepository dadoRepository, IndicadorRepository indicadorRepository, VersaoRepository versaoRepository) {
		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.dadoRepository = dadoRepository;
		this.indicadorRepository = indicadorRepository;
		this.versaoRepository = versaoRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
	}

	@GetMapping("/listagem/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<List<AnoJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId) {

		return this.getAno(agenciaId, municipioId, prestadoraId);
	}

	@GetMapping("/listagem/{username}/{agenciaId}/{municipioId}")
	public ResponseEntity<Response<List<AnoJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId) {

		return this.getAno(agenciaId, municipioId, "");
	}

	@GetMapping("/listagem/{username}/{agenciaId}")
	public ResponseEntity<Response<List<AnoJSON>>> getAll(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId) {

		return this.getAno(agenciaId, "", "");
	}

	public ResponseEntity<Response<List<AnoJSON>>> getAno(String agenciaId, String municipioId, String prestadoraId) {

		List<AnoJSON> anosJSON = new ArrayList<AnoJSON>();
		Response<List<AnoJSON>> response = new Response<List<AnoJSON>>();

		List<MunicipioTO> municipiosTO = this.getMunicipios(agenciaId, municipioId);
		if (municipiosTO == null) {
			response.setError(new ErroJSON("ERRO", this.getClass().getName()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

		for (MunicipioTO municipioTO : municipiosTO) {
			if (municipioTO.getPrestadoras() != null && !municipioTO.getPrestadoras().isEmpty()) {
				for (PrestadoraTO prestadoraTO : municipioTO.getPrestadoras()) {
					if (prestadoraId.isEmpty()) {
						if (prestadoraTO != null && prestadoraTO.getAnos() != null) {
							for (AnoTO to : prestadoraTO.getAnos()) {
								AnoJSON anoJSON = new AnoJSON(to);

								if (anosJSON.isEmpty()) {
									anosJSON.add(anoJSON);
								} else {
									int sizeAnosJSON = anosJSON.size();
									boolean isNovo = true;
									for (int ii = 0; ii < sizeAnosJSON; ii++) {
										AnoJSON anoAux = anosJSON.get(ii);
										if (anoJSON.getAno().equals(anoAux.getAno())) {
											if (anoAux.isEditar() == null
													|| anoAux.isEditar().equals(String.valueOf(to.isEditar()))) {
												anoAux.setEditar(Constantes.DIFERENTE);
												isNovo = false;
											}
											if (anoAux.isExibir() == null
													|| anoAux.isExibir().equals(String.valueOf(to.isExibir()))) {
												anoAux.setExibir(Constantes.DIFERENTE);
												isNovo = false;
											}
										}
									}
									if (isNovo) {
										anosJSON.add(anoJSON);
									}
								}
							}
						}
					} else {
						if (prestadoraTO != null && prestadoraTO.getId().equals(prestadoraId)
								&& prestadoraTO.getAnos() != null) {
							for (AnoTO to : prestadoraTO.getAnos()) {
								anosJSON.add(new AnoJSON(to));
							}
						}
					}
				}
				this.logController.insert(new Log(Constantes.ANO_LISTAGEM,
						new Util().ListColectionToString(new ArrayList<Object>(anosJSON))));

				response.setData(anosJSON);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		}
		response.setError(new ErroJSON("ERRO", this.getClass().getName()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	public List<MunicipioTO> getMunicipios(String agenciaId, String municipioId) {
		List<MunicipioTO> municipiosTO = null;

		if (municipioId.isEmpty()) {
			municipiosTO = this.municipioRepository.findByAgenciaIdAndAtivo(agenciaId, true);
		} else {
			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);
			if (municipioTO != null) {
				municipiosTO = new ArrayList<>();
				municipiosTO.add(municipioTO);
			} else {
				return null;
			}
		}
		return municipiosTO;
	}

	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<AnoJSON>> insert(@PathVariable("username") String userName,
			@RequestBody AnoJSON anoJSON) {

		Response<AnoJSON> response = new Response<AnoJSON>();
		try {

			AnoTO anoTO = new AnoTO(anoJSON);

			List<DadoTO> dadosTO = this.dadoRepository.findAll();
			List<IndicadorTO> indicadoresTO = this.indicadorRepository.findAll();

			anoTO.setDado(dadosTO);
			anoTO.setIndicador(indicadoresTO);

			List<MunicipioTO> municipiosTO = this.municipioRepository.findAll();

			if (municipiosTO == null || municipiosTO.isEmpty()) {
				response.setError(new ErroJSON("VxPxRx00001", this.getClass().getName() + "/insert/" + userName));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			for (MunicipioTO municipioTO : municipiosTO) {
				List<PrestadoraTO> prestadorasTO = municipioTO.getPrestadoras();

				if (prestadorasTO == null || prestadorasTO.isEmpty()) {
					response.setError(new ErroJSON("VxPxRx00002", this.getClass().getName() + "/insert/" + userName));
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
				}
				for (PrestadoraTO prestadoraTO : prestadorasTO) {
					prestadoraTO.setAno(anoTO);
					this.municipioRepository.save(municipioTO);
					this.logController.insert(new Log(Constantes.ANO_INSERT, prestadoraTO.toString()));
				}
			}

			for (IndicadorTO indicadorTO : indicadoresTO) {
				for (EquacaoTO equacaoTO : indicadorTO.getEquacaoAtiva()) {
					if (equacaoTO.getAno() != null && equacaoTO.getAno().equals(anoJSON.getAno())) {
						EquacaoTO aux = new EquacaoTO();
						aux.setAno(equacaoTO.getAno());
						aux.setAtiva(true);
						aux.setFormula(equacaoTO.getFormula());
						aux.setId();
						aux.setPaiId("-1");
						aux.setVersaoGlobal(this.versaoRepository.count());
						indicadorTO.setEquacao(aux);
						break;
					}
				}
			}
			this.indicadorRepository.save(indicadoresTO);

			response.setData(new AnoJSON(anoTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/update/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<AnoJSON>> update(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId, @RequestBody AnoJSON anoJSON) {
		Response<AnoJSON> response = new Response<AnoJSON>();

		try {

			List<MunicipioTO> municipiosTO = this.getMunicipios(agenciaId, municipioId);
			if (municipiosTO == null) {
				response.setError(new ErroJSON("ERRO", this.getClass().getName()));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

			boolean salvar = false;
			for (MunicipioTO municipioTO : municipiosTO) {
				if (municipioTO.getPrestadoras() != null && !municipioTO.getPrestadoras().isEmpty()) {
					for (PrestadoraTO prestadoraTO : municipioTO.getPrestadoras()) {
						if (!prestadoraId.isEmpty() && prestadoraTO != null && prestadoraTO.getAnos() != null
								&& prestadoraId.equals(prestadoraTO.getId())) {
							for (AnoTO to : prestadoraTO.getAnos()) {
								if (anoJSON.getAno().equals(to.getAno())) {
									if (!anoJSON.getEditar().toUpperCase().equals(Constantes.DIFERENTE)) {
										to.setEditar(Boolean.parseBoolean(anoJSON.getEditar()));
										salvar = true;
									}
									if (!anoJSON.getExibir().toUpperCase().equals(Constantes.DIFERENTE)) {
										to.setExibir(Boolean.parseBoolean(anoJSON.getExibir()));
										salvar = true;
									}
								}
							}
						}
					}
				}
			}
			if (salvar) {
				this.municipioRepository.save(municipiosTO);
			}
			this.logController.insert(new Log(Constantes.ANO_LISTAGEM, anoJSON.toString()));

			response.setData(anoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch ( Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PutMapping("/update/{username}/{agenciaId}/{municipioId}")
	public ResponseEntity<Response<AnoJSON>> update(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId, @RequestBody AnoJSON anoJSON) {
		Response<AnoJSON> response = new Response<AnoJSON>();

		try {

			List<MunicipioTO> municipiosTO = this.getMunicipios(agenciaId, municipioId);
			if (municipiosTO == null) {
				response.setError(new ErroJSON("ERRO", this.getClass().getName()));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

			boolean salvar = false;
			for (MunicipioTO municipioTO : municipiosTO) {
				if (municipioTO.getPrestadoras() != null && !municipioTO.getPrestadoras().isEmpty()) {
					for (PrestadoraTO prestadoraTO : municipioTO.getPrestadoras()) {
						if (prestadoraTO != null && prestadoraTO.getAnos() != null ) {
							for (AnoTO to : prestadoraTO.getAnos()) {
								if (anoJSON.getAno().equals(to.getAno())) {
									if (!anoJSON.getEditar().toUpperCase().equals(Constantes.DIFERENTE)) {
										to.setEditar(Boolean.parseBoolean(anoJSON.getEditar()));
										salvar = true;
									}
									if (!anoJSON.getExibir().toUpperCase().equals(Constantes.DIFERENTE)) {
										to.setExibir(Boolean.parseBoolean(anoJSON.getExibir()));
										salvar = true;
									}
								}
							}
						}
					}
				}
			}
			if (salvar) {
				this.municipioRepository.save(municipiosTO);
			}
			this.logController.insert(new Log(Constantes.ANO_LISTAGEM, anoJSON.toString()));

			response.setData(anoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch ( Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PutMapping("/update/{username}/{agenciaId}")
	public ResponseEntity<Response<AnoJSON>> update(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @RequestBody AnoJSON anoJSON) {
		Response<AnoJSON> response = new Response<AnoJSON>();

		try {

			List<MunicipioTO> municipiosTO = this.getMunicipios(agenciaId, "");
			if (municipiosTO == null) {
				response.setError(new ErroJSON("ERRO", this.getClass().getName()));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

			boolean salvar = false;
			for (MunicipioTO municipioTO : municipiosTO) {
				if (municipioTO.getPrestadoras() != null && !municipioTO.getPrestadoras().isEmpty()) {
					for (PrestadoraTO prestadoraTO : municipioTO.getPrestadoras()) {
						if (prestadoraTO != null && prestadoraTO.getAnos() != null ) {
							for (AnoTO to : prestadoraTO.getAnos()) {
								if (anoJSON.getAno().equals(to.getAno())) {
									if (!anoJSON.getEditar().toUpperCase().equals(Constantes.DIFERENTE)) {
										to.setEditar(Boolean.parseBoolean(anoJSON.getEditar()));
										salvar = true;
									}
									if (!anoJSON.getExibir().toUpperCase().equals(Constantes.DIFERENTE)) {
										to.setExibir(Boolean.parseBoolean(anoJSON.getExibir()));
										salvar = true;
									}
								}
							}
						}
					}
				}
			}
			if (salvar) {
				this.municipioRepository.save(municipiosTO);
			}
			this.logController.insert(new Log(Constantes.ANO_LISTAGEM, anoJSON.toString()));

			response.setData(anoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch ( Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/update_1/{username}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<AnoJSON>> update_1(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@RequestBody AnoJSON anoJSON) {

		Response<AnoJSON> response = new Response<AnoJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxPxRx00001",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxPxRx00002",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			AnoTO anoTO = prestadoraTO.getAnoById(anoJSON.getId());
			anoTO.update(new AnoTO(anoJSON));

			this.municipioRepository.save(municipioTO);

			this.logController.insert(new Log(Constantes.ANO_UPDATE, prestadoraTO.toString()));
			response.setData(new AnoJSON(anoTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/delete/{username}/{municipioId}/{prestadoraId}/{dadoId}")
	public ResponseEntity<Response<AnoJSON>> delete(@PathVariable("username") String userName,
			@PathVariable("municipioId") String municipioId, @PathVariable("prestadoraId") String prestadoraId,
			@PathVariable("dadoId") String dadoId) {

		Response<AnoJSON> response = new Response<AnoJSON>();
		try {

			MunicipioTO municipioTO = this.municipioRepository.findById(municipioId);

			if (municipioTO == null) {
				response.setError(new ErroJSON("VxPxRx00001",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			PrestadoraTO prestadoraTO = municipioTO.getPrestadora(prestadoraId);
			if (prestadoraTO == null) {
				response.setError(new ErroJSON("VxPxRx00002",
						this.getClass().getName() + "/insert/" + userName + "/" + municipioId + "/" + prestadoraId));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			AnoJSON anoJSON = new AnoJSON(prestadoraTO.getAnoById(dadoId));
			prestadoraTO.removeAno(dadoId);

			this.municipioRepository.save(municipioTO);

			this.logController.insert(new Log(Constantes.ANO_DELETE, anoJSON.toString()));
			response.setData(anoJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
