package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsuarioIndicador")
public class UsuarioIndicador {

	@Id
	private String id;
	private String indicador_id;
	private boolean ler = false;
	private boolean alterar = false;

	public UsuarioIndicador() {
		super();
	}

	public UsuarioIndicador(String indicador_id) {
		super();
		this.id = ObjectId.get().toString();
		this.indicador_id = indicador_id;
	}

	public UsuarioIndicador(String indicador_id, boolean ler, boolean alterar) {
		super();
		this.id = ObjectId.get().toString();
		this.indicador_id = indicador_id;
		this.ler = ler;
		this.alterar = alterar;
	}

	public UsuarioIndicador(String id, String indicador_id, boolean ler, boolean alterar) {
		super();
		this.id = id;
		this.indicador_id = indicador_id;
		this.ler = ler;
		this.alterar = alterar;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getIndicador_id() {
		return indicador_id;
	}

	public void setIndicador_id(String indicador_id) {
		this.indicador_id = indicador_id;
	}

	public boolean isLer() {
		return ler;
	}

	public void setLer(boolean ler) {
		this.ler = ler;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public void update(UsuarioIndicador usuarioIndicador) {
		this.indicador_id = usuarioIndicador.getIndicador_id();
		this.ler = usuarioIndicador.isLer();
		this.alterar = usuarioIndicador.isAlterar();

	}

	@Override
	public String toString() {
		return "UsuarioIndicador [id=" + id + ", indicador_id=" + indicador_id + ", ler=" + ler + ", alterar=" + alterar
				+ "]";
	}

}
