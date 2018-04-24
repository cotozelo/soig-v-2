package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Grupo;
import br.com.nois.sa.rc.model.json.GrupoJSON;

@Document(collection = "grupo")
public class GrupoTO extends Grupo {

	public GrupoTO() {
		super();
	}	

	public GrupoTO(GrupoJSON json) {
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
