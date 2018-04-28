package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Servico;
import br.com.nois.sa.rc.model.to.ServicoTO;

public class ServicoJSON extends Servico {

	public ServicoJSON() {
		super();
	}

	public ServicoJSON(String id, String nome, boolean selecionada) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public ServicoJSON(ServicoTO to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	public ServicoJSON(String nome) {
		super();
		super.setNome(nome);
	}

	@Override
	public String toString() {
		return "ServicoJSON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}
}
