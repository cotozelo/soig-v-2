package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class UsuarioPrestadora {
	@Id
	private String id;
	private String prestadoraId;
	private String nome;
	private boolean ver;
	private boolean editar;

	public UsuarioPrestadora() {
		this.id = ObjectId.get().toString();
	}

	public UsuarioPrestadora(String nome) {
		this.id = ObjectId.get().toString();
		this.nome = nome;
	}

	public String getId() {
		if (this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrestadoraId() {
		return prestadoraId;
	}

	public void setPrestadoraId(String prestadoraId) {
		this.prestadoraId = prestadoraId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	@Override
	public String toString() {
		return "UsuarioPrestadora [id=" + id + ", prestadoraId=" + prestadoraId + ", nome=" + nome + ", ver=" + ver
				+ ", editar=" + editar + "]";
	}
}
