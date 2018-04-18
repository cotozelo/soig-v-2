package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Ano;
import br.com.nois.sa.rc.model.Prestadora;
import br.com.nois.sa.rc.model.json.PrestadoraJSON;

@Document(collection = "prestadora")
public class PrestadoraTO extends Prestadora {
	List<AnoTO> anos = new ArrayList<AnoTO>();

	public PrestadoraTO() {
		super();
	}

	public PrestadoraTO(PrestadoraJSON json) {
		super();
		super.setAbrangencia(json.getAbrangencia());
		super.setCodigo(json.getCodigo());
		super.setId(json.getId());
		super.setNatureza(json.getNatureza());
		super.setNome(json.getNome());
		super.setServico(json.getServico());
		super.setSigla(json.getSigla());
		super.setContatoNome(json.getContatoNome());
		super.setContatoTelefone(json.getContatoTelefone());
		super.setContatoEmail(json.getContatoEmail());
	}

	public List<AnoTO> getAnos() {
		return anos;
	}

	public void setAnos(List<AnoTO> anos) {
		this.anos = anos;
	}

	public AnoTO getAno(String ano) {
		if (this.anos != null) {
			for (int ii = 0; ii < this.anos.size(); ii++) {
				if (this.anos.get(ii).getAno() != null && this.anos.get(ii).getAno().equals(ano)) {
					return this.anos.get(ii);
				}
			}
		}
		return null;
	}

	public AnoTO getAnoById(String id) {
		if (this.anos != null) {
			for (int ii = 0; ii < this.anos.size(); ii++) {
				if (this.anos.get(ii).getAno() != null && this.anos.get(ii).getId().equals(id)) {
					return this.anos.get(ii);
				}
			}
		}
		return null;
	}

	public Ano getAnoNumero(String ano) {
		for (int ii = 0; ii < this.anos.size(); ii++) {
			if (this.anos.get(ii).getAno() != null && this.anos.get(ii).getAno().equals(ano)) {
				return this.anos.get(ii);
			}
		}
		return null;
	}

	public void removeAno(String id) {
		for (Ano ano : this.anos) {
			if (ano.getId().equals(id)) {
				this.anos.remove(ano);
				return;
			}
		}
	}

	public Ano setAno(AnoTO ano) {
		if (ano.getId() == null) {
			ano.setId();
		}
		if (this.anos == null) {
			this.anos = new ArrayList<AnoTO>();
			this.anos.add(ano);
		} else {
			for (int ii = 0; ii < this.anos.size(); ii++) {
				if (this.anos.get(ii).getId() != null && this.anos.get(ii).getId().equals(ano.getId())) {
					this.anos.get(ii).update(ano);
					return ano;
				}
			}
			this.anos.add(ano);
		}
		return ano;
	}

	@Override
	public String toString() {
		return "Prestadora [id=" + super.getId() + ", nome=" + super.getNome() + ", codigo=" + super.getCodigo()
				+ ", sigla=" + super.getSigla() + ", abrangencia=" + super.getAbrangencia() + ", natureza="
				+ super.getNatureza() + ", servico=" + super.getServico() + ", anos=" + anos + "]";
	}

	public void update(PrestadoraTO prestadora) {
		super.setNome(prestadora.getNome());
		super.setCodigo(prestadora.getCodigo());
		super.setAbrangencia(prestadora.getAbrangencia());
		this.anos = prestadora.getAnos();
		super.setId(prestadora.getId());
		super.setNatureza(prestadora.getNatureza());
		super.setServico(prestadora.getServico());
		super.setSigla(prestadora.getSigla());
		super.setContatoNome(prestadora.getContatoNome());
		super.setContatoTelefone(prestadora.getContatoTelefone());
		super.setContatoEmail(prestadora.getContatoEmail());
	}
}
