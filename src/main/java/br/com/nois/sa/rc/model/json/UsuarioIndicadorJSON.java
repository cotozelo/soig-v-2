package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.UsuarioIndicador;
import br.com.nois.sa.rc.model.to.UsuarioIndicadorTO;

public class UsuarioIndicadorJSON extends UsuarioIndicador {
	public UsuarioIndicadorJSON() {
		super();
	}

	public UsuarioIndicadorJSON(String sigla) {
		super(sigla);
	}

	public UsuarioIndicadorJSON(UsuarioIndicadorTO to) {
		super();
		super.setId(to.getId());
		super.setIndicadorId(to.getIndicadorId());
		super.setEditar(to.isEditar());
		super.setFavorito(to.isFavorito());
		super.setSigla(to.getSigla());
		super.setVer(to.isVer());
	}
}
