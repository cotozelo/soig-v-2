package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum TipoFavorito {
	DADO, INDICADOR;

	public String getLabel(TipoFavorito enumerator) {
		String retorno = "";
		switch (enumerator) {
		case DADO:
			retorno = new Constantes().DADO.toString();
			break;
		case INDICADOR:
			retorno = new Constantes().INDICADOR.toString();
			break;
		default:
			break;
		}
		return retorno;
	}
}
