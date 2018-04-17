package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.UsuarioDado;
import br.com.nois.sa.rc.model.json.UsuarioDadoJSON;

@Document(collection = "UsuarioDado")
public class UsuarioDadoTO extends UsuarioDado {
	public UsuarioDadoTO() {
		super();
	}

	public UsuarioDadoTO(String sigla) {
		super(sigla);
	}

	public UsuarioDadoTO(UsuarioDadoJSON json) {
		super();
		super.setId(json.getId());
		super.setDadoId(json.getDadoId());
		super.setEditar(json.isEditar());
		super.setFavorito(json.isFavorito());
		super.setSigla(json.getSigla());
		super.setVer(json.isVer());
	}
}
