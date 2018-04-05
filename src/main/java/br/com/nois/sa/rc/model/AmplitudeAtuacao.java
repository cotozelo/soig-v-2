package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum AmplitudeAtuacao {
	ESTADUAL, REGIONAL, MUNICIPAL;
	
	public String getLabel(AmplitudeAtuacao amplitudeAtuacao) {
		String retorno = "";
		switch (amplitudeAtuacao) {
		case ESTADUAL:
			retorno = new Constantes().AMPLITUDEATUACAO_ESTADUAL.toString();
			break;
		case REGIONAL:
			retorno = new Constantes().AMPLITUDEATUACAO_REGIONAL.toString();
			break;
		case MUNICIPAL:
			retorno = new Constantes().AMPLITUDEATUACAO_MUNICIPAL.toString();
			break;

		default:
			break;
		}
		return retorno;
	}
	
}
