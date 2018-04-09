package br.com.nois.sa.rc.model.json;

import java.util.ArrayList;
import java.util.List;

import br.com.nois.sa.rc.model.Funcionalidade;
import br.com.nois.sa.rc.model.Perfil;
import br.com.nois.sa.rc.model.to.FuncionalidadeTO;
import br.com.nois.sa.rc.model.to.PerfilTO;

public class PerfilJSON extends Perfil {
	List<FuncionalidadeJSON> funcionalidades;

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
			this.funcionalidades = new ArrayList<FuncionalidadeJSON>();
		}

		for (FuncionalidadeTO funcionalidadeTO : to.getFuncionalidades()) {
			this.funcionalidades
					.add(new FuncionalidadeJSON(funcionalidadeTO.getId(), funcionalidadeTO.getNome(), true));
		}
	}

	public void sincFuncionalidades(List<FuncionalidadeTO> funcionalidades) {
		for (FuncionalidadeTO funcionalidade : funcionalidades) {
			boolean tem = false;
			if (!this.funcionalidades.isEmpty()) {
				for (FuncionalidadeJSON funcionalidadeJSON : this.funcionalidades) {
					if (funcionalidade.getNome() == funcionalidadeJSON.getNome()) {
						funcionalidadeJSON.setSelecionada(true);
						tem = true;
						continue;
					}
				}
			}
			if (tem == false) {

				this.funcionalidades
						.add(new FuncionalidadeJSON(funcionalidade.getId(), funcionalidade.getNome(), false));
			}
		}

	}

	public void setFuncionalidades(List<FuncionalidadeJSON> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<FuncionalidadeJSON> getFuncionalidades() {
		return funcionalidades;
	}

	@Override
	public String toString() {
		String sFuncionalidade = "";
		for (Funcionalidade funcionalidade : funcionalidades) {
			sFuncionalidade += funcionalidade.toString() + ", ";
		}
		return "PerfilJSON [id=" + super.getId() + ", funcionalidades=[" + sFuncionalidade + "], nome="
				+ super.getNome() + "]";
	}
}
