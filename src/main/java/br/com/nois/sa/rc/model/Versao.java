package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "versao")
public class Versao {
	@Id
	private String id = null;
	private long versao = -1l;

	public Versao() {
		super();
	}

	public Versao(long versao) {
		super();
		this.id = ObjectId.get().toString();
		this.versao = versao;
	}

	public Versao(String id, long versao) {
		super();
		this.id = id;
		this.versao = versao;
	}

	public String getId() {
		return this.id;
	}

	public long getVersao() {
		return this.versao;
	}
}
