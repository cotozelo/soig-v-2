package br.com.nois.sa.rc.model;

import java.util.Date;

public abstract class Relatorio {
	private String nomeTela;
	private String tituloDocumento;
	private String agenciaNome;
	private String municipioNome;
	private String prestadoraNome;
	private String usuario;
	private Date data;

	public Relatorio() {
		super();
	}

	public String getNomeTela() {
		return nomeTela;
	}

	public void setNomeTela(String nomeTela) {
		this.nomeTela = nomeTela;
	}

	public String getTituloDocumento() {
		return tituloDocumento;
	}

	public void setTituloDocumento(String tituloDocumento) {
		this.tituloDocumento = tituloDocumento;
	}

	public String getAgenciaNome() {
		return agenciaNome;
	}

	public void setAgenciaNome(String agenciaNome) {
		this.agenciaNome = agenciaNome;
	}

	public String getMunicipioNome() {
		return municipioNome;
	}

	public void setMunicipioNome(String municipioNome) {
		this.municipioNome = municipioNome;
	}

	public String getPrestadoraNome() {
		return prestadoraNome;
	}

	public void setPrestadoraNome(String prestadoraNome) {
		this.prestadoraNome = prestadoraNome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
