package br.com.nois.sa.rc.model.json;

import java.util.List;

public class ColunaJSON extends PosicaoValorJSON{

	private List<PosicaoValorJSON> linhas;

	public List<PosicaoValorJSON> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<PosicaoValorJSON> linhas) {
		this.linhas = linhas;
	}
}
