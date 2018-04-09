package br.com.nois.sa.rc.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsuarioAgencia")
public class UsuarioAgencia {

	@Id
	private String id;
	private Agencia agencia_id;
	private String nome;
	private boolean ver;
	private boolean alterar;
	private List<UsuarioMunicipio> usuarioMunicipio;
	private String error = null;

	public UsuarioAgencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Agencia getAgencia_id() {
		return agencia_id;
	}

	public void setAgencia_id(Agencia agencia_id) {
		this.agencia_id = agencia_id;
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

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public List<UsuarioMunicipio> getUsuarioMunicipio() {
		return usuarioMunicipio;
	}

	public void setUsuarioMunicipio(List<UsuarioMunicipio> usuarioMunicipio) {
		this.usuarioMunicipio = usuarioMunicipio;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
