package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.NomeId;

public class UsuarioFuncionalidadeJSON extends NomeId {
	public UsuarioFuncionalidadeJSON() {
		super();
	}

	public UsuarioFuncionalidadeJSON(NomeId to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}
}
