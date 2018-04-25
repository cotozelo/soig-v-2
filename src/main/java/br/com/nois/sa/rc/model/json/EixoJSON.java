package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Eixo;
import br.com.nois.sa.rc.model.to.EixoTO;

public class EixoJSON extends Eixo {
	public EixoJSON() {
		super();
	}

	public EixoJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public EixoJSON(EixoTO to) {
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