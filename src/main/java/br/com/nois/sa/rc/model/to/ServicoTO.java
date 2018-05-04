package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.NomeId;

@Document(collection = "servico")
public class ServicoTO extends NomeId {

	public ServicoTO() {
		super();
	}

	public ServicoTO(NomeId json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public ServicoTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public ServicoTO(String id) {
		super();
		super.setId(id);
	}

}
