package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.json.AnoJSON;
import br.com.nois.sa.rc.util.Util;

@Document(collection = "ano")
public class AnoTO extends Ano {
	public AnoTO() {
		super();
	}

	public AnoTO(AnoJSON json) {
		super();
		super.setAno(json.getAno());
		super.setEditar(json.isEditar());
		super.setExibir(json.isExibir());
		super.setId(json.getId());
	}

	private List<IndicadorValorTO> indicadorValores;
	private List<DadoValorTO> dadoValores;

	public List<IndicadorValorTO> getIndicadorValores() {
		return indicadorValores;
	}

	public void setIndicadorValores(List<IndicadorValorTO> indicadorValores) {
		this.indicadorValores = indicadorValores;
	}

	public void setIndicador(List<IndicadorTO> indicadoresTO) {
		if (this.indicadorValores == null || this.indicadorValores.isEmpty()) {
			this.indicadorValores = new ArrayList<IndicadorValorTO>();
		}

		for (IndicadorTO indicadorTO : indicadoresTO) {
			this.indicadorValores.add(new IndicadorValorTO(indicadorTO));
		}
	}

	public List<DadoValorTO> getDadoValores() {
		return dadoValores;
	}

	public void setDadoValores(List<DadoValorTO> dadoValores) {
		this.dadoValores = dadoValores;
	}

	public void setDado(List<DadoTO> dadosTO) {
		if (this.dadoValores == null || this.dadoValores.isEmpty()) {
			this.dadoValores = new ArrayList<DadoValorTO>();
		}

		for (DadoTO dadoTO : dadosTO) {
			this.dadoValores.add(new DadoValorTO(dadoTO));
		}
	}

	public void update(AnoTO ano) {
		super.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
		this.dadoValores = ano.getDadoValores() != null ? ano.getDadoValores() : this.dadoValores;
		this.indicadorValores = ano.getIndicadorValores() != null ? ano.getIndicadorValores() : this.indicadorValores;
	}

	public void update(AnoJSON ano) {
		super.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return "Ano [id=" + super.getId() + ", ano=" + super.getAno() + ", editar=" + super.isEditar() + ", exibir="
				+ super.isExibir() + ", \n\t\t\tindicadorValores=["
				+ new Util().ListColectionToString(((List<Object>) (List<?>) indicadorValores))
				+ "], \n\t\t\tdadoValores=[" + new Util().ListColectionToString((List<Object>) (List<?>) dadoValores)
				+ "]]";
	}
}
