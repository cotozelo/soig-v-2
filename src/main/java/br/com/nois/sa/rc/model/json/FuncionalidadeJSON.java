package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Funcionalidade;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;

public class FuncionalidadeJSON extends Funcionalidade {

	public FuncionalidadeJSON() {
		super();
	}

	public FuncionalidadeJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public FuncionalidadeJSON(FuncionalidadeTO to) {
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
		return "FuncionalidadeJSON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}
}
