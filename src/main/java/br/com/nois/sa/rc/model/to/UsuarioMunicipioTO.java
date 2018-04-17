package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.UsuarioMunicipio;
import br.com.nois.sa.rc.model.json.UsuarioMunicipioJSON;
import br.com.nois.sa.rc.model.json.UsuarioPrestadoraJSON;

@Document(collection = "UsuarioMunicipio")
public class UsuarioMunicipioTO extends UsuarioMunicipio {
	List<UsuarioPrestadoraTO> usuarioPrestadoras;

	public UsuarioMunicipioTO() {
		super();
	}

	public UsuarioMunicipioTO(String nome) {
		super(nome);
	}

	public UsuarioMunicipioTO(UsuarioMunicipioJSON json) {
		super();
		super.setId(json.getId());
		super.setEditar(json.isEditar());
		super.setMunicipioId(json.getMunicipioId());
		super.setNome(json.getNome());
		super.setVer(json.isVer());
		if (json.getUsuarioPrestadoras() != null) {
			this.usuarioPrestadoras = new ArrayList<UsuarioPrestadoraTO>();
			for (UsuarioPrestadoraJSON usuarioPrestadoraJSON : json.getUsuarioPrestadoras()) {
				this.usuarioPrestadoras.add(new UsuarioPrestadoraTO(usuarioPrestadoraJSON));
			}
		}
	}

	public List<UsuarioPrestadoraTO> getUsuarioPrestadoras() {
		return usuarioPrestadoras;
	}

	public void setUsuarioPrestadoras(List<UsuarioPrestadoraTO> usuarioPrestadora) {
		this.usuarioPrestadoras = usuarioPrestadora;
	}

	@Override
	public String toString() {
		String sUsuarioPrestadoraTO = "";
		if (this.usuarioPrestadoras != null) {
			for (UsuarioPrestadoraTO usuarioPrestadoraTO : usuarioPrestadoras) {
				sUsuarioPrestadoraTO += usuarioPrestadoraTO.toString() + ", ";
			}
		}
		return "UsuarioMunicipio [id=" + super.getId() + ", municipioId=" + super.getMunicipioId() + ", nome="
				+ super.getNome() + ", ver=" + super.isVer() + ", editar=" + super.isEditar() + ", UsuarioPrestadora=["
				+ sUsuarioPrestadoraTO + "]]";
	}

}
