package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Municipio {

	@Id
	private String id;
	private String agenciaId;
	private String nome;
	private String codigo;
	private String cidade;
	private String estado;
	private String contatoTelefone;
	private String contatoNome;
	private String contatoEmail;
	private Boolean ativa = false;
	// private List<Prestadora> prestadoras;

	public Municipio() {
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

	public Boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public String getAgenciaId() {
		return agenciaId;
	}

	public void setAgenciaId(String agenciaId) {
		this.agenciaId = agenciaId;
	}

	public void update(Municipio municipio) {
		this.nome = municipio.getNome();
		this.codigo = municipio.getCodigo();
		this.cidade = municipio.getCidade();
		this.estado = municipio.getEstado();
		this.contatoTelefone = municipio.getContatoTelefone();
		this.contatoNome = municipio.getContatoNome();
		this.contatoEmail = municipio.getContatoEmail();
		this.ativa = municipio.isAtiva();
		this.agenciaId = municipio.getAgenciaId();
	}
}
