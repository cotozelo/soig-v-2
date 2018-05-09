package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.NomeId;

@Document(collection = "unidade")
public class UnidadeTO extends NomeId {

	public UnidadeTO() {
		super();
	}

	public UnidadeTO(NomeId json) {
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
