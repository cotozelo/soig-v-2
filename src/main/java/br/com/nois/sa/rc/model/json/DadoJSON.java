package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Dado;
import br.com.nois.sa.rc.model.to.DadoTO;

public class DadoJSON extends Dado {

	public DadoJSON() {
		super();
	}

	public DadoJSON(DadoTO to) {
		super();
		super.setAtivo(to.isAtivo());
		super.setDescricao(to.getDescricao());
		super.setGrafico(to.getGrafico());
		super.setGrupoId(to.getGrupoId());
		super.setGrupoNome(to.getGrupoNome());
		super.setEixoId(to.getEixoId());
		super.setEixoNome(to.getEixoNome());
		super.setObservacao(to.getObservacao());
		super.setSigla(to.getSigla());
		super.setUnidadeId(to.getUnidadeId());
		super.setUnidadeNome(to.getUnidadeNome());
		super.setId(to.getId());
		super.setDecimal(to.isDecimal());
		super.setTipoDado(to.getTipoDado());
	}
}
