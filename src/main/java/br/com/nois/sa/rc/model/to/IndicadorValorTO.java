package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.IndicadorValor;

@Document(collection = "IndicadorValor")
public class IndicadorValorTO extends IndicadorValor {
	public IndicadorValorTO() {
		super();
	}

	public IndicadorValorTO(IndicadorTO to) {
		super();
		super.setId();
		super.setSigla(to.getSigla());
		super.setIndicadorId(to.getId());
	}

}
