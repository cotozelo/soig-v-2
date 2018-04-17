package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Funcionalidade;
import br.com.nois.sa.rc.model.json.FuncionalidadeJSON;

@Document(collection = "funcionalidade")
public class FuncionalidadeTO extends Funcionalidade {

	public FuncionalidadeTO() {
		super();
	}

	public FuncionalidadeTO(FuncionalidadeJSON json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public FuncionalidadeTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

}
