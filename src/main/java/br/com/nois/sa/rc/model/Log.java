package br.com.nois.sa.rc.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log")
public class Log {

	@Id
	private String id = null;
	private String usuario_id;
	private String funcionalidade;
	private long data = 0;
	private long versaoGlobal = -1L;
	private String mudanca;

	public Log() {
		super();
	}

	public Log(String funcionalidade, String mudanca) {
		super();
		this.id = ObjectId.get().toString();
		this.mudanca = mudanca;
		this.funcionalidade = funcionalidade;
	}

	public Log(String funcionalidade, String mudanca, long versaoGlobal) {
		super();
		this.id = ObjectId.get().toString();
		this.funcionalidade = funcionalidade;
		this.versaoGlobal = versaoGlobal;
		this.mudanca = mudanca;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = ObjectId.get().toString();
	}

	public String getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public long getData() {
		return data;
	}

	public void setData(long l) {
		this.data = l;
	}

	public long getVersaoGlobal() {
		return versaoGlobal;
	}

	public void setVersaoGlobal(long versaoGlobal) {
		this.versaoGlobal = versaoGlobal;
	}

	public String getMudanca() {
		return mudanca;
	}

	public void setMudanca(String mudanca) {
		this.mudanca = mudanca;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", usuario_id=" + usuario_id + ", funcionalidade=" + funcionalidade + ", data=" + data
				+ ", versaoGlobal=" + versaoGlobal + ", mudanca=" + mudanca + "]";
	}

}
