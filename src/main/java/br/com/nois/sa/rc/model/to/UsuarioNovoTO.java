package br.com.nois.sa.rc.model.to;

import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.model.json.UsuarioNovoJSON;

public class UsuarioNovoTO extends Usuario {
	private String senha;

	public UsuarioNovoTO() {
		super();
	}

	public UsuarioNovoTO(String nome) {
		super(nome);
	}

	public UsuarioNovoTO(UsuarioNovoJSON json) {
		super(json.getNome());
		super.setId(json.getId());
		super.setNome(json.getNome());
		super.setNomeDeUsuario(json.getNomeDeUsuario());
		super.setTelefone(json.getTelefone());
		super.setEmail(json.getEmail());
		super.setAdmin(json.isAdmin());
		super.setAtivo(json.isAtivo());
		super.setPerfilId(json.getPerfilId());
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + super.getId() + ", nome=" + super.getNome() + ", nomeDeUsuario="
				+ super.getNomeDeUsuario() + ", telefone= " + super.getTelefone() + ", perfilId=" + super.getPerfilId()
				+ ", email=" + super.getEmail() + ", admin=" + super.isAdmin() + ", ativo=" + super.isAtivo() + "]";
	}
}
