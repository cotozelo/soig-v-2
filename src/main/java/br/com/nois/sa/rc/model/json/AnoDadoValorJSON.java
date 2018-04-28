package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Ano;

public class AnoDadoValorJSON extends Ano {
	public AnoDadoValorJSON() {
		super();
	}

	private DadoValorJSON dadoValor;

	public void update(AnoDadoValoresJSON ano) {
		super.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
	}

	public DadoValorJSON getDadoValor() {
		return dadoValor;
	}

	public void setDadoValor(DadoValorJSON dadoValor) {
		this.dadoValor = dadoValor;
	}

	@Override
	public String toString() {
		return "Ano [id=" + super.getId() + ", ano=" + super.getAno() + ", editar=" + super.isEditar() + ", exibir="
				+ super.isExibir() + "]";
	}

}
