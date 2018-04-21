package br.com.nois.sa.rc.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Funcionalidade;
import br.com.nois.sa.rc.model.Perfil;
import br.com.nois.sa.rc.model.json.PerfilJSON;

@Document(collection = "perfil")
public class PerfilTO extends Perfil {

	List<FuncionalidadeTO> funcionalidades;

	public PerfilTO(String nome) {
		super(nome);
	}

	public PerfilTO() {
		super();
	}

	public PerfilTO(PerfilJSON json) {
		super();
		super.setId(json.getId());
		super.setNome(json.getNome());
		if (this.funcionalidades == null) {
			this.funcionalidades = new ArrayList<FuncionalidadeTO>();
		}

		for (String funcionalidadeId : json.getFuncionalidades()) {
			this.funcionalidades.add(new FuncionalidadeTO(funcionalidadeId));
		}
	}

	public List<FuncionalidadeTO> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<FuncionalidadeTO> funcionalidades) {
		this.funcionalidades = funcionalidades;
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
