package br.com.nois.sa.rc.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.util.Util;

@Document(collection = "ano")
public class Ano {
	@Id
	private String id;
	private String ano;
	private boolean editar = false;
	private boolean exibir = false;
	private List<IndicadorValor> indicadorValores;
	private List<IndicadorMeta> indicadorMetas;
	private List<DadoValor> dadoValores;
	private List<DadoMeta> dadoMetas;
	private String error = null;

	public Ano() {
		super();
	}

	public Ano(String error) {
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

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean isExibir() {
		return exibir;
	}

	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}

	public List<IndicadorValor> getIndicadorValores() {
		return indicadorValores;
	}

	public void setIndicadorValores(List<IndicadorValor> indicadorValores) {
		this.indicadorValores = indicadorValores;
	}

	public List<IndicadorMeta> getIndicadorMetas() {
		return indicadorMetas;
	}

	public void setIndicadorMetas(List<IndicadorMeta> indicadorMetas) {
		this.indicadorMetas = indicadorMetas;
	}

	public List<DadoValor> getDadoValores() {
		return dadoValores;
	}

	public void setDadoValores(List<DadoValor> dadoValores) {
		this.dadoValores = dadoValores;
	}

	public List<DadoMeta> getDadoMetas() {
		return dadoMetas;
	}

	public void setDadoMetas(List<DadoMeta> dadoMetas) {
		this.dadoMetas = dadoMetas;
	}

	public void update(Ano ano) {
		this.ano = ano.getAno();
		this.editar = ano.isEditar();
		this.exibir = ano.isExibir();
		this.dadoMetas = ano.getDadoMetas() != null ? ano.getDadoMetas() : this.dadoMetas;
		this.dadoValores = ano.getDadoValores() != null ? ano.getDadoValores() : this.dadoValores;
		this.indicadorMetas = ano.getIndicadorMetas() != null ? ano.getIndicadorMetas() : this.indicadorMetas;
		this.indicadorValores = ano.getIndicadorValores() != null ? ano.getIndicadorValores() : this.indicadorValores;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return "Ano [id=" + id + ", ano=" + ano + ", editar=" + editar + ", exibir=" + exibir
				+ ", \n\t\t\tindicadorValores=["
				+ new Util().ListColectionToString(((List<Object>) (List<?>) indicadorValores))
				+ "], \n\t\t\tindicadorMetas=["
				+ new Util().ListColectionToString(((List<Object>) (List<?>) indicadorMetas))
				+ "], \n\t\t\tdadoValores=[" + new Util().ListColectionToString((List<Object>) (List<?>) dadoValores)
				+ "], \n\t\t\tdadoMetas=[" + new Util().ListColectionToString(((List<Object>) (List<?>) dadoMetas))
				+ "]]";
	}

}
