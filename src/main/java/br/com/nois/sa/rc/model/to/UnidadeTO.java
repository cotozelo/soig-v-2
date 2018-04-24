package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Unidade;
import br.com.nois.sa.rc.model.json.UnidadeJSON;

@Document(collection = "unidade")
public class UnidadeTO extends Unidade {

	public UnidadeTO() {
		super();
	}

	public UnidadeTO(UnidadeJSON json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public UnidadeTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public UnidadeTO(String nome) {
		super();
		super.setNome(nome);
	}

}
