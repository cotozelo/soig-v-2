package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.DadoValor;

@Document(collection = "DadoValor")
public class DadoValorTO extends DadoValor {
	public DadoValorTO() {
		super();
	}
}
