package br.com.nois.sa.rc.controller.impl;

import java.util.ArrayList;
import java.util.Calendar;
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

import br.com.nois.sa.rc.controller.IndicadorController;
import br.com.nois.sa.rc.controller.LogController;
import br.com.nois.sa.rc.controller.Response;
import br.com.nois.sa.rc.model.Log;
import br.com.nois.sa.rc.model.json.ErroJSON;
import br.com.nois.sa.rc.model.json.IndicadorJSON;
import br.com.nois.sa.rc.model.to.EquacaoTO;
import br.com.nois.sa.rc.model.to.IndicadorTO;
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

	@GetMapping("/listagem/{username}")
	public ResponseEntity<Response<List<IndicadorJSON>>> getAll(@PathVariable("username") String userName) {

		Response<List<IndicadorJSON>> response = new Response<List<IndicadorJSON>>();
		try {
			List<IndicadorTO> indicadoresTO = this.indicadorRepository.findAll();
			if (indicadoresTO == null || indicadoresTO.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				response.setData(new ArrayList<>());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			List<IndicadorJSON> indicadoresJSON = new ArrayList<IndicadorJSON>();

			for (IndicadorTO indicadorTO : indicadoresTO) {
				indicadoresJSON.add(new IndicadorJSON(indicadorTO));
			}

			this.logController.insert(new Log(new Constantes().INDICADOR_GETALL, indicadoresJSON == null ? ""
					: new Util().ListColectionToString(new ArrayList<Object>(indicadoresJSON))));
			response.setData(indicadoresJSON);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/clonar/{username}/{anoorigem}/{anodestino}")
	public ResponseEntity<Response<String>> clonar(@PathVariable("username") String userName,
			@PathVariable("anoorigem") String anoOrigem, @PathVariable("anodestino") String anoDestino) {

		Response<String> response = new Response<String>();
		try {
			List<IndicadorTO> indicadoresTO = this.indicadorRepository.findAll();
			if (indicadoresTO == null || indicadoresTO.isEmpty()) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			for (IndicadorTO indicadorTO : indicadoresTO) {

				if (indicadorTO.getEquacaoAtiva() != null && !indicadorTO.getEquacaoAtiva().isEmpty()) {
					for (EquacaoTO equacaoOrigem : indicadorTO.getEquacaoAtiva()) {

						if (equacaoOrigem.getAno().equals(anoOrigem)) {
							EquacaoTO equacaoAtual = new EquacaoTO();
							equacaoAtual.setId();
							equacaoAtual.setAno(anoDestino);
							equacaoAtual.setAtiva(true);
							equacaoAtual.setFormula(equacaoOrigem.getFormula());
							equacaoAtual.setPaiId("-1");
							equacaoAtual.setVersaoGlobal(this.logController.getVersaoGlogal());

							for (EquacaoTO equacaoDestino : indicadorTO.getEquacaoAtiva()) {
								if (equacaoDestino.getAno().equals(anoDestino)) {
									equacaoDestino.setAtiva(false);
									equacaoAtual.setPaiId(equacaoDestino.getId());
									break;
								}
							}
							indicadorTO.setEquacao(equacaoAtual);
							break;
						}

					}
				}

			}

			indicadoresTO = this.indicadorRepository.save(indicadoresTO);
			this.logController.insert(new Log(new Constantes().INDICADOR_GETALL, indicadoresTO == null ? ""
					: new Util().ListColectionToString(new ArrayList<Object>(indicadoresTO))));

			String retorno = "anoOrigem" + " : " + anoOrigem + ", anoDestino : " + anoDestino;
			response.setData(retorno);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/listagem/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/insert/{username}")
	public ResponseEntity<Response<IndicadorJSON>> insert(@PathVariable("username") String userName,
			@RequestBody IndicadorJSON indicadorJSON) {

		Response<IndicadorJSON> response = new Response<IndicadorJSON>();
		try {
			IndicadorTO indicadorTO = new IndicadorTO(indicadorJSON);
			EquacaoTO equacaoTO = new EquacaoTO();
			equacaoTO.setAno(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
			equacaoTO.setAtiva(true);
			indicadorTO.setEquacao(equacaoTO);

			this.logController.insert(new Log(new Constantes().INDICADOR_INSERT, indicadorTO.toString()));
			indicadorTO = this.indicadorRepository.insert(indicadorTO);

			response.setData(new IndicadorJSON(indicadorTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<Response<IndicadorJSON>> update(@PathVariable("username") String userName,
			@RequestBody IndicadorJSON indicadorJSON) {

		Response<IndicadorJSON> response = new Response<IndicadorJSON>();
		try {
			IndicadorTO indicadorTO = this.indicadorRepository.findById(indicadorJSON.getId());
			if (indicadorTO == null) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

			indicadorTO.update(indicadorJSON);

			this.logController.insert(new Log(new Constantes().INDICADOR_UPDATE, indicadorTO.toString()));
			indicadorTO = this.indicadorRepository.save(indicadorTO);

			response.setData(new IndicadorJSON(indicadorTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/// TODO talvez seja interessante colocar o como inativo ao invez
	/// de deleta-lo, avaliar quando implementar o CRUD
	@DeleteMapping("/delete/{username}/{indicadorid}")
	public ResponseEntity<Response<IndicadorJSON>> deleteById(@PathVariable("username") String userName,
			@PathVariable("indicadorid") String indicadorId) {

		Response<IndicadorJSON> response = new Response<IndicadorJSON>();
		try {
			IndicadorTO indicadorTO = this.indicadorRepository.findById(indicadorId);
			if (indicadorTO == null) {
				response.setError(new ErroJSON("VxMxRx00001", this.getClass().getName() + "/listagem/" + userName));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			IndicadorJSON indicadorJSON = new IndicadorJSON(indicadorTO);
			this.indicadorRepository.delete(indicadorTO);

			this.logController.insert(new Log(new Constantes().INDICADOR_DELETEBYID, indicadorTO.toString()));

			response.setData(indicadorJSON);
			response.setData(new IndicadorJSON(indicadorTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			response.setError(new ErroJSON(ex, this.getClass().getName() + "/insert/" + userName));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
