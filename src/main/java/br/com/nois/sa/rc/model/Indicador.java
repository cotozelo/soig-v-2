package br.com.nois.sa.rc.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public abstract class Indicador {

	@Id
	private String id;
	private String sigla;
	private String descricao;
	private String observacao;
	private String grupo_id;
	private String grupo_name;
	private String inclinacao_id;
	private String inclinacao_nome;
	private String unidade_id;
	private String unidade_nome;
	private String grafico;
	private boolean ativo = false;
	private List<Equacao> equacoes;

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

	public String getGrupo_name() {
		return grupo_name;
	}

	public void setGrupo_name(String grupo_name) {
		this.grupo_name = grupo_name;
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

	public String getGrafico() {
		return grafico;
	}

	public void setGrafico(String grafico) {
		this.grafico = grafico;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Equacao> getEquacoes() {
		return equacoes;
	}

	public Equacao getEquacao(String equacao_id) {
		for (int ii = 0; ii < this.equacoes.size(); ii++) {
			if (this.equacoes.get(ii).getId() != null && this.equacoes.get(ii).getId().equals(equacao_id)) {
				return this.equacoes.get(ii);
			}
		}
		return null;
	}

	public void setEquacoes(List<Equacao> equacoes) {
		for (Equacao equacao : equacoes) {
			this.setEquacao(equacao);
		}
	}

	public Equacao setEquacao(Equacao equacao) {
		if (equacao.getId() == null) {
			equacao.setId();
		}

		if (this.equacoes == null) {
			this.equacoes = new ArrayList<Equacao>();
			this.equacoes.add(equacao);
			return equacao;
		}

		for (int ii = 0; ii < this.equacoes.size(); ii++) {
			if (this.equacoes.get(ii).getAno().equals(equacao.getAno())
					&& !this.equacoes.get(ii).getId().equals(equacao.getId())) {
				return null;
			} else if (this.equacoes.get(ii).getId().equals(equacao.getId())) {
				this.equacoes.get(ii).setAtiva(false);
				equacao.setId();
				equacao.setPai_id(this.equacoes.get(ii).getId());
				this.equacoes.add(equacao);

				return equacao;
			}
		}

		this.equacoes.add(equacao);
		return equacao;

	}

	public void update(Indicador indicador) {
		this.id = indicador.getId();
		this.sigla = indicador.getSigla();
		this.descricao = indicador.getDescricao();
		this.observacao = indicador.getObservacao();
		this.grupo_id = indicador.getGrupo_id();
		this.grupo_name = indicador.getGrupo_name();
		this.inclinacao_id = indicador.getInclinacao_id();
		this.inclinacao_nome = indicador.getInclinacao_nome();
		this.unidade_id = indicador.getUnidade_id();
		this.unidade_nome = indicador.getUnidade_nome();
		this.grafico = indicador.getGrafico();
		this.ativo = indicador.isAtivo();
	}

	@Override
	public String toString() {
		return "Indicador [id=" + id + ", sigla=" + sigla + ", descricao=" + descricao + ", observacao=" + observacao
				+ ", grupo_id=" + grupo_id + ", grupo_name=" + grupo_name + ", inclinacao_id=" + inclinacao_id
				+ ", inclinacao_nome=" + inclinacao_nome + ", unidade_id=" + unidade_id + ", unidade_nome="
				+ unidade_nome + ", grafico=" + grafico + ", ativo=" + ativo + ", equacoes=" + equacoes + "]";
	}
}
