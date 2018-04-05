package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsuarioDado")
public class UsuarioDado {

	@Id
	private String id;
	private String dado_id;
	private boolean ler = false;
	private boolean alterar = false;

	public UsuarioDado() {
		super();
	}

	public UsuarioDado(String dado_id) {
		super();
		this.id = ObjectId.get().toString();
		this.dado_id = dado_id;
	}

	public UsuarioDado(String dado_id, boolean ler, boolean alterar) {
		super();
		this.id = ObjectId.get().toString();
		this.dado_id = dado_id;
		this.ler = ler;
		this.alterar = alterar;
	}

	public UsuarioDado(String id, String dado_id, boolean ler, boolean alterar) {
		super();
		this.id = id;
		this.dado_id = dado_id;
		this.ler = ler;
		this.alterar = alterar;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getDado_id() {
		return dado_id;
	}

	public void setDado_id(String dado_id) {
		this.dado_id = dado_id;
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

	@Override
	public String toString() {
		return "UsuarioDado [id=" + id + ", dado_id=" + dado_id + ", ler=" + ler + ", alterar=" + alterar + "]";
	}

	public void update(UsuarioDado usuarioDado) {
		this.dado_id = usuarioDado.getDado_id();
		this.ler = usuarioDado.isLer();
		this.alterar = usuarioDado.isAlterar();

	}
}
