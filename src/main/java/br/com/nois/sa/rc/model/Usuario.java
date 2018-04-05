package br.com.nois.sa.rc.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.util.Util;

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
	private List<UsuarioEntidade> usuarioEntidades;
	private List<UsuarioIndicador> usuarioIndicadores;
	private List<UsuarioDado> usuarioDados;
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

	public List<UsuarioEntidade> getUsuarioEntidades() {
		return usuarioEntidades;
	}

	public UsuarioEntidade getUsuarioEntidade(String idUEntidade) {
		for (UsuarioEntidade usuarioEntidade : usuarioEntidades) {
			if (usuarioEntidade.getId().equals(idUEntidade)) {
				return usuarioEntidade;
			}
		}
		return null;
	}

	public void removeUsuarioEntidade(UsuarioEntidade usuarioEntidade) {
		this.usuarioEntidades.remove(usuarioEntidade);
	}

	public void setUsuarioEntidades(List<UsuarioEntidade> usuarioEntidades) {
		this.usuarioEntidades = usuarioEntidades;
	}

	public List<UsuarioIndicador> getUsuarioIndicadores() {
		return usuarioIndicadores;
	}

	public UsuarioIndicador getUsuarioIndicador(String idUIndicador) {
		for (UsuarioIndicador usuarioIndicador : this.usuarioIndicadores) {
			if (usuarioIndicador.getId().equals(idUIndicador)) {
				return usuarioIndicador;
			}
		}
		return null;
	}

	public void removeUsuarioIndicador(UsuarioIndicador usuarioIndicador) {
		this.usuarioIndicadores.remove(usuarioIndicador);
	}

	public void setUsuarioIndicadores(List<UsuarioIndicador> usuarioIndicadores) {
		this.usuarioIndicadores = usuarioIndicadores;
	}

	public List<UsuarioDado> getUsuarioDados() {
		return usuarioDados;
	}

	public UsuarioDado getUsuarioDado(String idUDado) {
		for (UsuarioDado usuarioDado : this.usuarioDados) {
			if (usuarioDado.getId().equals(idUDado)) {
				return usuarioDado;
			}
		}
		return null;
	}

	public void removeUsuarioDado(UsuarioDado usarioDado) {
		this.usuarioDados.remove(usarioDado);
	}

	public void setUsuarioDados(List<UsuarioDado> usuarioDados) {
		for (UsuarioDado usuarioDado : usuarioDados) {
			this.setUsuarioDado(usuarioDado);
		}
	}

	public UsuarioDado setUsuarioDado(UsuarioDado usuarioDado) {
		if (usuarioDado.getId() == null) {
			usuarioDado.setId();
		}
		if (this.usuarioDados == null) {
			this.usuarioDados = new ArrayList<UsuarioDado>();
			this.usuarioDados.add(usuarioDado);
		} else {
			for (int ii = 0; ii < this.usuarioDados.size(); ii++) {
				if (this.usuarioDados.get(ii).getId() != null
						&& this.usuarioDados.get(ii).getId().equals(usuarioDado.getId())) {
					this.usuarioDados.get(ii).update(usuarioDado);
					return usuarioDado;
				}
			}
			this.usuarioDados.add(usuarioDado);
		}
		return usuarioDado;
	}

	public UsuarioIndicador setUsuarioIndicador(UsuarioIndicador usuarioIndicador) {
		if (usuarioIndicador.getId() == null) {
			usuarioIndicador.setId();
		}
		if (this.usuarioIndicadores == null) {
			this.usuarioIndicadores = new ArrayList<UsuarioIndicador>();
			this.usuarioIndicadores.add(usuarioIndicador);
		} else {
			for (int ii = 0; ii < this.usuarioIndicadores.size(); ii++) {
				if (this.usuarioIndicadores.get(ii).getId() != null
						&& this.usuarioIndicadores.get(ii).getId().equals(usuarioIndicador.getId())) {
					this.usuarioIndicadores.get(ii).update(usuarioIndicador);
					return usuarioIndicador;
				}
			}
			this.usuarioIndicadores.add(usuarioIndicador);
		}
		return usuarioIndicador;
	}

	public UsuarioEntidade setUsuarioEntidade(UsuarioEntidade usuarioEntidade) {
		if (usuarioEntidade.getId() == null) {
			usuarioEntidade.setId();
		}
		if (this.usuarioEntidades == null) {
			this.usuarioEntidades = new ArrayList<UsuarioEntidade>();
			this.usuarioEntidades.add(usuarioEntidade);
		} else {
			for (int ii = 0; ii < this.usuarioEntidades.size(); ii++) {
				if (this.usuarioEntidades.get(ii).getId() != null
						&& this.usuarioEntidades.get(ii).getId().equals(usuarioEntidade.getId())) {
					this.usuarioEntidades.get(ii).update(usuarioEntidade);
					return usuarioEntidade;
				}
			}
			this.usuarioEntidades.add(usuarioEntidade);
		}
		return usuarioEntidade;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", nomeDeUsuario=" + nomeDeUsuario + ", perfil_id=" + perfil_id
				+ ", email=" + email + ", admin=" + admin + ", ativo=" + ativo + ", agencia_id=" + agencia_id
				+ ", senha=" + senha + ", usuarioEntidades=["
				+ new Util().ListColectionToString((List<Object>) (List<?>) this.usuarioEntidades)
				+ "], usuarioIndicadores=["
				+ new Util().ListColectionToString((List<Object>) (List<?>) this.usuarioIndicadores)
				+ "], usuarioDados=[" + new Util().ListColectionToString((List<Object>) (List<?>) this.usuarioDados)
				+ "]]";
	}

}
