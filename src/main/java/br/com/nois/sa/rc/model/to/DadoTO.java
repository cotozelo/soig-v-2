package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Dado;
import br.com.nois.sa.rc.model.json.DadoJSON;

@Document(collection = "dado")
public class DadoTO extends Dado {

	public DadoTO() {
		super();
	}

	public DadoTO(DadoJSON json) {
		super();
		super.setAtivo(json.isAtivo());
		super.setDescricao(json.getDescricao());
		super.setGrafico(json.getGrafico());
		super.setGrupoId(json.getGrupoId());
		super.setGrupoNome(json.getGrupoNome());
		super.setEixoId(json.getEixoId());
		super.setEixoNome(json.getEixoNome());
		super.setObservacao(json.getObservacao());
		super.setSigla(json.getSigla());
		super.setUnidadeId(json.getUnidadeId());
		super.setUnidadeNome(json.getUnidadeNome());
		super.setId(json.getId());
		super.setDecimal(json.isDecimal());
		super.setTipoDado(json.getTipoDado());
	}

	public void update(DadoJSON json) {
		super.setAtivo(json.isAtivo());
		super.setDescricao(json.getDescricao());
		super.setGrafico(json.getGrafico());
		super.setGrupoId(json.getGrupoId());
		super.setGrupoNome(json.getGrupoNome());
		super.setEixoId(json.getEixoId());
		super.setEixoNome(json.getEixoNome());
		super.setObservacao(json.getObservacao());
		super.setSigla(json.getSigla());
		super.setUnidadeId(json.getUnidadeId());
		super.setUnidadeNome(json.getUnidadeNome());
		super.setId(json.getId());
		super.setDecimal(json.isDecimal());
		super.setTipoDado(json.getTipoDado());
	}

}
