package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Equacao {

	@Id
	private String id;
	private String ano;
	private String formula;
	private long versaoGlobal;
	private String paiId;
	private Boolean ativa = true;

	public Equacao() {
		super();
	}

	public String getId() {
		if (this.id == null || this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
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

	public String getPaiId() {
		return paiId;
	}

	public void setPaiId(String paiId) {
		this.paiId = paiId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	@Override
	public String toString() {
		return "Equacao [id=" + id + ", ano=" + ano + ", formula=" + formula + ", versaoGlobal=" + versaoGlobal
				+ ", pai_id=" + paiId + ", ativa=" + ativa + "]";
	}

}
