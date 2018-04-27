package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Abrangencia {

	@Id
	private String id;
	private String nome;

	public Abrangencia() {
		super();
	}

	public String getId() {
		if (this.id == null || this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return "Abrangencia [id=" + this.id + ", nome=" + this.getNome() + "]";
	}
}