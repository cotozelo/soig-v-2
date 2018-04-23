package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Dado {
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
	private Grafico grafico;
	private TipoDado tipoDado;
	private boolean ativo = false;
	private boolean decimal;

	public Dado() {
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
		this.grupoNome = grupoNome;
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
		this.inclinacaoNome = inclinacaoNome;
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
		this.unidadeNome = unidadeNome;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Grafico getGrafico() {
		return this.grafico;
	}

	public void setGrafico(Grafico grafico) {
		this.grafico = grafico;
	}

	public TipoDado getTipoDado() {
		return tipoDado;
	}

	public void setTipoDado(TipoDado tipoDado) {
		this.tipoDado = tipoDado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isDecimal() {
		return decimal;
	}

	public void setDecimal(boolean decimal) {
		this.decimal = decimal;
	}

	@Override
	public String toString() {
		return "Dado [id=" + id + ", sigla=" + sigla + ", descricao=" + descricao + ", observacao=" + observacao
				+ ", grupo_id=" + grupoId + ", grupo_nome=" + grupoNome + ", inclinacao_id=" + inclinacaoId
				+ ", inclinacao_nome=" + inclinacaoNome + ", unidade_id=" + unidadeId + ", unidade_nome=" + unidadeNome
				+ ", grafico=" + "grafico" + ", tipoDado=" + tipoDado + ", ativo=" + ativo + ", decimal=" + decimal
				+ "]";
	}

}
