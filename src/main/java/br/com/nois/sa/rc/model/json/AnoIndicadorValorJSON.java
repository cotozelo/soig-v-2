package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.IndicadorValorTO;

public class AnoIndicadorValorJSON extends Ano {
	public AnoIndicadorValorJSON() {
		super();
	}

	private List<IndicadorValorJSON> indicadorValores;

	public AnoIndicadorValorJSON(AnoTO to) {
		super();
		super.setAno(to.getAno());
		super.setEditar(to.isEditar());
		super.setExibir(to.isExibir());
		super.setId(to.getId());

		this.setIndicadorValores(to.getIndicadorValores());
	}

	public void update(AnoIndicadorValorJSON ano) {
		super.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
	}

	public List<IndicadorValorJSON> getIndicadorValores() {
		return indicadorValores;
	}

	public void setIndicadorValores(IndicadorValorJSON indicadorValor) {
		if (this.indicadorValores == null) {
			this.indicadorValores = new ArrayList<IndicadorValorJSON>();
		}
		this.indicadorValores.add(indicadorValor);
	}

	public void setIndicadorValores(IndicadorValorTO indicadorValor) {
		if (this.indicadorValores == null) {
			this.indicadorValores = new ArrayList<IndicadorValorJSON>();
		}
		this.indicadorValores.add(new IndicadorValorJSON(indicadorValor));
	}

	public void setIndicadorValores(List<IndicadorValorTO> indicadorValores) {
		if (this.indicadorValores == null) {
			this.indicadorValores = new ArrayList<IndicadorValorJSON>();
		}
		for (IndicadorValorTO indicadorValor : indicadorValores) {
			this.indicadorValores.add(new IndicadorValorJSON(indicadorValor));
		}
	}

	public void setInclinacao(Map<String, String> inclinacao) {
		if (this.indicadorValores != null) {
			for (IndicadorValorJSON indicadorValorJSON : this.indicadorValores) {
				indicadorValorJSON.setInclinacao(inclinacao.get(indicadorValorJSON.getSigla()));
			}
		}
	}

	@Override
	public String toString() {
		return "Ano [id=" + super.getId() + ", ano=" + super.getAno() + ", editar=" + super.isEditar() + ", exibir="
				+ super.isExibir() + "]";
	}

}
