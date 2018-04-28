package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Municipio;
import br.com.nois.sa.rc.model.json.MunicipioJSON;
import br.com.nois.sa.rc.util.Util;

@Document(collection = "municipio")
public class MunicipioTO extends Municipio {
	private List<PrestadoraTO> prestadoras;

	public MunicipioTO() {
		super();
	}

	public MunicipioTO(MunicipioJSON json) {
		super.setAgenciaId(json.getAgenciaId());
		super.setAtiva(json.isAtiva());
		super.setCidade(json.getCidade());
		super.setCodigo(json.getCodigo());
		super.setContatoEmail(json.getContatoEmail());
		super.setContatoNome(json.getContatoNome());
		super.setContatoTelefone(json.getContatoTelefone());
		super.setEstado(json.getEstado());
		super.setId(json.getId());
		super.setNome(json.getNome());
	}

	public List<PrestadoraTO> getPrestadoras() {
		return prestadoras;
	}

	public void setPrestadoras(List<PrestadoraTO> prestadoras) {
		this.prestadoras = prestadoras;
	}

	public PrestadoraTO getPrestadora(String prestadora_id) {
		for (int ii = 0; ii < this.prestadoras.size(); ii++) {
			if (this.prestadoras.get(ii).getId() != null && this.prestadoras.get(ii).getId().equals(prestadora_id)) {
				return this.prestadoras.get(ii);
			}
		}
		return null;
	}

	public PrestadoraTO getPrestadoraCodigo(String codigo) {
		if (this.prestadoras != null) {
			for (int ii = 0; ii < this.prestadoras.size(); ii++) {
				if (this.prestadoras.get(ii).getCodigo() != null
						&& this.prestadoras.get(ii).getCodigo().equals(codigo)) {
					return this.prestadoras.get(ii);
				}
			}
		}
		return null;
	}

	public void removePrestadora(String id) {
		for (PrestadoraTO prestadora : this.prestadoras) {
			if (prestadora.getId().equals(id)) {
				this.prestadoras.remove(prestadora);
				return;
			}
		}
	}

	public PrestadoraTO setPrestadora(PrestadoraTO prestadora) {
		if (prestadora.getId() == null) {
			prestadora.setId();
		}
		if (this.prestadoras == null) {
			this.prestadoras = new ArrayList<PrestadoraTO>();
			this.prestadoras.add(prestadora);
		} else {
			for (int ii = 0; ii < this.prestadoras.size(); ii++) {
				if (this.prestadoras.get(ii).getId() != null
						&& this.prestadoras.get(ii).getId().equals(prestadora.getId())) {
					this.prestadoras.get(ii).update(prestadora);
					return prestadora;
				}
			}
			this.prestadoras.add(prestadora);
		}
		return prestadora;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return "Municipio [id=" + super.getId() + ", nome=" + super.getNome() + ", codigo=" + super.getCodigo()
				+ ", cidade=" + super.getCidade() + ", estado=" + super.getEstado() + ", contatoTelefone="
				+ super.getContatoTelefone() + ", contatoNome=" + super.getContatoNome() + ", contatoEmail="
				+ super.getContatoEmail() + ", ativa=" + super.isAtiva() + ", idAgencia = " + super.getAgenciaId()
				+ " \n\t\t\tprestadoras=[" + new Util().ListColectionToString((List<Object>) (List<?>) prestadoras)
				+ "]]";
	}
}
