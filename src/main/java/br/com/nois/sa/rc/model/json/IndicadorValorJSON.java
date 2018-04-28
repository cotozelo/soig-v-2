package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.IndicadorValor;

public class IndicadorValorJSON extends IndicadorValor {

	private String inclinacao;

	public IndicadorValorJSON() {
		super();
	}

	public IndicadorValorJSON(IndicadorValor indicadorValor) {
		super(indicadorValor);
	}

	public String getInclinacao() {
		return inclinacao;
	}

	public void setInclinacao(String inclinacao) {
		this.inclinacao = inclinacao;
	}
}
