package br.com.nois.sa.rc.model.json;

import java.util.List;

import br.com.nois.sa.rc.model.Relatorio;

public class RelatorioJSON extends Relatorio {
	private List<ColunaJSON> tabela;
	
	public RelatorioJSON() {
		super();
	}
	
	public List<ColunaJSON> getTabela() {
		return tabela;
	}

	public void setTabela(List<ColunaJSON> tabela) {
		this.tabela = tabela;
	}
}