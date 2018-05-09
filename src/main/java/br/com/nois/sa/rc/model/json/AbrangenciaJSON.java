package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;
import br.com.nois.sa.rc.model.to.AbrangenciaTO;

public class AbrangenciaJSON extends NomeId {
	public AbrangenciaJSON() {
		super();
	}

	public AbrangenciaJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public AbrangenciaJSON(AbrangenciaTO to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public AbrangenciaJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "AbrangenciaJSON " + super.toString();
	}
}
