package br.com.nois.sa.rc.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "perfil")
public class Perfil {

	@Id
	private String id;
	private String nome;
	private List<Funcionalidade> funcionalidades;

	public Perfil(String nome) {
		super();
		this.id = ObjectId.get().toString();
		this.nome = nome;
	}

	public Perfil(String id, String nome, List<Funcionalidade> funcionalidades) {
		super();
		this.id = id;
		this.nome = nome;
		this.funcionalidades = funcionalidades;
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

	public List<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

}
