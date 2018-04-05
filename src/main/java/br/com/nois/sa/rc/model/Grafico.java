package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum Grafico {
	BARRA(0);

	private int val;

	private Grafico(int num) {
		this.val = num;
	}

	private Grafico(String name) {
		if (name.toUpperCase().equals(new Constantes().GRAFICO_BARRA.toString())) {
			this.val = 0;
		}
	}

	@SuppressWarnings("unused")
	private int getValue() {
		return this.val;
	}

	public String getLabel(Grafico grafico) {
		String retorno = "";
		switch (grafico) {
		case BARRA:
			retorno = new Constantes().GRAFICO_BARRA.toString();
			break;

		default:
			break;
		}
		return retorno;
	}
}
