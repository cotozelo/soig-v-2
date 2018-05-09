package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;

public class ServicoJSON extends NomeId {

	public ServicoJSON() {
		super();
	}

	public ServicoJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public ServicoJSON(NomeId to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public ServicoJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "ServicoJSON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}
}
