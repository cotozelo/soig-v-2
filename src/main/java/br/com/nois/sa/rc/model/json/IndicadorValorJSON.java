package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.IndicadorValor;

public class IndicadorValorJSON extends IndicadorValor {

	private String inclinacaoId;
	private String inclinacaoNome;

	public IndicadorValorJSON() {
		super();
	}

	public IndicadorValorJSON(IndicadorValor indicadorValor) {
		super(indicadorValor);
	}

	public String getInclinacaoNome() {
		return inclinacaoNome;
	}

	public String getInclinacaoId() {
		return inclinacaoId;
	}

	public void setInclinacaoId(String inclinacaoId) {
		this.inclinacaoId = inclinacaoId;
	}

	public void setInclinacaoNome(String inclinacaoNome) {
		this.inclinacaoNome = inclinacaoNome;
	}

}
