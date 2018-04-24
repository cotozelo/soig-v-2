package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Grupo;
import br.com.nois.sa.rc.model.to.GrupoTO;

public class GrupoJSON extends Grupo {

	public GrupoJSON() {
		super();
	}

	public GrupoJSON(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public GrupoJSON(GrupoTO to) {
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
