package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.NomeId;

@Document(collection = "funcionalidade")
public class FuncionalidadeTO extends NomeId {

	public FuncionalidadeTO() {
		super();
	}

	public FuncionalidadeTO(NomeId json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public FuncionalidadeTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public FuncionalidadeTO(String id) {
		super();
		super.setId(id);
	}

}
