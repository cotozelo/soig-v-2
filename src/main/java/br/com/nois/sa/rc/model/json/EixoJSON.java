package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;


public class EixoJSON extends NomeId {
	public EixoJSON() {
		super();
	}

	public EixoJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public EixoJSON(NomeId to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public EixoJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "EixoJSON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}
}