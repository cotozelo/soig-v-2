package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;

public class TipoDadoJSON extends NomeId {
	public TipoDadoJSON() {
		super();
	}

	public TipoDadoJSON(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}
	
	@Override
	public String toString() {
		return "GraficoJSON " + super.toString();
	}
}
