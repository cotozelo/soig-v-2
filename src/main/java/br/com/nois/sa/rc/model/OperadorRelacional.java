package br.com.nois.sa.rc.model;

import br.com.nois.sa.rc.util.Constantes;

public enum OperadorRelacional {
	IGUAL, MAIOR, MENOR, MAIORIGUAL, MENORIGUAL, DIFERENTE;

	public String getLabel(OperadorRelacional operadorRelacional) {
		String retorno = "";
		switch (operadorRelacional) {
		case IGUAL:
			retorno = new Constantes().OPERADORRELACIONAL_LABEL_IGUAL.toString();
			break;
		case MAIOR:
			retorno = new Constantes().OPERADORRELACIONAL_LABEL_MAIOR.toString();
			break;
		case MENOR:
			retorno = new Constantes().OPERADORRELACIONAL_LABEL_MENOR.toString();
			break;
		case MAIORIGUAL:
			retorno = new Constantes().OPERADORRELACIONAL_LABEL_MAIORIGUAL.toString();
			break;
		case MENORIGUAL:
			retorno = new Constantes().OPERADORRELACIONAL_LABEL_MENORIGUAL.toString();
			break;
		case DIFERENTE:
			retorno = new Constantes().OPERADORRELACIONAL_LABEL_DIFERENTE.toString();
			break;

		default:
			break;
		}
		return retorno;
	}

	public String getExpressaoMatematica(OperadorRelacional operadorRelacional) {
		String retorno = "";
		switch (operadorRelacional) {
		case IGUAL:
			retorno = new Constantes().OPERADORRELACIONAL_EXPRESSAO_IGUAL.toString();
			break;
		case MAIOR:
			retorno = new Constantes().OPERADORRELACIONAL_EXPRESSAO_MAIOR.toString();
			break;
		case MENOR:
			retorno = new Constantes().OPERADORRELACIONAL_EXPRESSAO_MENOR.toString();
			break;
		case MAIORIGUAL:
			retorno = new Constantes().OPERADORRELACIONAL_EXPRESSAO_MAIORIGUAL.toString();
			break;
		case MENORIGUAL:
			retorno = new Constantes().OPERADORRELACIONAL_EXPRESSAO_MENORIGUAL.toString();
			break;
		case DIFERENTE:
			retorno = new Constantes().OPERADORRELACIONAL_EXPRESSAO_DIFERENTE.toString();
			break;

		default:
			break;
		}
		return retorno;
	}
}
