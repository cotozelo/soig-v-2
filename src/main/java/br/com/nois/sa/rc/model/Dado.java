package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dado")
public class Dado {
	@Id
	private String id;
	private String sigla;
	private String descricao;
	private String observacao;
	private String grupo_id;
	private String grupo_nome;
	private String inclinacao_id;
	private String inclinacao_nome;
	private String unidade_id;
	private String unidade_nome;
	private Grafico grafico;
	private TipoDado tipoDado;
	private boolean ativo = false;
	private boolean decimal;
	private String error = null;
	private Double valor; //Apenas para testes
	

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	

	public Dado() {
		super();
	}

	public Dado(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return this.error;
	}

	public String getId() {
		return id;
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

	public String getGrupo_id() {
		return grupo_id;
	}

	public void setGrupo_id(String grupo_id) {
		this.grupo_id = grupo_id;
	}

	public String getGrupo_nome() {
		return grupo_nome;
	}

	public void setGrupo_nome(String grupo_nome) {
		this.grupo_nome = grupo_nome;
	}

	public String getInclinacao_id() {
		return inclinacao_id;
	}

	public void setInclinacao_id(String inclinacao_id) {
		this.inclinacao_id = inclinacao_id;
	}

	public String getInclinacao_nome() {
		return inclinacao_nome;
	}

	public void setInclinacao_nome(String inclinacao_nome) {
		this.inclinacao_nome = inclinacao_nome;
	}

	public String getUnidade_id() {
		return unidade_id;
	}

	public void setUnidade_id(String unidade_id) {
		this.unidade_id = unidade_id;
	}

	public String getUnidade_nome() {
		return unidade_nome;
	}

	public void setUnidade_nome(String unidade_nome) {
		this.unidade_nome = unidade_nome;
	}

	public Grafico getGrafico() {
		return grafico;
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
				+ ", grupo_id=" + grupo_id + ", grupo_nome=" + grupo_nome + ", inclinacao_id=" + inclinacao_id
				+ ", inclinacao_nome=" + inclinacao_nome + ", unidade_id=" + unidade_id + ", unidade_nome="
				+ unidade_nome + ", grafico=" + grafico + ", tipoDado=" + tipoDado + ", ativo=" + ativo + ", decimal="
				+ decimal + "]";
	}

}
