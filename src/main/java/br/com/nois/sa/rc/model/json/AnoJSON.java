package br.com.nois.sa.rc.model.json;

import org.bson.types.ObjectId;

import br.com.nois.sa.rc.model.to.AnoTO;

public class AnoJSON {
	
	private String id;
	private String ano;
	private String editar = "false";
	private String exibir = "false";
	
	public AnoJSON() {
		super();
	}

	public AnoJSON(AnoTO to) {
		super();
		this.setAno(to.getAno());
		this.setEditar(String.valueOf(to.isEditar()));
		this.setExibir(String.valueOf(to.isExibir()));
		this.setId(to.getId());
	}

	public void update(AnoJSON ano) {
		this.setAno(ano.getAno());
		this.setEditar(ano.isEditar());
		this.setExibir(ano.isExibir());
	}
	
	public String getId() {
		if (this.id == null || this.id.isEmpty())
			this.id = ObjectId.get().toString();
		return this.id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String isEditar() {
		return editar;
	}

	public void setEditar(String editar) {
		this.editar = editar;
	}
	
	public void setEditar(boolean editar) {
		this.editar = String.valueOf(editar);
	}


	public String isExibir() {
		return exibir;
	}

	public void setExibir(String exibir) {
		this.exibir = exibir;
	}
	
	public void setExibir(boolean exibir) {
		this.exibir = String.valueOf(exibir);
	}

	@Override
	public String toString() {
		return "Ano [id=" + this.getId() + ", ano=" + this.getAno() + ", editar=" + this.isEditar() + ", exibir="
				+ this.isExibir() + "]";
	}

}
