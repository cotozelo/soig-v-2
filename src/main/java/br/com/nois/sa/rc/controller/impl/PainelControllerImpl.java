package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.PainelController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.PainelJSON;
import br.com.nois.sa.rc.model.json.UsuarioJSON;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.DadoTO;
import br.com.nois.sa.rc.model.to.DadoValorTO;
import br.com.nois.sa.rc.model.to.IndicadorTO;
import br.com.nois.sa.rc.model.to.IndicadorValorTO;
import br.com.nois.sa.rc.model.to.MunicipioTO;
import br.com.nois.sa.rc.model.to.PrestadoraTO;
import br.com.nois.sa.rc.model.to.UsuarioAgenciaTO;
import br.com.nois.sa.rc.model.to.UsuarioDadoTO;
import br.com.nois.sa.rc.model.to.UsuarioIndicadorTO;
import br.com.nois.sa.rc.model.to.UsuarioMunicipioTO;
import br.com.nois.sa.rc.model.to.UsuarioPrestadoraTO;
import br.com.nois.sa.rc.model.to.UsuarioTO;
import br.com.nois.sa.rc.repository.DadoRepository;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.PerfilRepository;
import br.com.nois.sa.rc.repository.UsuarioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;

@RestController
@RequestMapping("/painel")
public class PainelControllerImpl implements PainelController {
	private MunicipioRepository municipioRepository;
	private UsuarioRepository usuarioRepository;
	private PerfilRepository perfilRepository;
	private IndicadorRepository indicadorRepository;
	private DadoRepository dadoRepository;
	private LogRepository logRepository;
	private VersaoRepository versaoRepository;
	private Map<String, IndicadorTO> indicadoresTO = new HashMap<String, IndicadorTO>();
	private Map<String, DadoTO> dadosTO = new HashMap<String, DadoTO>();

	@Autowired
	LogController logController;
	private Map<String, Boolean> usuarioDados = new HashMap<String, Boolean>();
	private Map<String, Boolean> usuarioIndicadores = new HashMap<String, Boolean>();

	public PainelControllerImpl(MunicipioRepository municipioRepository, LogRepository logRepository,
			IndicadorRepository indicadorRepository, DadoRepository dadoRepository, UsuarioRepository usuarioRepository,
			PerfilRepository perfilRepository, VersaoRepository versaoRepository) {

		this.municipioRepository = municipioRepository;
		this.logRepository = logRepository;
		this.indicadorRepository = indicadorRepository;
		this.dadoRepository = dadoRepository;
		this.logController = new LogControllerImpl(this.logRepository, versaoRepository);
		this.usuarioRepository = usuarioRepository;
		this.perfilRepository = perfilRepository;
		this.versaoRepository = versaoRepository;
	}

	@Override
	@GetMapping("/listagem/{username}/{agenciaId}/{municipioId}/{prestadoraId}")
	public ResponseEntity<Response<List<PainelJSON>>> getPainel(@PathVariable("username") String userName,
			@PathVariable("agenciaId") String agenciaId, @PathVariable("municipioId") String municipioId,
			@PathVariable("prestadoraId") String prestadoraId) {

		Response<List<PainelJSON>> response = new Response<List<PainelJSON>>();
		List<PainelJSON> paineisJSON = new ArrayList<PainelJSON>();
		try {
			for (IndicadorTO indicadorTO : this.indicadorRepository.findAll()) {
				this.indicadoresTO.put(indicadorTO.getSigla(), indicadorTO);
			}
			for (DadoTO dadoTO : this.dadoRepository.findAll()) {
				this.dadosTO.put(dadoTO.getSigla(), dadoTO);
			}
			if (this.indicadoresTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/painel/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			if (this.dadosTO == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/painel/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			UsuarioJSON usuarioJSON = new UsuarioControllerImpl(usuarioRepository, logRepository, perfilRepository,
					versaoRepository).getInformacao(userName).getBody().getData();
			if (usuarioJSON == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/painel/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			UsuarioTO usuarioTO = new UsuarioTO(usuarioJSON);
			
			PainelJSON painelJSON = getDadosIndicadores(agenciaId, municipioId, prestadoraId, usuarioTO);
			if (painelJSON == null) {
				response.setError(new ErroJSON("VxAxRx00001", this.getClass().getName() + "/lisgatem/" + userName + "/"
						+ agenciaId + "/" + municipioId + "/" + prestadoraId));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

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

			for (AnoTO anoTO : anosTO) {
				for (DadoValorTO dadoValorTO : anoTO.getDadoValores()) {
					if (this.dadosTO.containsKey(dadoValorTO.getSigla())
							&& this.usuarioDados.containsKey(dadoValorTO.getSigla())
							&& this.usuarioDados.get(dadoValorTO.getSigla())) {
						PainelJSON auxPainel = new PainelJSON();
						auxPainel.setSigla(dadoValorTO.getSigla());
						auxPainel.setAgencia(painelJSON.getAgencia());
						auxPainel.setMunicipio(painelJSON.getMunicipio());
						auxPainel.setPrestadora(painelJSON.getPrestadora());
						auxPainel.setUnidade(this.dadosTO.get(dadoValorTO.getSigla()).getUnidadeNome());
						auxPainel.setTipoCalculo("");
						auxPainel.setAno(anoTO.getAno());
						auxPainel.setMeses(dadoValorTO);
						if (auxPainel.getValor() != null && !auxPainel.getValor().isEmpty()) {
							paineisJSON.add(auxPainel);
						}
					}
				}
				for (IndicadorValorTO indicadorValorTO : anoTO.getIndicadorValores()) {
					if (this.indicadoresTO.containsKey(indicadorValorTO.getSigla())
							&& this.usuarioIndicadores.containsKey(indicadorValorTO.getSigla())
							&& this.usuarioIndicadores.get(indicadorValorTO.getSigla())) {
						String tipo = "";
						if (indicadorValorTO.getTipo() != null) {
							tipo = indicadorValorTO.getTipo().toString();
						}
						PainelJSON auxPainel = new PainelJSON();
						auxPainel.setSigla(indicadorValorTO.getSigla());
						auxPainel.setAgencia(painelJSON.getAgencia());
						auxPainel.setMunicipio(painelJSON.getMunicipio());
						auxPainel.setPrestadora(painelJSON.getPrestadora());
						auxPainel.setUnidade(this.indicadoresTO.get(indicadorValorTO.getSigla()).getUnidadeNome());
						auxPainel.setTipoCalculo(tipo);
						auxPainel.setAno(anoTO.getAno());
						auxPainel.setMeses(indicadorValorTO);
						if (auxPainel.getValor() != null && !auxPainel.getValor().isEmpty()) {
							paineisJSON.add(auxPainel);
						}
					}
				}
			}

			response.setData(paineisJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);

		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/lisgatem/" + userName + "/" + agenciaId
					+ "/" + municipioId + "/" + prestadoraId));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	private PainelJSON getDadosIndicadores(String agenciaId, String municipioId, String prestadoraId,
			UsuarioTO usuarioTO) {
		String agencia;
		String municipio;
		String prestadora;
		for (UsuarioAgenciaTO usuarioAgenciaTO : usuarioTO.getUsuarioAgencias()) {
			if (usuarioAgenciaTO.getAgenciaId() != null && usuarioAgenciaTO.getAgenciaId().equals(agenciaId)) {
				agencia = usuarioAgenciaTO.getNome();

				for (UsuarioMunicipioTO usuarioMunicipioTO : usuarioAgenciaTO.getUsuarioMunicipios()) {
					if (usuarioMunicipioTO.getMunicipioId() != null && usuarioMunicipioTO.getMunicipioId().equals(municipioId)) {
						municipio = usuarioMunicipioTO.getNome();

						for (UsuarioPrestadoraTO usuarioPrestadoraTO : usuarioMunicipioTO.getUsuarioPrestadoras()) {
							if (usuarioPrestadoraTO.getPrestadoraId() != null && usuarioPrestadoraTO.getPrestadoraId().equals(prestadoraId)) {
								prestadora = usuarioPrestadoraTO.getNome();
								for (UsuarioDadoTO usuarioDado : usuarioPrestadoraTO.getUsuarioDados()) {
									this.usuarioDados.put(usuarioDado.getSigla(), usuarioDado.isFavorito());
								}
								for (UsuarioIndicadorTO usuarioIndicadorTO : usuarioPrestadoraTO
										.getUsuarioIndicadores()) {
									this.usuarioIndicadores.put(usuarioIndicadorTO.getSigla(),
											usuarioIndicadorTO.isFavorito());
								}
								PainelJSON painelJSON = new PainelJSON();
								painelJSON.setAgencia(agencia);
								painelJSON.setMunicipio(municipio);
								painelJSON.setPrestadora(prestadora);
								return painelJSON;
							}
						}
					}
				}
			}
		}
		return null;
	}

}
