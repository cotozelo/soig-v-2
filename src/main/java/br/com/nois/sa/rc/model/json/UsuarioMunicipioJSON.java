package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;

import br.com.nois.sa.rc.model.UsuarioMunicipio;
import br.com.nois.sa.rc.model.to.UsuarioMunicipioTO;
import br.com.nois.sa.rc.model.to.UsuarioPrestadoraTO;

public class UsuarioMunicipioJSON extends UsuarioMunicipio {
	List<UsuarioPrestadoraJSON> usuarioPrestadoras;

	public UsuarioMunicipioJSON() {
		super();
	}

	public UsuarioMunicipioJSON(String nome) {
		super(nome);
	}

	public UsuarioMunicipioJSON(UsuarioMunicipioTO to) {
		super();
		super.setId(to.getId());
		super.setEditar(to.isEditar());
		super.setMunicipioId(to.getMunicipioId());
		super.setNome(to.getNome());
		super.setVer(to.isVer());
		if (to.getUsuarioPrestadoras() != null) {
			this.usuarioPrestadoras = new ArrayList<UsuarioPrestadoraJSON>();
			for (UsuarioPrestadoraTO usuarioPrestadoraTO : to.getUsuarioPrestadoras()) {
				this.usuarioPrestadoras.add(new UsuarioPrestadoraJSON(usuarioPrestadoraTO));
			}
		}
	}

	public List<UsuarioPrestadoraJSON> getUsuarioPrestadoras() {
		return usuarioPrestadoras;
	}

	public void setUsuarioPrestadoras(List<UsuarioPrestadoraJSON> usuarioPrestadora) {
		this.usuarioPrestadoras = usuarioPrestadora;
	}

	@Override
	public String toString() {
		String sUsuarioPrestadoraJSON = "";
		if (this.usuarioPrestadoras != null) {
			for (UsuarioPrestadoraJSON usuarioPrestadoraJSON : usuarioPrestadoras) {
				sUsuarioPrestadoraJSON += usuarioPrestadoraJSON.toString() + ", ";
			}
		}
		return "UsuarioMunicipio [id=" + super.getId() + ", municipioId=" + super.getMunicipioId() + ", nome="
				+ super.getNome() + ", ver=" + super.isVer() + ", editar=" + super.isEditar() + ", UsuarioPrestadora=["
				+ sUsuarioPrestadoraJSON + "]]";
	}
}
