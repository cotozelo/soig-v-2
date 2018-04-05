package br.com.nois.sa.rc.carga;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.nois.sa.rc.controller.impl.AgenciaControllerImpl;
import br.com.nois.sa.rc.controller.impl.MunicipioControllerImpl;
import br.com.nois.sa.rc.controller.impl.PrestadoraControllerImpl;
import br.com.nois.sa.rc.model.Agencia;
import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.model.Prestadora;
import br.com.nois.sa.rc.repository.AgenciaRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Util;

public class MunicipioPrestadora {

	Util util = new Util();
	private LogRepository logRepository;
	private AgenciaRepository agenciaRepository;
	private MunicipioRepository municipioRepository;
	private VersaoRepository versaoRepository;

	@Autowired
	AgenciaControllerImpl agenciaController;

	public MunicipioPrestadora(Map<String, Object> repositorios) {
		super();
		this.logRepository = (LogRepository) repositorios.get("log");
		this.agenciaRepository = (AgenciaRepository) repositorios.get("agenciaRepository");
		this.municipioRepository = (MunicipioRepository) repositorios.get("municipioRepository");
		this.versaoRepository = (VersaoRepository) repositorios.get("versaoRepository");
	}

	public void run(String caminho) {

		ArrayList<String> linhas = this.util.fileToString(caminho);
		ArrayList<String> erroMun = new ArrayList<>();

		AgenciaControllerImpl agenciaController = new AgenciaControllerImpl(this.agenciaRepository, this.logRepository,
				this.versaoRepository);
		List<Agencia> agencias = agenciaController.getAll();

		Agencia agencia;
		if (agencias.size() > 0) {
			agencia = agencias.get(0);
		} else {
			agencia = new Agencia();
			agencia.setAtivo(true);
			agencia.setNome("Agencia Completa");
			agencia = agenciaController.insert(agencia);
		}
		String idAgencia = agencia.getId();

		MunicipioControllerImpl municipioController = new MunicipioControllerImpl(this.municipioRepository,
				this.logRepository, this.versaoRepository);

		PrestadoraControllerImpl prestadoraController = new PrestadoraControllerImpl(this.municipioRepository,
				this.logRepository, this.versaoRepository);

		for (String linha : linhas) {
			// 0 ;1 ;2 ;3 ;4 ;5 ;6 ;7 ;8
			// codmun;municipio;UF;codpre;prestadora;sigla;abrangencia;natureza;servico
			String[] linhaSplit = linha.split(";");

			if (linhaSplit.length > 3 && !linhaSplit[0].toUpperCase().contains("CODMUN")) {

				Municipio municipio = municipioController.getByCodigo(linhaSplit[0]);
				if (municipio == null) {
					municipio = new Municipio();
				} else {
					municipio.setAtiva(true);
				}
				if (municipio.getId() == null) {
					municipio = new Municipio();

					municipio.setId();
					municipio.setAtiva(true);
					municipio.setCidade(linhaSplit[1].trim());
					municipio.setContatoEmail("");
					municipio.setContatoNome("");
					municipio.setContatoTelefone("");
					municipio.setEstado(linhaSplit[2].trim());
					municipio.setNome(linhaSplit[1].trim());
					municipio.setCodigo(linhaSplit[0].trim());
					municipio.setIdAgencia(idAgencia);

					municipio = municipioController.insert(municipio);
					if (municipio.getError() != null) {
						erroMun.add(linha);
					}
				}

				if (linhaSplit.length > 8) {
					Prestadora prestadora = prestadoraController.getIdCodigo(municipio.getId(), linhaSplit[3].trim());

					if (prestadora == null || prestadora.getId() == null) {

						prestadora = new Prestadora();
						prestadora.setId();
						prestadora.setAbrangencia(linhaSplit[6].trim());
						prestadora.setNatureza(linhaSplit[7].trim());
						prestadora.setNome(linhaSplit[4].trim());
						prestadora.setCodigo(linhaSplit[3].trim());
						prestadora.setServico(linhaSplit[8].trim());
						prestadora.setSigla(linhaSplit[5].trim());

						prestadora = prestadoraController.insert(municipio.getId(), prestadora);
						if (prestadora.getError() != null) {
							erroMun.add(linha);
						} else {
							System.out.println("[NOVO]" + linha);
						}
					}
				}
			}
		}

		if (!erroMun.isEmpty()) {
			System.out.println("Totado de erros " + erroMun.size());
			for (String erro : erroMun) {
				System.out.println(erro);

			}
		} else {
			System.out.println("Sem erros");
		}
		System.out.println("Tudo atualizado");

	}
}
