package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "equacao")
public class Equacao {

	@Id
	private String id;
	private String ano;
	private String formula;
	private long versaoGlobal;
	private String pai_id;
	private Boolean ativa = true;
	private String error = null;

	public Equacao() {
		super();
	}

	public Equacao(String error) {
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

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public long getVersaoGlobal() {
		return versaoGlobal;
	}

	public void setVersaoGlobal(long versaoGlobal) {
		this.versaoGlobal = versaoGlobal;
	}

	public String getPai_id() {
		return pai_id;
	}

	public void setPai_id(String pai_id) {
		this.pai_id = pai_id;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	@Override
	public String toString() {
		return "Equacao [id=" + id + ", ano=" + ano + ", formula=" + formula + ", versaoGlobal=" + versaoGlobal
				+ ", pai_id=" + pai_id + ", ativa=" + ativa + "]";
	}

}
