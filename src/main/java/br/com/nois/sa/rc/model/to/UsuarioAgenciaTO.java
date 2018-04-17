package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.UsuarioAgencia;
import br.com.nois.sa.rc.model.json.UsuarioAgenciaJSON;
import br.com.nois.sa.rc.model.json.UsuarioMunicipioJSON;

@Document(collection = "UsuarioAgencia")
public class UsuarioAgenciaTO extends UsuarioAgencia {
	private List<UsuarioMunicipioTO> usuarioMunicipios;

	public UsuarioAgenciaTO() {
		super();
	}

	public UsuarioAgenciaTO(String nome) {
		super(nome);
	}

	public UsuarioAgenciaTO(UsuarioAgenciaJSON json) {
		super();
		super.setId(json.getId());
		super.setAgenciaId(json.getAgenciaId());
		super.setEditar(json.isEditar());
		super.setNome(json.getNome());
		super.setVer(json.isVer());
		if (json.getUsuarioMunicipios() != null) {
			this.usuarioMunicipios = new ArrayList<UsuarioMunicipioTO>();
			for (UsuarioMunicipioJSON usuarioMunicipioJSON : json.getUsuarioMunicipios()) {
				this.usuarioMunicipios.add(new UsuarioMunicipioTO(usuarioMunicipioJSON));
			}
		}
	}

	public List<UsuarioMunicipioTO> getUsuarioMunicipios() {
		return usuarioMunicipios;
	}

	public void setUsuarioMunicipios(List<UsuarioMunicipioTO> usuarioMunicipio) {
		this.usuarioMunicipios = usuarioMunicipio;
	}

	@Override
	public String toString() {
		String sUsuarioMunicipio = "";
		if (this.usuarioMunicipios != null) {
			for (UsuarioMunicipioTO usuarioMunicipioTO : usuarioMunicipios) {
				sUsuarioMunicipio += usuarioMunicipioTO.toString() + ", ";
			}
		}
		return "UsuarioAgencia [id=" + super.getId() + ", agenciaId=" + super.getAgenciaId() + ", nome="
				+ super.getNome() + ", ver=" + super.isVer() + ", editar=" + super.isEditar() + ", UsuarioMunicipo=["
				+ sUsuarioMunicipio + "]]";
	}
}
