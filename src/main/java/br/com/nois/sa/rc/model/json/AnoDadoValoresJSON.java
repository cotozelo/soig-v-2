package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;

import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.to.AnoTO;
import br.com.nois.sa.rc.model.to.DadoValorTO;

public class AnoDadoValoresJSON extends Ano {
	public AnoDadoValoresJSON() {
		super();
	}

	private List<DadoValorJSON> dadoValores;

	public AnoDadoValoresJSON(AnoTO to) {
		super();
		super.setAno(to.getAno());
		super.setEditar(to.isEditar());
		super.setExibir(to.isExibir());
		super.setId(to.getId());

		this.setDadoValores(to.getDadoValores());
	}

	public void update(AnoDadoValoresJSON ano) {
		super.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
	}

	public List<DadoValorJSON> getDadoValores() {
		return dadoValores;
	}

	public void setDadoValores(DadoValorJSON dadoValor) {
		if (this.dadoValores == null) {
			this.dadoValores = new ArrayList<DadoValorJSON>();
		}
		this.dadoValores.add(dadoValor);
	}

	public void setDadoValores(DadoValorTO dadoValor) {
		if (this.dadoValores == null) {
			this.dadoValores = new ArrayList<DadoValorJSON>();
		}
		this.dadoValores.add(new DadoValorJSON(dadoValor));
	}

	public void setDadoValores(List<DadoValorTO> dadoValores) {
		if (this.dadoValores == null) {
			this.dadoValores = new ArrayList<DadoValorJSON>();
		}
		for (DadoValorTO dadoValor : dadoValores) {
			this.dadoValores.add(new DadoValorJSON(dadoValor));
		}
	}

	@Override
	public String toString() {
		return "Ano [id=" + super.getId() + ", ano=" + super.getAno() + ", editar=" + super.isEditar() + ", exibir="
				+ super.isExibir() + "]";
	}

}
