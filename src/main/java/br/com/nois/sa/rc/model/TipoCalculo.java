package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum TipoCalculo {
	ACUMULADO, ACUMLADO, MENSAL;

	public String getLabel(TipoCalculo tipoIndicador) {
		String retorno = "";
		switch (tipoIndicador) {
		case ACUMULADO:
			retorno = Constantes.TIPOCALCULO_ACUMULADO.toString();
			break;
		case ACUMLADO:
			retorno = Constantes.TIPOCALCULO_ACUMULADO.toString();
			break;
		case MENSAL:
			retorno = Constantes.TIPOCALCULO_MENSAL.toString();
			break;

		default:
			break;
		}
		return retorno;
	}
}
