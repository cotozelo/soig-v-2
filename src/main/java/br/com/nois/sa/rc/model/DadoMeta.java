package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DadoMeta")
public class DadoMeta {

	@Id
	private String id;
	private String dado_id;
	private String nome;
	private OperadorRelacional operadorRelacional;
	private double valor;
	private int versaoGlobal;
	private String pai_id;

	public DadoMeta() {
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

	public String getDado_id() {
		return dado_id;
	}

	public void setDado_id(String dado_id) {
		this.dado_id = dado_id;
	}

	public String getPai_id() {
		return pai_id;
	}

	public void setPai_id(String pai_id) {
		this.pai_id = pai_id;
	}

	@Override
	public String toString() {
		return "DadoMeta [id=" + id + ", dado_id=" + dado_id + ", nome=" + nome + ", operadorRelacional="
				+ operadorRelacional + ", valor=" + valor + ", versaoGlobal=" + versaoGlobal + ", pai_id=" + pai_id
				+ "]";
	}
}
