package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Inclinacao;
import br.com.nois.sa.rc.model.json.InclinacaoJSON;

@Document(collection = "inclinacao")
public class InclinacaoTO  extends Inclinacao{

	public InclinacaoTO() {
		super();
	}

	public InclinacaoTO(InclinacaoJSON json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public InclinacaoTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public InclinacaoTO(String id) {
		super();
		super.setId(id);
	}

}
