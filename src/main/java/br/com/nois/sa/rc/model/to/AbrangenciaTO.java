package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Abrangencia;
import br.com.nois.sa.rc.model.json.AbrangenciaJSON;

@Document(collection = "abrangencia")
public class AbrangenciaTO extends Abrangencia {

	public AbrangenciaTO() {
		super();
	}

	public AbrangenciaTO(AbrangenciaJSON json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public AbrangenciaTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public AbrangenciaTO(String id) {
		super();
		super.setId(id);
	}

}
