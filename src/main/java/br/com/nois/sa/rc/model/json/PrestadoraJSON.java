package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Prestadora;

public class PrestadoraJSON extends Prestadora {
	// List<AnoJSON> anos = new ArrayList<AnoJSON>();

	public PrestadoraJSON() {
		super();
	}

	public PrestadoraJSON(Prestadora to) {
		super();
		super.setAbrangenciaId(to.getAbrangenciaId());
		super.setAbrangenciaNome(to.getAbrangenciaNome());
		super.setCodigo(to.getCodigo());
		super.setId(to.getId());
		super.setNatureza(to.getNatureza());
		super.setNome(to.getNome());
		super.setServicoId(to.getServicoId());
		super.setServicoNome(to.getServicoNome());
		super.setSigla(to.getSigla());
		super.setContatoNome(to.getContatoNome());
		super.setContatoTelefone(to.getContatoTelefone());
		super.setContatoEmail(to.getContatoEmail());
	}

	@Override
	public String toString() {
		return "Prestadora [id=" + super.getId() + ", nome=" + super.getNome() + ", codigo=" + super.getCodigo()
				+ ", sigla=" + super.getSigla() + ", abrangenciaId=" + super.getAbrangenciaId() + ", abrangenciaNome="
				+ super.getAbrangenciaNome() + ", natureza=" + super.getNatureza() + ", servicoId="
				+ super.getServicoId() + ", servicoNome=" + super.getServicoNome() + "]";
	}

	public void update(PrestadoraJSON prestadora) {
		super.setNome(prestadora.getNome());
		super.setCodigo(prestadora.getCodigo());
		super.setAbrangenciaId(prestadora.getAbrangenciaId());
		super.setAbrangenciaNome(prestadora.getAbrangenciaNome());
		super.setId(prestadora.getId());
		super.setNatureza(prestadora.getNatureza());
		super.setServicoId(prestadora.getServicoId());
		super.setServicoNome(prestadora.getServicoNome());
		super.setSigla(prestadora.getSigla());
		super.setContatoNome(prestadora.getContatoNome());
		super.setContatoTelefone(prestadora.getContatoTelefone());
		super.setContatoEmail(prestadora.getContatoEmail());
	}
}
