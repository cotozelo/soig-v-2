package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class UsuarioAgencia {

	@Id
	private String id;
	private String agenciaId;
	private String nome;
	private boolean ver;
	private boolean editar;

	public UsuarioAgencia() {
		this.id = ObjectId.get().toString();
	}

	public UsuarioAgencia(String nome) {
		this.nome = nome;
		this.id = ObjectId.get().toString();
	}

	public String getId() {
		if (this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgenciaId() {
		return agenciaId;
	}

	public void setAgenciaId(String agenciaId) {
		this.agenciaId = agenciaId;
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
}
