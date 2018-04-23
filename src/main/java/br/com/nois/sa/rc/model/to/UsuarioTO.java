package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.model.json.UsuarioAgenciaJSON;
import br.com.nois.sa.rc.model.json.UsuarioJSON;

@Document(collection = "usuario")
public class UsuarioTO extends Usuario {
	List<UsuarioAgenciaTO> usuarioAgencias;

	public UsuarioTO() {
		super();
	}

	public UsuarioTO(String nome) {
		super(nome);
	}

	public UsuarioTO(UsuarioJSON json) {
		super(json.getNome());
		super.setId(json.getId());
		super.setNome(json.getNome());
		super.setNomeDeUsuario(json.getNomeDeUsuario());
		super.setTelefone(json.getTelefone());
		super.setEmail(json.getEmail());
		super.setAdmin(json.isAdmin());
		super.setAtivo(json.isAtivo());
		super.setSenha(json.getSenha());
		super.setPerfilId(json.getPerfilId());
		if (json.getUsuarioAgencias() != null) {
			this.usuarioAgencias = new ArrayList<UsuarioAgenciaTO>();
			for (UsuarioAgenciaJSON usuarioAgenciaJSON : json.getUsuarioAgencias()) {
				this.usuarioAgencias.add(new UsuarioAgenciaTO(usuarioAgenciaJSON));
			}
		}
	}

	public List<UsuarioAgenciaTO> getUsuarioAgencias() {
		return usuarioAgencias;
	}

	public void setUsuarioAgencias(List<UsuarioAgenciaTO> usuarioAgencia) {
		this.usuarioAgencias = usuarioAgencia;
	}

	public void update(UsuarioJSON json) {
		super.setId(json.getId());
		super.setNome(json.getNome());
		super.setNomeDeUsuario(json.getNomeDeUsuario());
		super.setTelefone(json.getTelefone());
		super.setEmail(json.getEmail());
		super.setAdmin(json.isAdmin());
		super.setAtivo(json.isAtivo());
		super.setPerfilId(json.getPerfilId());
	}

	@Override
	public String toString() {
		String sUsuarioAgencia = "";
		if (this.usuarioAgencias != null) {
			for (UsuarioAgenciaTO usuarioAgenciaTO : usuarioAgencias) {
				sUsuarioAgencia += usuarioAgenciaTO.toString() + ", ";
			}
		}
		return "Usuario [id=" + super.getId() + ", nome=" + super.getNome() + ", nomeDeUsuario="
				+ super.getNomeDeUsuario() + ", telefone= " + super.getTelefone() + ", perfilId=" + super.getPerfilId()
				+ ", email=" + super.getEmail() + ", admin=" + super.isAdmin() + ", ativo=" + super.isAtivo()
				+ ", UsuarioAgencia=[" + sUsuarioAgencia + "]]";
	}
}
