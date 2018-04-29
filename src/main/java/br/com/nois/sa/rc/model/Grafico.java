package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum Grafico {
	BARRA, barra;

	public String getLabel(Grafico grafico) {
		String retorno = "";
		switch (grafico) {
		case BARRA:
			retorno = Constantes.GRAFICO_BARRA.toString();
			break;
		case barra:
			retorno = Constantes.GRAFICO_BARRA.toString();
			break;
		default:
			retorno = Constantes.GRAFICO_BARRA.toString();
			break;
		}
		return retorno;
	}
}
