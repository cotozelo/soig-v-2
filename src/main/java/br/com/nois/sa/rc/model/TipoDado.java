package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum TipoDado {
	SOMATORIO, ULTIMOVALOR;

	public String getLabel(TipoDado enumerator) {
		String retorno = "";
		switch (enumerator) {
		case SOMATORIO:
			retorno = Constantes.TIPODADO_SOMATORIO.toString();
			break;
		case ULTIMOVALOR:
			retorno = Constantes.TIPODADO_ULTIMOVALOR.toString();
			break;
		default:
			break;
		}
		return retorno;
	}
}
