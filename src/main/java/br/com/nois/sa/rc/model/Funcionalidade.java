package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funcionalidade")
public class Funcionalidade {
	@Id
	private String id;
	private String nome;
	
	public Funcionalidade() {
		super();
	}

	public Funcionalidade(String nome) {
		super();
		this.id = ObjectId.get().toString();
		this.nome = nome;
	}
	
	public Funcionalidade(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}