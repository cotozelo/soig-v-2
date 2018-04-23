package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Indicador {

	@Id
	private String id;
	private String sigla;
	private String descricao;
	private String observacao;
	private String grupoId;
	private String grupoNome;
	private String inclinacaoId;
	private String inclinacaoNome;
	private String unidadeId;
	private String unidadeNome;
	private String grafico;
	private boolean ativo = false;

	public Indicador() {
		super();
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		if (sigla != null) {
			this.sigla = sigla.toUpperCase();
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(String grupoId) {
		this.grupoId = grupoId;
	}

	public String getGrupoNome() {
		return grupoNome;
	}

	public void setGrupoNome(String grupoNome) {
		if (grupoNome != null) {
			this.grupoNome = grupoNome.toUpperCase();
		}
	}

	public String getInclinacaoId() {
		return inclinacaoId;
	}

	public void setInclinacaoId(String inclinacaoId) {
		this.inclinacaoId = inclinacaoId;
	}

	public String getInclinacaoNome() {
		return inclinacaoNome;
	}

	public void setInclinacaoNome(String inclinacaoNome) {
		if (inclinacaoNome != null) {
			this.inclinacaoNome = inclinacaoNome.toUpperCase();
		}
	}

	public String getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId(String unidadeId) {
		this.unidadeId = unidadeId;
	}

	public String getUnidadeNome() {
		return unidadeNome;
	}

	public void setUnidadeNome(String unidadeNome) {
		if (unidadeNome != null) {
			this.unidadeNome = unidadeNome.toUpperCase();
		}
	}

	public String getGrafico() {
		return grafico;
	}

	public void setGrafico(String grafico) {
		if (grafico != null) {
			this.grafico = grafico.toUpperCase();
		}
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void update(Indicador indicador) {
		this.id = indicador.getId();
		this.sigla = indicador.getSigla();
		this.descricao = indicador.getDescricao();
		this.observacao = indicador.getObservacao();
		this.grupoId = indicador.getGrupoId();
		this.grupoNome = indicador.getGrupoNome();
		this.inclinacaoId = indicador.getInclinacaoId();
		this.inclinacaoNome = indicador.getInclinacaoNome();
		this.unidadeId = indicador.getUnidadeId();
		this.unidadeNome = indicador.getUnidadeNome();
		this.grafico = indicador.getGrafico();
		this.ativo = indicador.isAtivo();
	}

	@Override
	public String toString() {
		return "Indicador [id=" + id + ", sigla=" + sigla + ", descricao=" + descricao + ", observacao=" + observacao
				+ ", grupoId=" + grupoId + ", grupoNome=" + grupoNome + ", inclinacao_id=" + inclinacaoId
				+ ", inclinacaoNome=" + inclinacaoNome + ", unidadeId=" + unidadeId + ", unidadeNome=" + unidadeNome
				+ ", grafico=" + grafico + ", ativo=" + ativo + "]";
	}
}
