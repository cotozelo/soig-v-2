package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum TipoDado {
	SOMATORIO(0), ULTIMOVALOR(1);

	private final int val;

	private TipoDado(int num) {
		val = num;
	}

	@SuppressWarnings("unused")
	private int getValue() {
		return val;
	}

	public String getLabel(TipoDado tipoDado) {
		String retorno = "";
		switch (tipoDado) {
		case SOMATORIO:
			retorno = new Constantes().TIPODADO_SOMATORIO.toString();
			break;
		case ULTIMOVALOR:
			retorno = new Constantes().TIPODADO_ULTIMOVALOR.toString();
			break;

		default:
			break;
		}
		return retorno;
	}
}
