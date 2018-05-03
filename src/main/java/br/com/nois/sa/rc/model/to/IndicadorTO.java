package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Indicador;
import br.com.nois.sa.rc.model.json.IndicadorJSON;

@Document(collection = "indicador")
public class IndicadorTO extends Indicador {

	private List<EquacaoTO> equacoes;

	public IndicadorTO() {
		super();
	}

	public IndicadorTO(IndicadorJSON json) {
		super();
		super.setAtivo(json.isAtivo());
		super.setDescricao(json.getDescricao());
		super.setGrafico(json.getGrafico());
		super.setGrupoId(json.getGrupoId());
		super.setGrupoNome(json.getGrupoNome());
		super.setInclinacaoId(json.getInclinacaoId());
		super.setInclinacaoNome(json.getInclinacaoNome());
		super.setObservacao(json.getObservacao());
		super.setSigla(json.getSigla());
		super.setUnidadeId(json.getUnidadeId());
		super.setUnidadeNome(json.getUnidadeNome());
		super.setId(json.getId());
	}

	public void update(IndicadorJSON json) {
		super.setAtivo(json.isAtivo());
		super.setDescricao(json.getDescricao());
		super.setGrafico(json.getGrafico());
		super.setGrupoId(json.getGrupoId());
		super.setGrupoNome(json.getGrupoNome());
		super.setInclinacaoId(json.getInclinacaoId());
		super.setInclinacaoNome(json.getInclinacaoNome());
		super.setObservacao(json.getObservacao());
		super.setSigla(json.getSigla());
		super.setUnidadeId(json.getUnidadeId());
		super.setUnidadeNome(json.getUnidadeNome());
		super.setId(json.getId());
	}

	public List<EquacaoTO> getEquacoes() {
		if (this.equacoes == null) {
			this.equacoes = new ArrayList<EquacaoTO>();
		}
		return equacoes;
	}

	public List<EquacaoTO> getEquacaoAtiva() {
		List<EquacaoTO> aux = new ArrayList<EquacaoTO>();
		if (this.equacoes != null) {
			for (int ii = 0; ii < this.equacoes.size(); ii++) {
				if (this.equacoes.get(ii).isAtiva()) {
					aux.add(this.equacoes.get(ii));
				}
			}
		}
		return aux;
	}

	public EquacaoTO getEquacao(String equacaoId) {
		for (int ii = 0; ii < this.equacoes.size(); ii++) {
			if (this.equacoes.get(ii).getId() != null && this.equacoes.get(ii).getId().equals(equacaoId)) {
				return this.equacoes.get(ii);
			}
		}
		return null;
	}

	public void setEquacoes(List<EquacaoTO> equacoes) {
		if (this.equacoes != null) {
			this.equacoes = new ArrayList<EquacaoTO>();
		}
		this.equacoes.addAll(equacoes);
	}

	public void setEquacao(EquacaoTO equacao) {
		if (this.equacoes == null) {
			this.equacoes = new ArrayList<EquacaoTO>();
		}
		this.equacoes.add(equacao);
	}

	public void setAnoEquacao(String ano, String equacaoAno) {
		if (this.equacoes == null) {
			this.equacoes = new ArrayList<EquacaoTO>();
			EquacaoTO equacao = new EquacaoTO();
			equacao.setAno(ano);
			equacao.setId();
			equacao.setPaiId("-1");
			equacao.setAtiva(false);
			this.equacoes.add(equacao);
		} else {
			boolean hasAno = false;
			EquacaoTO equacaoTO = new EquacaoTO();
			equacaoTO.setAtiva(false);
			for (EquacaoTO equacao : this.equacoes) {
				if (equacao.getAno().equals(ano)) {
					hasAno = true;
					break;
				}
				if (equacao.getAno().equals(equacaoAno)) {
					equacaoTO.setFormula(equacao.getFormula());
					equacaoTO.setAtiva(true);
				}
			}
			if (hasAno == false) {
				equacaoTO.setAno(ano);
				equacaoTO.setId();
				equacaoTO.setPaiId("-1");
				this.equacoes.add(equacaoTO);
			}
		}
	}

	@Override
	public String toString() {
		return "Indicador [id=" + super.getId() + ", sigla=" + super.getSigla() + ", descricao=" + super.getDescricao()
				+ ", observacao=" + super.getObservacao() + ", grupoId=" + super.getGrupoId() + ", grupoName="
				+ super.getGrupoNome() + ", inclinacaoId=" + super.getInclinacaoId() + ", inclinacaoNome="
				+ super.getInclinacaoNome() + ", unidadeId=" + super.getUnidadeId() + ", unidadeNome="
				+ super.getUnidadeNome() + ", grafico=" + super.getGrafico() + ", ativo=" + super.isAtivo()
				+ ", equacoes=" + (equacoes == null ? "" : equacoes.toString()) + "]";
	}

}
