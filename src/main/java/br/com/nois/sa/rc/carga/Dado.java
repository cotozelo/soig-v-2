package br.com.nois.sa.rc.carga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.nois.sa.rc.controller.impl.AgenciaControllerImpl;
import br.com.nois.sa.rc.controller.impl.AnoControllerImpl;
import br.com.nois.sa.rc.controller.impl.MunicipioControllerImpl;
import br.com.nois.sa.rc.controller.impl.PrestadoraControllerImpl;
import br.com.nois.sa.rc.model.Agencia;
import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.DadoValor;
import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.model.Prestadora;
import br.com.nois.sa.rc.repository.AgenciaRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Util;

public class Dado {
	Util util = new Util();
	private LogRepository logRepository;
	private MunicipioRepository municipioRepository;
	private AgenciaRepository agenciaRepository;
	private VersaoRepository versaoRepository;

	@Autowired
	AgenciaControllerImpl agenciaController;

	public Dado(Map<String, Object> repositorios) {
		super();
		this.logRepository = (LogRepository) repositorios.get("log");
		this.municipioRepository = (MunicipioRepository) repositorios.get("municipioRepository");
		this.agenciaRepository = (AgenciaRepository) repositorios.get("agenciaRepository");
		this.versaoRepository = (VersaoRepository) repositorios.get("versaoRepository");
	}

	@Autowired
	public void run(String caminho, String anoTexto) {
		ArrayList<String> linhas = this.util.fileToString(caminho);
		if (linhas == null || linhas.size() < 1) {
			return;
		}
		AgenciaControllerImpl agenciaController = new AgenciaControllerImpl(this.agenciaRepository, this.logRepository,
				this.versaoRepository);
		List<Agencia> agencias = agenciaController.getAll();

		ArrayList<String> errosInsert = new ArrayList<>();

		// CODMUN;municipio;UF;CODREG;CODPRE;prestadora;Sigla;Abrangencia;Natureza;servico;
		// 0; 1; 2; 3; 4; 5; 6; 7; 8; 9;
		// 0 ; 1 ; 2; 3 ; 4 ; 5 ; 6; 7 ;8
		// codmun;municipio;UF;codpre;prestadora;sigla;abrangencia;natureza;servico
		@SuppressWarnings("unused")
		MunicipioControllerImpl municipioController = new MunicipioControllerImpl(this.municipioRepository,
				this.logRepository, this.versaoRepository);

		@SuppressWarnings("unused")
		PrestadoraControllerImpl prestadoraController = new PrestadoraControllerImpl(this.municipioRepository,
				this.logRepository, this.versaoRepository);

		AnoControllerImpl anoController = new AnoControllerImpl(this.municipioRepository, this.logRepository,
				this.versaoRepository);

		Map<String, Integer> posicaoCabecalho = new HashMap<String, Integer>();

		for (String linha : linhas) {

			String[] linhaSplit = linha.split(";");
			if (linhaSplit[0].toUpperCase().contains("codmun".toUpperCase()) && posicaoCabecalho.isEmpty()) {

				posicaoCabecalho = this.montaCabecalho(linha);

			} else if (!linhaSplit[0].toUpperCase().contains("codmun".toUpperCase()) && !posicaoCabecalho.isEmpty()) {

				Municipio municipio = this.municipioRepository
						.findByCodigo(linhaSplit[posicaoCabecalho.get("codmun".toUpperCase())]);
				if (municipio == null) {

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
					municipio.setIdAgencia(this.getIdAgencia(linhaSplit[2].trim(), agencias));

					municipio = municipioController.insert(municipio);
					if (municipio.getError() != null) {
						System.out.println("[ERRO MUNICIPIO]" + linha);
						continue;
					}
				}

				Prestadora prestadora = municipio.getPrestadoraCodigo(linhaSplit[posicaoCabecalho.get("CODPRE")]);
				if (prestadora == null || prestadora.getError() != null) {
					prestadora = new Prestadora();
					prestadora.setId();
					prestadora.setAbrangencia(linhaSplit[7].trim());
					prestadora.setNatureza(linhaSplit[8].trim());
					prestadora.setNome(linhaSplit[5].trim());
					prestadora.setCodigo(linhaSplit[4].trim());
					prestadora.setServico(linhaSplit[9].trim());
					prestadora.setSigla(linhaSplit[6].trim());

					prestadora = prestadoraController.insert(municipio.getId(), prestadora);
					if (prestadora.getError() != null) {
						System.out.println("[ERRO PRESTADORA]" + linha);
						continue;
					}
				}

				Ano ano = prestadora.getAno(anoTexto);
				List<DadoValor> dadosValor = new ArrayList<DadoValor>();

				if (ano == null) {
					ano = new Ano();
					ano.setId();
					ano.setAno(anoTexto);
					ano.setEditar(true);
					ano.setExibir(true);
				}
				if (ano.getDadoValores() != null && ano.getDadoValores().size() > 0) {
					dadosValor = ano.getDadoValores();
				}

				for (Map.Entry<String, Integer> entry : posicaoCabecalho.entrySet()) {
					// CODMUN;municipio;UF;CODREG;CODPRE;prestadora;Sigla;Abrangencia;Natureza;servico;
					if (!entry.getKey().toUpperCase().contains("CODMUN".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("municipio".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("UF".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("CODREG".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("CODPRE".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("prestadora".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("Sigla".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("Abrangencia".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("Natureza".toUpperCase())
							&& !entry.getKey().toUpperCase().contains("servico".toUpperCase())) {

						DadoValor dadoValor = this.getDado(entry.getKey(), dadosValor);
						if (dadoValor != null) {
							System.out.print(".");// [Ja Existe][" + anoTexto +
													// "]" +
							// linha);
							continue;
						} else {

							dadoValor = new DadoValor(entry.getKey());
							String valor = "";
							if (entry.getValue() < linhaSplit.length) {
								valor = linhaSplit[entry.getValue()];
							}
							dadoValor.setTotal(valor.toUpperCase());
							dadosValor.add(dadoValor);
						}
					}
				}
				ano.setDadoValores(dadosValor);
				ano = anoController.insert(municipio.getId(), prestadora.getId(), ano);
				if (ano.getError() != null) {
					errosInsert.add(linha);
					continue;
				} else {
					System.out.println("[Dado novo][" + anoTexto + "]" + linha);
				}
			}
		}

		if (!errosInsert.isEmpty()) {
			System.out.println("Totado de erros " + errosInsert.size());
			for (String erro : errosInsert) {
				System.out.println("[Dado erro][" + anoTexto + "]" + erro);

			}
		} else {
			System.out.println("Sem erros");
		}
		System.out.println("Tudo atualizado");
	}

	private Map<String, Integer> montaCabecalho(String cabecalho) {
		Map<String, Integer> posicaoDado = new HashMap<String, Integer>();
		String[] dadoCVS = cabecalho.split(";");

		int ii = 0;
		for (String dado : dadoCVS) {
			posicaoDado.put(dado.toUpperCase(), ii++);
		}
		return posicaoDado;
	}

	private String getIdAgencia(String estado, List<Agencia> agencias) {
		for (Agencia agencia : agencias) {
			if (agencia.getNome().contains(estado)) {
				return agencia.getId();
			}
		}
		return "0";
	}

	private DadoValor getDado(String sigla, List<DadoValor> dadosValor) {
		for (DadoValor dadoValor : dadosValor) {
			if (dadoValor.getSigla().equals(sigla)) {
				return dadoValor;
			}
		}
		return null;
	}
}
