package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "unidade")
public class Unidade {
	@Id
	private String id;
	private String nome;

	public Unidade() {
		super();
	}

	public Unidade(String nome) {
		super();
		this.id = ObjectId.get().toString();
		this.nome = nome;
	}

	public Unidade(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
}