package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;

public class FuncionalidadeJSON extends NomeId {

	public FuncionalidadeJSON() {
		super();
	}

	public FuncionalidadeJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public FuncionalidadeJSON(NomeId to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public FuncionalidadeJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "FuncionalidadeJSON " + super.toString();
	}
}
