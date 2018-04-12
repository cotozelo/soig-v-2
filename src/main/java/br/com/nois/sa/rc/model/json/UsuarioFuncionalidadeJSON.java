package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Funcionalidade;

public class UsuarioFuncionalidadeJSON extends Funcionalidade {
	public UsuarioFuncionalidadeJSON() {
		super();
	}

	public UsuarioFuncionalidadeJSON(String nome) {
		super(nome);
	}

	public UsuarioFuncionalidadeJSON(Funcionalidade to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}
}
