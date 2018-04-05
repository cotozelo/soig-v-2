package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "IndicadorMeta")
public class IndicadorMeta {

	@Id
	private String id;
	private String indicador_id;
	private String nome;
	private OperadorRelacional operadorRelacional;
	private double valor;
	private int versaoGlobal;
	private String pai_id;

	public IndicadorMeta() {
		super();
	}

	public IndicadorMeta(String indicador_id, String nome) {
		super();
		this.id = ObjectId.get().toString();
		this.indicador_id = indicador_id;
		this.nome = nome;
	}

	public IndicadorMeta(String indicador_id, String nome, OperadorRelacional operadorRelacional, double valor) {
		super();
		this.id = ObjectId.get().toString();
		this.indicador_id = indicador_id;
		this.nome = nome;
		this.operadorRelacional = operadorRelacional;
		this.valor = valor;
	}

	public IndicadorMeta(String id, String indicador_id, String nome, OperadorRelacional operadorRelacional,
			double valor, int versaoGlobal) {
		super();
		this.id = id;
		this.indicador_id = indicador_id;
		this.nome = nome;
		this.operadorRelacional = operadorRelacional;
		this.valor = valor;
		this.versaoGlobal = versaoGlobal;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getIndicador_id() {
		return indicador_id;
	}

	public void setIndicador_id(String indicador_id) {
		this.indicador_id = indicador_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public OperadorRelacional getOperadorRelacional() {
		return operadorRelacional;
	}

	public void setOperadorRelacional(OperadorRelacional operadorRelacional) {
		this.operadorRelacional = operadorRelacional;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getVersaoGlobal() {
		return versaoGlobal;
	}

	public void setVersaoGlobal(int versaoGlobal) {
		this.versaoGlobal = versaoGlobal;
	}

	public String getPai_id() {
		return pai_id;
	}

	public void setPai_id(String pai_id) {
		this.pai_id = pai_id;
	}

	@Override
	public String toString() {
		return "IndicadorMeta [id=" + id + ", indicador_id=" + indicador_id + ", nome=" + nome + ", operadorRelacional="
				+ operadorRelacional + ", valor=" + valor + ", versaoGlobal=" + versaoGlobal + ", pai_id=" + pai_id
				+ "]";
	}

}
