package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class Prestadora {

	@Id
	private String id;
	private String nome;
	private String codigo;
	private String sigla;
	private String abrangenciaId;
	private String abrangenciaNome;
	private String natureza;
	private String servicoId;
	private String servicoNome;
	private String contatoTelefone;
	private String contatoNome;
	private String contatoEmail;
	// private List<Ano> anos;

	public Prestadora() {
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

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getAbrangenciaId() {
		return abrangenciaId;
	}

	public void setAbrangenciaId(String id) {
		this.abrangenciaId = id;
	}

	public String getAbrangenciaNome() {
		return abrangenciaNome;
	}

	public void setAbrangenciaNome(String nome) {
		this.abrangenciaNome = nome;
	}

	public String getServicoId() {
		return servicoId;
	}

	public void setServicoId(String id) {
		this.servicoId = id;
	}

	public String getServicoNome() {
		return servicoNome;
	}

	public void setServicoNome(String nome) {
		this.servicoNome = nome;
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
}
