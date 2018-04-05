package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "agencia")
public class Agencia {
	@Id
	private String id;
	private String nome;
	private String contatoNome;
	private String contatoTelefone;
	private String contatoEmail;
	private String razaoSocial;
	private String endereco;
	private String municipio;
	private String estado;
	private AmplitudeAtuacao atuacao;
	private boolean ativo = false;
	private String error = null;

	public Agencia() {
		super();
	}

	public Agencia(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
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

	public String getContatoNome() {
		return contatoNome;
	}

	public void setContatoNome(String contatoNome) {
		this.contatoNome = contatoNome;
	}

	public String getContatoTelefone() {
		return contatoTelefone;
	}

	public void setContatoTelefone(String contatoTelefone) {
		this.contatoTelefone = contatoTelefone;
	}

	public String getContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = contatoEmail;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public AmplitudeAtuacao getAtuacao() {
		return atuacao;
	}

	public void setAtuacao(AmplitudeAtuacao atuacao) {
		this.atuacao = atuacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void update(Agencia agencia) {
		this.nome = agencia.getNome();
		this.contatoNome = agencia.getContatoNome();
		this.contatoTelefone = agencia.getContatoTelefone();
		this.contatoEmail = agencia.getContatoEmail();
		this.razaoSocial = agencia.getRazaoSocial();
		this.endereco = agencia.getEndereco();
		this.municipio = agencia.getMunicipio();
		this.estado = agencia.getEstado();
		this.atuacao = agencia.getAtuacao();
		this.ativo = agencia.isAtivo();
	}

	@Override
	public String toString() {
		return "Agencia [id=" + id + ", nome=" + nome + ", contatoNome=" + contatoNome + ", contatoTelefone="
				+ contatoTelefone + ", contatoEmail=" + contatoEmail + ", razaoSocial=" + razaoSocial + ", endereco="
				+ endereco + ", municipio=" + municipio + ", estado=" + estado + ", atuacao=" + atuacao + ", ativo="
				+ ativo + " ]";
	}

}
