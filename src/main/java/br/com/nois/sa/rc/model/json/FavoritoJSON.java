package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.TipoFavorito;

public class FavoritoJSON {
	private String agenciaId;
	private String municipioId;
	private String prestadoraId;
	private String sigla;
	private TipoFavorito tipo;
	private boolean favorito;

	public String getAgenciaId() {
		return agenciaId;
	}

	public void setAgenciaId(String agenciaId) {
		this.agenciaId = agenciaId;
	}

	public String getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}

	public String getPrestadoraId() {
		return prestadoraId;
	}

	public void setPrestadoraId(String prestadoraId) {
		this.prestadoraId = prestadoraId;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public boolean isFavorito() {
		return favorito;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

	public TipoFavorito getTipo() {
		return tipo;
	}

	public void setTipo(TipoFavorito tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "FavoritoJSON [agenciaId=" + agenciaId + ", municipioId=" + municipioId + ", prestadoraId="
				+ prestadoraId + ", sigla=" + sigla + ", tipo=" + tipo + ", favorito=" + favorito + "]";
	}
}
