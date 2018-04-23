package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Equacao;

@Document(collection = "equacao")
public class EquacaoTO extends Equacao {

	public EquacaoTO() {
		super();
	}

}
