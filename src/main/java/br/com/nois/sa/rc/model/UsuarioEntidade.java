package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsuarioEntidade")
public class UsuarioEntidade {

	@Id
	private String id;
	private String entidade_id;
	private boolean ler = false;
	private boolean alterar = false;
	private boolean inativar = false;
	private String error = null;

	public UsuarioEntidade() {
		super();
	}

	public UsuarioEntidade(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return this.error;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getEntidade_id() {
		return entidade_id;
	}

	public void setEntidade_id(String entidade_id) {
		this.entidade_id = entidade_id;
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

	public boolean isInativar() {
		return inativar;
	}

	public void setInativar(boolean inativar) {
		this.inativar = inativar;
	}

	public void update(UsuarioEntidade usuarioEntidade) {
		this.entidade_id = usuarioEntidade.getEntidade_id();
		this.ler = usuarioEntidade.isLer();
		this.alterar = usuarioEntidade.isAlterar();
	}

	@Override
	public String toString() {
		return "UsuarioEntidade [id=" + id + ", entidade_id=" + entidade_id + ", ler=" + ler + ", alterar=" + alterar
				+ ", inativar=" + inativar + "]";
	}
}
