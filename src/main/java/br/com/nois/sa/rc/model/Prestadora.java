package br.com.nois.sa.rc.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prestadora")
public class Prestadora {

	@Id
	private String id;
	private String nome;
	private String codigo;
	private String sigla;
	private String abrangencia;
	private String natureza;
	private String servico;
	private List<Ano> anos;
	private String error = null;

	public Prestadora() {
		super();
	}

	public Prestadora(String error) {
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getAbrangencia() {
		return abrangencia;
	}

	public void setAbrangencia(String abrangencia) {
		this.abrangencia = abrangencia;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public List<Ano> getAnos() {
		return anos;
	}

	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}

	public Ano getAno(String ano) {
		if (this.anos != null) {
			for (int ii = 0; ii < this.anos.size(); ii++) {
				if (this.anos.get(ii).getAno() != null && this.anos.get(ii).getAno().equals(ano)) {
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

	public Ano setAno(Ano ano) {
		if (ano.getId() == null) {
			ano.setId();
		}
		if (this.anos == null) {
			this.anos = new ArrayList<Ano>();
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
		return "Prestadora [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", sigla=" + sigla + ", abrangencia="
				+ abrangencia + ", natureza=" + natureza + ", servico=" + servico + ", anos=" + anos + ", error="
				+ error + "]";
	}

	public void update(Prestadora prestadora) {
		this.nome = prestadora.getNome();
		this.codigo = prestadora.getCodigo();
		this.abrangencia = prestadora.getAbrangencia();
		this.anos = prestadora.getAnos();
		this.id = prestadora.getId();
		this.natureza = prestadora.getNatureza();
		this.servico = prestadora.getServico();
		this.sigla = prestadora.getSigla();
	}

}
