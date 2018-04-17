package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.model.to.MunicipioTO;

public class MunicipioJSON extends Municipio {
	// private List<PrestadoraJSON> prestadoras;

	public MunicipioJSON() {
		super();
	}

	public MunicipioJSON(MunicipioTO to) {
		super.setAgenciaId(to.getAgenciaId());
		super.setAtiva(to.isAtiva());
		super.setCidade(to.getCidade());
		super.setCodigo(to.getCodigo());
		super.setContatoEmail(to.getContatoEmail());
		super.setContatoNome(to.getContatoNome());
		super.setContatoTelefone(to.getContatoTelefone());
		super.setEstado(to.getEstado());
		super.setId(to.getId());
		super.setNome(to.getNome());
	}

	/*
	 * public List<PrestadoraJSON> getPrestadoras() { return prestadoras; }
	 * 
	 * public void setPrestadoras(List<PrestadoraJSON> prestadoras) {
	 * this.prestadoras = prestadoras; }
	 * 
	 * public Prestadora getPrestadora(String prestadora_id) { for (int ii = 0;
	 * ii < this.prestadoras.size(); ii++) { if
	 * (this.prestadoras.get(ii).getId() != null &&
	 * this.prestadoras.get(ii).getId().equals(prestadora_id)) { return
	 * this.prestadoras.get(ii); } } return null; }
	 * 
	 * public Prestadora getPrestadoraCodigo(String codigo) { if
	 * (this.prestadoras != null) { for (int ii = 0; ii <
	 * this.prestadoras.size(); ii++) { if (this.prestadoras.get(ii).getCodigo()
	 * != null && this.prestadoras.get(ii).getCodigo().equals(codigo)) { return
	 * this.prestadoras.get(ii); } } } return null; }
	 * 
	 * public void removePrestadora(String id) { for (Prestadora prestadora :
	 * this.prestadoras) { if (prestadora.getId().equals(id)) {
	 * this.prestadoras.remove(prestadora); return; } } }
	 * 
	 * public Prestadora setPrestadora(PrestadoraJSON prestadora) { if
	 * (prestadora.getId() == null) { prestadora.setId(); } if (this.prestadoras
	 * == null) { this.prestadoras = new ArrayList<PrestadoraJSON>();
	 * this.prestadoras.add(prestadora); } else { for (int ii = 0; ii <
	 * this.prestadoras.size(); ii++) { if (this.prestadoras.get(ii).getId() !=
	 * null && this.prestadoras.get(ii).getId().equals(prestadora.getId())) {
	 * this.prestadoras.get(ii).update(prestadora); return prestadora; } }
	 * this.prestadoras.add(prestadora); } return prestadora; }
	 */
	@Override
	public String toString() {
		return "Municipio [id=" + super.getId() + ", nome=" + super.getNome() + ", codigo=" + super.getCodigo()
				+ ", cidade=" + super.getCidade() + ", estado=" + super.getEstado() + ", contatoTelefone="
				+ super.getContatoTelefone() + ", contatoNome=" + super.getContatoNome() + ", contatoEmail="
				+ super.getContatoEmail() + ", ativa=" + super.isAtiva() + ", idAgencia = " + super.getAgenciaId()
				// + " \n\t\t\tprestadoras=[" + new
				// Util().ListColectionToString((List<Object>) (List<?>)
				// prestadoras)
				+ "]]";
	}
}
