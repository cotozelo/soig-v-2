package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;

import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;
import br.com.nois.sa.rc.model.to.UsuarioAgenciaTO;
import br.com.nois.sa.rc.model.to.UsuarioTO;

public class UsuarioJSON extends Usuario {

	List<UsuarioAgenciaJSON> usuarioAgencias;
	List<UsuarioFuncionalidadeJSON> usuarioFuncionalidades;

	public UsuarioJSON() {
		super();
	}

	public UsuarioJSON(String nome) {
		super(nome);
	}

	public UsuarioJSON(UsuarioTO to) {
		super(to.getNome());
		super.setId(to.getId());
		super.setNome(to.getNome());
		super.setNomeDeUsuario(to.getNomeDeUsuario());
		super.setTelefone(to.getTelefone());
		super.setEmail(to.getEmail());
		super.setAdmin(to.isAdmin());
		super.setAtivo(to.isAtivo());
		super.setSenha(to.getSenha());
		super.setPerfilId(to.getPerfilId());
		if (to.getUsuarioAgencias() != null) {
			this.usuarioAgencias = new ArrayList<UsuarioAgenciaJSON>();
			for (UsuarioAgenciaTO usuarioAgenciaTO : to.getUsuarioAgencias()) {
				this.usuarioAgencias.add(new UsuarioAgenciaJSON(usuarioAgenciaTO));
			}
		}
	}

	public List<UsuarioAgenciaJSON> getUsuarioAgencias() {
		return usuarioAgencias;
	}

	public void setUsuarioAgencias(List<UsuarioAgenciaJSON> usuarioAgencia) {
		this.usuarioAgencias = usuarioAgencia;
	}

	public List<UsuarioFuncionalidadeJSON> getUsuarioFuncionalidades() {
		return usuarioFuncionalidades;
	}

	public void setUsuarioFuncionalidades(List<FuncionalidadeTO> list) {
		if (this.usuarioFuncionalidades == null) {
			this.usuarioFuncionalidades = new ArrayList<UsuarioFuncionalidadeJSON>();
		}
		for (FuncionalidadeTO funcionalidadeTO : list) {
			this.usuarioFuncionalidades.add(new UsuarioFuncionalidadeJSON(funcionalidadeTO));
		}
	}

	@Override
	public String toString() {
		String sUsuarioAgencia = "";
		if (this.usuarioAgencias != null) {
			for (UsuarioAgenciaJSON usuarioAgenciaJSON : usuarioAgencias) {
				sUsuarioAgencia += usuarioAgenciaJSON.toString() + ", ";
			}
		}
		String sFuncionalidade = "";
		if (this.usuarioFuncionalidades != null) {
			for (UsuarioFuncionalidadeJSON funcionalidade : this.usuarioFuncionalidades) {
				sFuncionalidade += funcionalidade.toString() + ", ";
			}
		}
		return "Usuario [id=" + super.getId() + ", nome=" + super.getNome() + ", nomeDeUsuario="
				+ super.getNomeDeUsuario() + ", telefone= " + super.getTelefone() + ", perfilId=" + super.getPerfilId()
				+ ", email=" + super.getEmail() + ", admin=" + super.isAdmin() + ", ativo=" + super.isAtivo()
				+ ", UsuarioAgencia=[" + sUsuarioAgencia + "], funcionalidades=[" + sFuncionalidade + "]";
	}
}
