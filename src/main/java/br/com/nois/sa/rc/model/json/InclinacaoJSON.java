package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Inclinacao;
import br.com.nois.sa.rc.model.to.InclinacaoTO;

public class InclinacaoJSON extends Inclinacao{

	public InclinacaoJSON() {
		super();
	}

	public InclinacaoJSON(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}
	
	public InclinacaoJSON(InclinacaoTO to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public InclinacaoJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "FuncionalidadeJSON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}

}
