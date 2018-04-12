package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.UsuarioIndicador;
import br.com.nois.sa.rc.model.json.UsuarioIndicadorJSON;

@Document(collection = "UsuarioIndicador")
public class UsuarioIndicadorTO extends UsuarioIndicador {
	public UsuarioIndicadorTO() {
		super();
	}

	public UsuarioIndicadorTO(String sigla) {
		super(sigla);
	}

	public UsuarioIndicadorTO(UsuarioIndicadorJSON json) {
		super();
		super.setId(json.getId());
		super.setIndicadorId(json.getIndicadorId());
		super.setEditar(json.isEditar());
		super.setFavorito(json.isFavorito());
		super.setSigla(json.getSigla());
		super.setVer(json.isVer());
	}
}
