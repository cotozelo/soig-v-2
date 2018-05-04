package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;

public class GraficoJSON extends NomeId {
	
	public GraficoJSON() {
		super();
	}

	public GraficoJSON( String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}
	
	@Override
	public String toString() {
		return "GraficoJSON " + super.toString();
	}
}
