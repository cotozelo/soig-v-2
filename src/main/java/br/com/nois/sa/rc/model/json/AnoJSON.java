package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.to.AnoTO;

public class AnoJSON extends Ano {
	public AnoJSON() {
		super();
	}

	public AnoJSON(AnoTO to) {
		super();
		super.setAno(to.getAno());
		super.setEditar(to.isEditar());
		super.setExibir(to.isExibir());
		super.setId(to.getId());
	}

	public void update(AnoJSON ano) {
		super.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
	}

	@Override
	public String toString() {
		return "Ano [id=" + super.getId() + ", ano=" + super.getAno() + ", editar=" + super.isEditar() + ", exibir="
				+ super.isExibir() + "]";
	}

}
