package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.UsuarioDado;
import br.com.nois.sa.rc.model.to.UsuarioDadoTO;

public class UsuarioDadoJSON extends UsuarioDado {

	public UsuarioDadoJSON() {
		super();
	}

	public UsuarioDadoJSON(String sigla) {
		super(sigla);
	}

	public UsuarioDadoJSON(UsuarioDadoTO to) {
		super();
		super.setId(to.getId());
		super.setDadoId(to.getDadoId());
		super.setEditar(to.isEditar());
		super.setFavorito(to.isFavorito());
		super.setSigla(to.getSigla());
		super.setVer(to.isVer());
	}
}
