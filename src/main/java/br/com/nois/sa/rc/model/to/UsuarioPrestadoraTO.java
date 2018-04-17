package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.UsuarioPrestadora;
import br.com.nois.sa.rc.model.json.UsuarioDadoJSON;
import br.com.nois.sa.rc.model.json.UsuarioIndicadorJSON;
import br.com.nois.sa.rc.model.json.UsuarioPrestadoraJSON;

@Document(collection = "UsuarioPrestadora")
public class UsuarioPrestadoraTO extends UsuarioPrestadora {

	List<UsuarioDadoTO> usuarioDados;
	List<UsuarioIndicadorTO> usuarioIndicadores;

	public UsuarioPrestadoraTO() {
		super();
	}

	public UsuarioPrestadoraTO(String nome) {
		super(nome);
	}

	public UsuarioPrestadoraTO(UsuarioPrestadoraJSON json) {
		super();
		super.setId(json.getId());
		super.setEditar(json.isEditar());
		super.setNome(json.getNome());
		super.setPrestadoraId(json.getPrestadoraId());
		super.setVer(json.isVer());
		if (json.getUsuarioDados() != null) {
			this.usuarioDados = new ArrayList<UsuarioDadoTO>();
			for (UsuarioDadoJSON usuarioDadoJSON : json.getUsuarioDados()) {
				this.usuarioDados.add(new UsuarioDadoTO(usuarioDadoJSON));
			}
		}
		if (json.getUsuarioIndicadores() != null) {
			this.usuarioIndicadores = new ArrayList<UsuarioIndicadorTO>();
			for (UsuarioIndicadorJSON usuarioIndicadorJSON : json.getUsuarioIndicadores()) {
				this.usuarioIndicadores.add(new UsuarioIndicadorTO(usuarioIndicadorJSON));
			}
		}
	}

	public List<UsuarioDadoTO> getUsuarioDados() {
		return usuarioDados;
	}

	public void setUsuarioDados(List<UsuarioDadoTO> usuarioDados) {
		this.usuarioDados = usuarioDados;
	}

	public List<UsuarioIndicadorTO> getUsuarioIndicadores() {
		return usuarioIndicadores;
	}

	public void setUsuarioIndicadores(List<UsuarioIndicadorTO> usuarioIndicadores) {
		this.usuarioIndicadores = usuarioIndicadores;
	}

	@Override
	public String toString() {
		String sUsuarioDados = "";
		if (this.usuarioDados != null) {
			for (UsuarioDadoTO usuarioDadoTO : usuarioDados) {
				sUsuarioDados += usuarioDadoTO.toString() + ", ";
			}
		}
		String sUsuarioIndicadores = "";
		if (this.usuarioIndicadores != null) {
			for (UsuarioIndicadorTO usuarioIndicadorTO : usuarioIndicadores) {
				sUsuarioIndicadores += usuarioIndicadorTO.toString() + ", ";
			}
		}
		return "UsuarioPrestadoraTO [id=" + super.getId() + ", prestadoraId=" + super.getPrestadoraId() + ", nome="
				+ super.getNome() + ", ver=" + super.isVer() + ", editar=" + super.isEditar() + ", usuarioDado=["
				+ sUsuarioDados + "] , usuarioIndicador=[" + sUsuarioIndicadores + "]]";
	}
}
