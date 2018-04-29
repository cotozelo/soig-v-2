package br.com.nois.sa.rc.model.json;

public class BooleanJSON {
	private String chave;
	private String valor;
	private boolean exite;

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isExite() {
		return exite;
	}

	public void setExite(boolean exite) {
		this.exite = exite;
	}

	@Override
	public String toString() {
		return "BooleanJSON [chave=" + chave + ", valor=" + valor + ", exite=" + exite + "]";
	}
}
