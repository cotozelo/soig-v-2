package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.json.AnoIndicadorValoresJSON;
import br.com.nois.sa.rc.util.Util;

@Document(collection = "ano")
public class AnoTO extends Ano {
	public AnoTO() {
		super();
	}

	public AnoTO(AnoIndicadorValoresJSON json) {
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
		if (this.indicadorValores == null) {
			this.indicadorValores = new ArrayList<IndicadorValorTO>();
		}
		this.indicadorValores.addAll(indicadorValores);
	}

	public void setIndicadorValores(IndicadorValorTO indicadorValor) {
		if (this.indicadorValores == null) {
			this.indicadorValores = new ArrayList<IndicadorValorTO>();
		}
		this.indicadorValores.add(indicadorValor);
	}

	public List<DadoValorTO> getDadoValores() {
		return dadoValores;
	}

	public void setDadoValores(List<DadoValorTO> dadoValores) {
		if (this.dadoValores == null) {
			this.dadoValores = new ArrayList<DadoValorTO>();
		}
		this.dadoValores.addAll(dadoValores);
	}

	public void setIndicadorValores(DadoValorTO dadoValor) {
		if (this.dadoValores == null) {
			this.dadoValores = new ArrayList<DadoValorTO>();
		}
		this.dadoValores.add(dadoValor);
	}

	public void update(AnoTO ano) {
		super.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
	}

	public void update(AnoIndicadorValoresJSON ano) {
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
