package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Funcionalidade;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;

public class FuncionalidadeJSON extends Funcionalidade {
	private boolean selecionada = false;

	public FuncionalidadeJSON() {
		super();
	}

	public FuncionalidadeJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
		this.selecionada = selecionada;
	}

	public FuncionalidadeJSON(FuncionalidadeTO to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
		this.selecionada = false;
	}

	public FuncionalidadeJSON(String nome) {
		super();
		super.setNome(nome);
	}

	public boolean isSelecionada() {
		return selecionada;
	}

	public void setSelecionada(boolean selecionada) {
		this.selecionada = selecionada;
	}

	@Override
	public String toString() {
		return "FuncionalidadeJSON [id=" + super.getId() + ", nome=" + super.getNome() + ", selecionada=" + selecionada
				+ "]";
	}
}
