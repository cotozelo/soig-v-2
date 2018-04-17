package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;

import br.com.nois.sa.rc.model.UsuarioPrestadora;
import br.com.nois.sa.rc.model.to.UsuarioDadoTO;
import br.com.nois.sa.rc.model.to.UsuarioIndicadorTO;
import br.com.nois.sa.rc.model.to.UsuarioPrestadoraTO;

public class UsuarioPrestadoraJSON extends UsuarioPrestadora {

	List<UsuarioDadoJSON> usuarioDados;
	List<UsuarioIndicadorJSON> usuarioIndicadores;

	public UsuarioPrestadoraJSON() {
		super();
	}

	public UsuarioPrestadoraJSON(String nome) {
		super(nome);
	}

	public UsuarioPrestadoraJSON(UsuarioPrestadoraTO to) {
		super();
		super.setId(to.getId());
		super.setEditar(to.isEditar());
		super.setNome(to.getNome());
		super.setPrestadoraId(to.getPrestadoraId());
		super.setVer(to.isVer());
		if (to.getUsuarioDados() != null) {
			this.usuarioDados = new ArrayList<UsuarioDadoJSON>();
			for (UsuarioDadoTO usuarioDadoTO : to.getUsuarioDados()) {
				this.usuarioDados.add(new UsuarioDadoJSON(usuarioDadoTO));
			}
		}
		if (to.getUsuarioIndicadores() != null) {
			this.usuarioIndicadores = new ArrayList<UsuarioIndicadorJSON>();
			for (UsuarioIndicadorTO usuarioIndicadorTO : to.getUsuarioIndicadores()) {
				this.usuarioIndicadores.add(new UsuarioIndicadorJSON(usuarioIndicadorTO));
			}
		}
	}

	public List<UsuarioDadoJSON> getUsuarioDados() {
		return usuarioDados;
	}

	public void setUsuarioDados(List<UsuarioDadoJSON> usuarioDados) {
		this.usuarioDados = usuarioDados;
	}

	public List<UsuarioIndicadorJSON> getUsuarioIndicadores() {
		return usuarioIndicadores;
	}

	public void setUsuarioIndicadores(List<UsuarioIndicadorJSON> usuarioIndicadores) {
		this.usuarioIndicadores = usuarioIndicadores;
	}

	@Override
	public String toString() {
		String sUsuarioDados = "";
		if (this.usuarioDados != null) {
			for (UsuarioDadoJSON usuarioDadoJSON : usuarioDados) {
				sUsuarioDados += usuarioDadoJSON.toString() + ", ";
			}
		}
		String sUsuarioIndicadores = "";
		if (this.usuarioIndicadores != null) {
			for (UsuarioIndicadorJSON usuarioIndicadorJSON : usuarioIndicadores) {
				sUsuarioIndicadores += usuarioIndicadorJSON.toString() + ", ";
			}
		}
		return "UsuarioPrestadoraJSON [id=" + super.getId() + ", prestadoraId=" + super.getPrestadoraId() + ", nome="
				+ super.getNome() + ", ver=" + super.isVer() + ", editar=" + super.isEditar() + ", usuarioDado=["
				+ sUsuarioDados + "] , usuarioIndicador=[" + sUsuarioIndicadores + "]]";
	}
}
