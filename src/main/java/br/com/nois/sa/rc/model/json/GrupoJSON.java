package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;

public class GrupoJSON extends NomeId {

	public GrupoJSON() {
		super();
	}

	public GrupoJSON(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public GrupoJSON(NomeId to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public GrupoJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "GrupoJSON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}

}
