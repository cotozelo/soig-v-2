package br.com.nois.sa.rc.model.json;

import br.com.nois.sa.rc.model.Indicador;
import br.com.nois.sa.rc.model.to.IndicadorTO;

public class IndicadorJSON extends Indicador {

	public IndicadorJSON() {
		super();
	}

	public IndicadorJSON(IndicadorTO to) {
		super();
		super.setAtivo(to.isAtivo());
		super.setDescricao(to.getDescricao());
		super.setGrafico(to.getGrafico());
		super.setGrupoId(to.getGrupoId());
		super.setGrupoNome(to.getGrupoNome());
		super.setInclinacaoId(to.getInclinacaoId());
		super.setInclinacaoNome(to.getInclinacaoNome());
		super.setObservacao(to.getObservacao());
		super.setSigla(to.getSigla());
		super.setUnidadeId(to.getUnidadeId());
		super.setUnidadeNome(to.getUnidadeNome());
		super.setId(to.getId());
	}
}
