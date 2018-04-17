package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Perfil {

	@Id
	private String id;
	private String nome;

	public Perfil(String nome) {
		this.nome = nome;
	}

	public Perfil() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		if (this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public abstract String toString();
}
