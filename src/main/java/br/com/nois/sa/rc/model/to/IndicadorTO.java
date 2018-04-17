package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Indicador;

@Document(collection = "indicador")
public class IndicadorTO extends Indicador {
	public IndicadorTO() {
		super();
	}
}
