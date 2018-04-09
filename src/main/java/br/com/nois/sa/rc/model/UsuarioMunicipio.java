package br.com.nois.sa.rc.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsuarioMunicipio")
public class UsuarioMunicipio {
	@Id
	private String id;
	private Municipio municipio_id;
	private String nome;
	private boolean ver;
	private boolean alterar;
	private List<UsuarioPrestadora> usuarioPrestador;
	private String error = null;

	public UsuarioMunicipio() {
	}

	public UsuarioMunicipio(String error) {
		this.error = error;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public Municipio getMunicipio_id() {
		return municipio_id;
	}

	public void setMunicipio_id(Municipio municipio_id) {
		this.municipio_id = municipio_id;
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

	public List<UsuarioPrestadora> getUsuarioPrestador() {
		return usuarioPrestador;
	}

	public void setUsuarioPrestador(List<UsuarioPrestadora> usuarioPrestador) {
		this.usuarioPrestador = usuarioPrestador;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
