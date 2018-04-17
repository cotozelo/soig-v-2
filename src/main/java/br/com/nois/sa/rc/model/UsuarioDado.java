package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class UsuarioDado {

	@Id
	private String id;
	private String dadoId;
	private String sigla;
	private boolean favorito = false;
	private boolean ver = false;
	private boolean editar = false;

	public UsuarioDado() {
	}

	public UsuarioDado(String sigla) {
		this.sigla = sigla;
		this.id = ObjectId.get().toString();
	}

	public String getId() {
		if (this.id == null || this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

	public boolean isVer() {
		return ver;
	}

	public void setVer(boolean ver) {
		this.ver = ver;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDadoId() {
		return dadoId;
	}

	public void setDadoId(String dadoId) {
		this.dadoId = dadoId;
	}

	@Override
	public String toString() {
		return "UsuarioDado [id=" + id + ", dadoId=" + dadoId + ", sigla=" + sigla + ", favorito=" + favorito + ", ver="
				+ ver + ", editar=" + editar + "]";
	}
}
