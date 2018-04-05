package br.com.nois.sa.rc.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.util.Util;

@Document(collection = "municipio")
public class Municipio {

	@Id
	private String id;
	private String idAgencia;
	private String nome;
	private String codigo;
	private String cidade;
	private String estado;
	private String contatoTelefone;
	private String contatoNome;
	private String contatoEmail;
	private Boolean ativa = false;
	private List<Prestadora> prestadoras;
	private String error = null;

	public Municipio() {
		super();
	}

	public Municipio(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return this.error;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getContatoTelefone() {
		return contatoTelefone;
	}

	public void setContatoTelefone(String contatoTelefone) {
		this.contatoTelefone = contatoTelefone;
	}

	public String getContatoNome() {
		return contatoNome;
	}

	public void setContatoNome(String contatoNome) {
		this.contatoNome = contatoNome;
	}

	public String getContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = contatoEmail;
	}

	public List<Prestadora> getPrestadoras() {
		return prestadoras;
	}

	public void setPrestadoras(List<Prestadora> prestadoras) {
		this.prestadoras = prestadoras;
	}

	public Prestadora getPrestadora(String prestadora_id) {
		for (int ii = 0; ii < this.prestadoras.size(); ii++) {
			if (this.prestadoras.get(ii).getId() != null && this.prestadoras.get(ii).getId().equals(prestadora_id)) {
				return this.prestadoras.get(ii);
			}
		}
		return null;
	}

	public Prestadora getPrestadoraCodigo(String codigo) {
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
		for (Prestadora prestadora : this.prestadoras) {
			if (prestadora.getId().equals(id)) {
				this.prestadoras.remove(prestadora);
				return;
			}
		}
	}

	public Prestadora setPrestadora(Prestadora prestadora) {
		if (prestadora.getId() == null) {
			prestadora.setId();
		}
		if (this.prestadoras == null) {
			this.prestadoras = new ArrayList<Prestadora>();
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

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public String getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(String idAgencia) {
		this.idAgencia = idAgencia;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return "Municipio [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", cidade=" + cidade + ", estado="
				+ estado + ", contatoTelefone=" + contatoTelefone + ", contatoNome=" + contatoNome + ", contatoEmail="
				+ contatoEmail + ", ativa=" + ativa + ", idAgencia = " + idAgencia + " \n\t\t\tprestadoras=["
				+ new Util().ListColectionToString((List<Object>) (List<?>) prestadoras) + "]]";
	}

	public void update(Municipio municipio) {
		this.nome = municipio.getNome();
		this.codigo = municipio.getCodigo();
		this.cidade = municipio.getCidade();
		this.estado = municipio.getEstado();
		this.contatoTelefone = municipio.getContatoTelefone();
		this.contatoNome = municipio.getContatoNome();
		this.contatoEmail = municipio.getContatoEmail();
		this.ativa = municipio.getAtiva();
		this.idAgencia = municipio.getIdAgencia();
	}
}
