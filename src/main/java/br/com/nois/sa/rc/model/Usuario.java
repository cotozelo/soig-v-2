package br.com.nois.sa.rc.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class Usuario {
	@Id
	private String id;
	private String nome;
	private String nomeDeUsuario;
	private String perfil_id;
	private String email;
	private boolean admin = false;
	private boolean ativo = false;
	private String agencia_id;
	private String senha;
	private List<UsuarioAgencia> usuarioAgencia;
	private List<UsuarioFuncionalidade> usuarioFuncionalidade;
	private String error = null;

	public Usuario() {
		super();
	}

	public Usuario(String error) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getPerfil_id() {
		return perfil_id;
	}

	public void setPerfil_id(String perfil_id) {
		this.perfil_id = perfil_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getAgencia_id() {
		return agencia_id;
	}

	public void setAgencia_id(String agencia_id) {
		this.agencia_id = agencia_id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", nomeDeUsuario=" + nomeDeUsuario + ", perfil_id=" + perfil_id
				+ ", email=" + email + ", admin=" + admin + ", ativo=" + ativo + ", agencia_id=" + agencia_id
				+ ", senha=" + senha + "]";
	}

	public List<UsuarioAgencia> getUsuarioAgencia() {
		return usuarioAgencia;
	}

	public void setUsuarioAgencia(List<UsuarioAgencia> usuarioAgencia) {
		this.usuarioAgencia = usuarioAgencia;
	}

	public List<UsuarioFuncionalidade> getUsuarioFuncionalidade() {
		return usuarioFuncionalidade;
	}

	public void setUsuarioFuncionalidade(List<UsuarioFuncionalidade> usuarioFuncionalidade) {
		this.usuarioFuncionalidade = usuarioFuncionalidade;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setError(String error) {

	}

	public String getRole() {
		return "ADMIN";
	}
}
