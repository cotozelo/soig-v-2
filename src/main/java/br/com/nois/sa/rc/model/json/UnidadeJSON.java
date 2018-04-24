package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Unidade;
import br.com.nois.sa.rc.model.to.UnidadeTO;

public class UnidadeJSON extends Unidade {

	public UnidadeJSON() {
		super();
	}

	public UnidadeJSON(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public UnidadeJSON(UnidadeTO to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public UnidadeJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "UnidadeJSON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}

}
