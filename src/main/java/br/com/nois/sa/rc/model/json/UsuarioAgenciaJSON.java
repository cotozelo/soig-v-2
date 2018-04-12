package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;

import br.com.nois.sa.rc.model.UsuarioAgencia;
import br.com.nois.sa.rc.model.to.UsuarioAgenciaTO;
import br.com.nois.sa.rc.model.to.UsuarioMunicipioTO;

public class UsuarioAgenciaJSON extends UsuarioAgencia {

	private List<UsuarioMunicipioJSON> usuarioMunicipios;

	public UsuarioAgenciaJSON() {
		super();
	}

	public UsuarioAgenciaJSON(String nome) {
		super(nome);
	}

	public UsuarioAgenciaJSON(UsuarioAgenciaTO to) {
		super();
		super.setId(to.getId());
		super.setAgenciaId(to.getAgenciaId());
		super.setEditar(to.isEditar());
		super.setNome(to.getNome());
		super.setVer(to.isVer());
		if (to.getUsuarioMunicipios() != null) {
			this.usuarioMunicipios = new ArrayList<UsuarioMunicipioJSON>();
			for (UsuarioMunicipioTO usuarioMunicipioTO : to.getUsuarioMunicipios()) {
				this.usuarioMunicipios.add(new UsuarioMunicipioJSON(usuarioMunicipioTO));
			}
		}
	}

	public List<UsuarioMunicipioJSON> getUsuarioMunicipios() {
		return usuarioMunicipios;
	}

	public void setUsuarioMunicipios(List<UsuarioMunicipioJSON> usuarioMunicipio) {
		this.usuarioMunicipios = usuarioMunicipio;
	}

	@Override
	public String toString() {
		String sUsuarioMunicipio = "";
		if (this.usuarioMunicipios != null) {
			for (UsuarioMunicipioJSON usuarioMunicipioJSON : usuarioMunicipios) {
				sUsuarioMunicipio += usuarioMunicipioJSON.toString() + ", ";
			}
		}
		return "UsuarioAgencia [id=" + super.getId() + ", agenciaId=" + super.getAgenciaId() + ", nome="
				+ super.getNome() + ", ver=" + super.isVer() + ", editar=" + super.isEditar() + ", UsuarioMunicipo=["
				+ sUsuarioMunicipio + "]]";
	}
}
