package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;

import br.com.nois.sa.rc.model.Perfil;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;
import br.com.nois.sa.rc.model.to.PerfilTO;

public class PerfilJSON extends Perfil {
	List<String> funcionalidades;

	public PerfilJSON(String nome) {
		super(nome);
	}

	public PerfilJSON() {
		super();
	}

	public PerfilJSON(PerfilTO to) {
		super();
		super.setId(to.getId());
		super.setNome(to.getNome());
		if (this.funcionalidades == null) {
			this.funcionalidades = new ArrayList<String>();
		}

		for (FuncionalidadeTO funcionalidadeTO : to.getFuncionalidades()) {
			this.funcionalidades.add(funcionalidadeTO.getId());
		}
	}

	public void setFuncionalidades(List<String> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<String> getFuncionalidades() {
		return funcionalidades;
	}

	@Override
	public String toString() {
		String sFuncionalidade = "";
		for (String funcionalidade : funcionalidades) {
			sFuncionalidade += funcionalidade.toString() + ", ";
		}
		return "PerfilJSON [id=" + super.getId() + ", funcionalidades=[" + sFuncionalidade + "], nome="
				+ super.getNome() + "]";
	}
}
