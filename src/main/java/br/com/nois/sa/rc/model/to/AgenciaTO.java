package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Agencia;
import br.com.nois.sa.rc.model.json.AgenciaJSON;

@Document(collection = "agencia")
public class AgenciaTO extends Agencia {
	public AgenciaTO() {
		super();
	}

	public AgenciaTO(AgenciaJSON json) {
		super();
		super.setId(json.getId());
		super.setAtivo(json.isAtivo());
		super.setAtuacao(json.getAtuacao());
		super.setContatoEmail(json.getContatoEmail());
		super.setContatoNome(json.getContatoNome());
		super.setContatoTelefone(json.getContatoTelefone());
		super.setEndereco(json.getEndereco());
		super.setEstado(json.getEstado());
		super.setMunicipio(json.getMunicipio());
		super.setNome(json.getNome());
		super.setRazaoSocial(json.getRazaoSocial());
	}
}
