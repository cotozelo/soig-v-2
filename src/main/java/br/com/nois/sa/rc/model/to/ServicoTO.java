package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Servico;
import br.com.nois.sa.rc.model.json.ServicoJSON;

@Document(collection = "servico")
public class ServicoTO extends Servico {

	public ServicoTO() {
		super();
	}

	public ServicoTO(ServicoJSON json) {
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
