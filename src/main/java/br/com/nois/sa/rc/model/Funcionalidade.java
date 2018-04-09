package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Funcionalidade {

	@Id
	private String id;
	private String nome;

	public Funcionalidade() {
		super();
		this.id = ObjectId.get().toString();
	}

	public Funcionalidade(String nome) {
		super();
		this.nome = nome;
		this.id = ObjectId.get().toString();
	}

	public String getId() {
		if (this.id.isEmpty()) {
			this.id = ObjectId.get().toString();
		}
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
		return "Funcionalidade [id=" + this.id + ", nome=" + this.getNome() + "]";
	}
}