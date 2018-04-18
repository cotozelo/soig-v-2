package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Prestadora;

public class PrestadoraJSON extends Prestadora {
	// List<AnoJSON> anos = new ArrayList<AnoJSON>();

	public PrestadoraJSON() {
		super();
	}

	public PrestadoraJSON(Prestadora to) {
		super();
		super.setAbrangencia(to.getAbrangencia());
		super.setCodigo(to.getCodigo());
		super.setId(to.getId());
		super.setNatureza(to.getNatureza());
		super.setNome(to.getNome());
		super.setServico(to.getServico());
		super.setSigla(to.getSigla());
		super.setContatoNome(to.getContatoNome());
		super.setContatoTelefone(to.getContatoTelefone());
		super.setContatoEmail(to.getContatoEmail());
	}

	@Override
	public String toString() {
		return "Prestadora [id=" + super.getId() + ", nome=" + super.getNome() + ", codigo=" + super.getCodigo()
				+ ", sigla=" + super.getSigla() + ", abrangencia=" + super.getAbrangencia() + ", natureza="
				+ super.getNatureza() + ", servico=" + super.getServico() + "]";
	}

	public void update(PrestadoraJSON prestadora) {
		super.setNome(prestadora.getNome());
		super.setCodigo(prestadora.getCodigo());
		super.setAbrangencia(prestadora.getAbrangencia());
		super.setId(prestadora.getId());
		super.setNatureza(prestadora.getNatureza());
		super.setServico(prestadora.getServico());
		super.setSigla(prestadora.getSigla());
		super.setContatoNome(prestadora.getContatoNome());
		super.setContatoTelefone(prestadora.getContatoTelefone());
		super.setContatoEmail(prestadora.getContatoEmail());
	}
}
