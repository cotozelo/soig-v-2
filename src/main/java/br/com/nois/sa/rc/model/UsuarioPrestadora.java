package br.com.nois.sa.rc.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsuarioPrestadora")
public class UsuarioPrestadora {
	@Id
	private String id;
	private Prestadora prestadora_id;
	private String nome;
	private boolean ver;
	private boolean alterar;
	private List<UsuarioDado> usuarioDado;
	private List<UsuarioIndicador> usuarioIndicador;
	private String error = null;

	public UsuarioPrestadora() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Prestadora getPrestadora_id() {
		return prestadora_id;
	}

	public void setPrestadora_id(Prestadora prestadora_id) {
		this.prestadora_id = prestadora_id;
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

	public List<UsuarioDado> getUsuarioDado() {
		return usuarioDado;
	}

	public void setUsuarioDado(List<UsuarioDado> usuarioDado) {
		this.usuarioDado = usuarioDado;
	}

	public List<UsuarioIndicador> getUsuarioIndicador() {
		return usuarioIndicador;
	}

	public void setUsuarioIndicador(List<UsuarioIndicador> usuarioIndicador) {
		this.usuarioIndicador = usuarioIndicador;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
