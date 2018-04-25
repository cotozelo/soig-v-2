package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Eixo;
import br.com.nois.sa.rc.model.json.EixoJSON;

@Document(collection = "eixo")
public class EixoTO extends Eixo {
	public EixoTO() {
		super();
	}

	public EixoTO(EixoJSON json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public EixoTO(String id, String nome) {
		super();
		super.setId(id);
		super.setNome(nome);
	}

	public EixoTO(String id) {
		super();
		super.setId(id);
	}

	@Override
	public String toString() {
		return "EixoTON [id=" + super.getId() + ", nome=" + super.getNome() + "]";
	}
}
