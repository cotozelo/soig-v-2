package br.com.nois.sa.rc.model.to;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.nois.sa.rc.model.Dado;

@Document(collection = "dado")
public class DadoTO extends Dado {

	public DadoTO() {
		super();
	}

}
