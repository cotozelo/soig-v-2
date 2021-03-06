package br.com.nois.sa.rc.carga;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.nois.sa.rc.model.to.IndicadorTO;
import br.com.nois.sa.rc.repository.IndicadorRepository;
import br.com.nois.sa.rc.repository.LogRepository;
import br.com.nois.sa.rc.repository.VersaoRepository;
import br.com.nois.sa.rc.util.Util;

public class Equacao {
	Util util = new Util();
	private LogRepository logRepository;
	private IndicadorRepository indicadorRepository;
	private VersaoRepository versaoRepository;

	// @Autowired
	// IndicadorControllerImpl indicadorController;

	public Equacao(Map<String, Object> repositorios) {
		super();
		this.logRepository = (LogRepository) repositorios.get("log");
		this.indicadorRepository = (IndicadorRepository) repositorios.get("indicadorRepository");
		this.versaoRepository = (VersaoRepository) repositorios.get("versaoRepository");

		// this.indicadorController = new
		// IndicadorControllerImpl(indicadorRepository, logRepository,
		// versaoRepository);
	}

	public void run(String caminho) {
		ArrayList<String> linhas = this.util.fileToString(caminho);
		if (linhas == null || linhas.size() < 1) {
			return;
		}
		ArrayList<String> errosInsert = new ArrayList<>();
		for (String linha : linhas) {

			String[] linhaSplit = linha.split(";");
			if (linhaSplit[0].toUpperCase().contains("equacao".toUpperCase())) {

			} else if (linhaSplit.length == 3) {
				IndicadorTO indicador = this.indicadorRepository.findBySigla(linhaSplit[2]);
				if (indicador != null) {
					List<br.com.nois.sa.rc.model.to.EquacaoTO> equacoes = indicador.getEquacoes();
					boolean temEquacao = false;
					if (equacoes != null && equacoes.size() > 0) {
						for (br.com.nois.sa.rc.model.Equacao equacao : equacoes) {
							if (equacao.getAno().equals(linhaSplit[1].toString())) {
								temEquacao = true;
							}
						}
					}
					if (temEquacao == false) {
						br.com.nois.sa.rc.model.to.EquacaoTO equacao = new br.com.nois.sa.rc.model.to.EquacaoTO();
						equacao.setAno(linhaSplit[1]);
						equacao.setFormula(linhaSplit[0]);
						equacao.setAtiva(true);
						indicador.setEquacao(equacao);
						IndicadorTO ob = this.indicadorRepository.save(indicador);
						if (ob == null) {
							System.out
									.println("[ERRO] " + linhaSplit[2] + " | " + linhaSplit[1] + " | " + linhaSplit[0]);
						} else {
							System.out
									.println("[NOVA] " + linhaSplit[2] + " | " + linhaSplit[1] + " | " + linhaSplit[0]);
						}
					} else {
						System.out.println("[TEM] " + linhaSplit[2] + " | " + linhaSplit[1] + " | " + linhaSplit[0]);
					}

				}
			}
		}
	}
}
