package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Agencia;
import br.com.nois.sa.rc.model.to.AgenciaTO;

public class AgenciaJSON extends Agencia {

	public AgenciaJSON() {
		super();
	}

	public AgenciaJSON(AgenciaTO to) {
		super();
		super.setId(to.getId());
		super.setAtivo(to.isAtivo());
		super.setAtuacao(to.getAtuacao());
		super.setContatoEmail(to.getContatoEmail());
		super.setContatoNome(to.getContatoNome());
		super.setContatoTelefone(to.getContatoTelefone());
		super.setEndereco(to.getEndereco());
		super.setEstado(to.getEstado());
		super.setMunicipio(to.getMunicipio());
		super.setNome(to.getNome());
		super.setRazaoSocial(to.getRazaoSocial());
	}
}
