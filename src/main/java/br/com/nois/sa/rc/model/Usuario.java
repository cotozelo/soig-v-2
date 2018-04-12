package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Usuario {
	@Id
	private String id;
	private String nome;
	private String nomeDeUsuario;
	private String telefone;
	private String perfilId;
	private String email;
	private boolean admin = false;
	private boolean ativo = false;

	public Usuario() {
		this.id = ObjectId.get().toString();
	}

	public Usuario(String nome) {
		this.nome = nome;
		this.id = ObjectId.get().toString();
	}

	public String getId() {
		if (this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(String perfilId) {
		this.perfilId = perfilId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setId(String id) {
		this.id = id;
	}
}
