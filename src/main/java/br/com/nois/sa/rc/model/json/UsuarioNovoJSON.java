package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.model.to.UsuarioNovoTO;

public class UsuarioNovoJSON extends Usuario {
	private String senha;

	public UsuarioNovoJSON() {
		super();
	}

	public UsuarioNovoJSON(String nome) {
		super(nome);
	}

	public UsuarioNovoJSON(UsuarioNovoTO to) {
		super(to.getNome());
		super.setId(to.getId());
		super.setNome(to.getNome());
		super.setNomeDeUsuario(to.getNomeDeUsuario());
		super.setTelefone(to.getTelefone());
		super.setEmail(to.getEmail());
		super.setAdmin(to.isAdmin());
		super.setAtivo(to.isAtivo());
		super.setPerfilId(to.getPerfilId());
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
