package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.NomeId;

@Document(collection = "grupo")
public class GrupoTO extends NomeId {

	public GrupoTO() {
		super();
	}	

	public GrupoTO(NomeId json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}
	
	public GrupoTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public GrupoTO(String id) {
		super();
		super.setId(id);
	}

}
