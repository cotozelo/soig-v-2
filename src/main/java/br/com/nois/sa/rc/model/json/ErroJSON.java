package br.com.nois.sa.rc.model.json;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ErroJSON {

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String error = "";
	String message = "";
	String path = "";

	public ErroJSON() {

	}

	public ErroJSON(String error, String path) {
		this.path = path;
		this.error = error;
		Tabela.getInstance();
		this.message = Tabela.getMessage(error);
	}

	public ErroJSON(Exception ex, String path) {
		this.path = path;
		this.error = ex.toString() + " " + ex.getClass().getName();
		this.message = ex.getMessage();
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	static class Tabela {

		private static Map<String, String> codigoErro = null;

		private static Tabela INSTANCE = null;

		private Tabela() {
		}

		private static void criateMap() {
			codigoErro = new HashMap<String, String>();
			codigoErro.put("VxUxRx00001", "Não retornou usuario");
			codigoErro.put("VxPxRx00001", "Não retornou perfil");
			codigoErro.put("VxPxUx00001", "Não efetuou update");
			codigoErro.put("VxPxDx00001", "Não efetuou detele");
			codigoErro.put("VxPxIx00001", "Não efetuou o insert");
		}

		public static String getMessage(String codigo) {
			if (Tabela.codigoErro.containsKey(codigo)) {
				return Tabela.codigoErro.get(codigo);
			}
			return "null";
		}

		public static Tabela getInstance() {
			if (INSTANCE == null) {
				INSTANCE = new Tabela();
				Tabela.criateMap();
			}
			return INSTANCE;
		}
	}
}
