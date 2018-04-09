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
import br.com.nois.sa.rc.model.IndicadorValor;
import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.model.Prestadora;
import br.com.nois.sa.rc.model.TipoCalculo;
import br.com.nois.sa.rc.repository.AgenciaRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.MunicipioRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Util;

public class Indicador {
	Util util = new Util();
	private LogRepository logRepository;
	private MunicipioRepository municipioRepository;
	private AgenciaRepository agenciaRepository;
	private VersaoRepository versaoRepository;

	@Autowired
	AgenciaControllerImpl agenciaController;

	public Indicador(Map<String, Object> repositorios) {
		super();
		this.logRepository = (LogRepository) repositorios.get("log");
		this.municipioRepository = (MunicipioRepository) repositorios.get("municipioRepository");
		this.versaoRepository = (VersaoRepository) repositorios.get("versaoRepository");
		this.agenciaRepository = (AgenciaRepository) repositorios.get("agenciaRepository");

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

		MunicipioControllerImpl municipioController = new MunicipioControllerImpl(this.municipioRepository,
				this.logRepository, this.versaoRepository);

		PrestadoraControllerImpl prestadoraController = new PrestadoraControllerImpl(this.municipioRepository,
				this.logRepository, this.versaoRepository);

		ArrayList<String> errosInsert = new ArrayList<>();

		AnoControllerImpl anoController = new AnoControllerImpl(this.municipioRepository, this.logRepository,
				this.versaoRepository);

		Map<String, Integer> posicaoCabecalho = new HashMap<String, Integer>();

		for (String linha : linhas) {

			String[] linhaSplit = linha.split(";");
			if (linhaSplit[0].toUpperCase().contains("CODMUN") && posicaoCabecalho.isEmpty()) {

				posicaoCabecalho = this.montaCabecalho(linha);

			} else if (!linhaSplit[0].toUpperCase().contains("CODMUN") && !posicaoCabecalho.isEmpty()) {

				Municipio municipio = this.municipioRepository.findByCodigo(linhaSplit[posicaoCabecalho.get("CODMUN")]);
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
				List<IndicadorValor> indicadoresValor = new ArrayList<IndicadorValor>();

				if (ano == null) {
					ano = new Ano();
					ano.setId();
					ano.setAno(anoTexto);
					ano.setEditar(true);
					ano.setExibir(true);
				}
				if (ano.getIndicadorValores() != null && ano.getIndicadorValores().size() > 0) {
					indicadoresValor = ano.getIndicadorValores();
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

						IndicadorValor indicadorValor = this.getIndicador(entry.getKey(), indicadoresValor);
						if (indicadorValor != null) {
							System.out.print(".");// [Ja Existe][" + anoTexto +
													// "]" +
							// linha);
							continue;
						} else {
							indicadorValor = new IndicadorValor(TipoCalculo.ACUMLADO, entry.getKey());
							String valor = "";
							if (entry.getValue() < linhaSplit.length) {
								valor = linhaSplit[entry.getValue()];
							}
							indicadorValor.setTotal(valor.toUpperCase());
							indicadoresValor.add(indicadorValor);
						}
					}
				}
				ano.setIndicadorValores(indicadoresValor);
				ano = anoController.insert(municipio.getId(), prestadora.getId(), ano);
				if (ano.getError() != null) {
					errosInsert.add(linha);
					continue;
				} else {
					System.out.println("[Ind novo][" + anoTexto + "]" + linha);
				}
			}
		}

		if (!errosInsert.isEmpty()) {
			System.out.println("Totado de erros " + errosInsert.size());
			for (String erro : errosInsert) {
				System.out.println("[Ind Erro][" + anoTexto + "]" + erro);

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

	private IndicadorValor getIndicador(String sigla, List<IndicadorValor> indicadoresValor) {
		for (IndicadorValor indicadorValor : indicadoresValor) {
			if (indicadorValor.getIndicador_id().equals(sigla)) {
				return indicadorValor;
			}
		}
		return null;
	}

}
