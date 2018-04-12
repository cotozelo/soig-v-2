package br.com.nois.sa.rc.model.json;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ErroJSON {

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	int status = 0;
	String error = "";
	String exception = "";
	String message = "";
	String path = "";

	public ErroJSON() {

	}

	public ErroJSON(ErroEnum status, String error, String exception, String path) {
		this.status = status.getValor();
		this.path = path;
		this.error = error;
		this.message = Tabela.getInstance().getMessage(error);
		this.exception = exception;
	}

	public ErroJSON(Exception ex, ErroEnum status, String classe) {
		this.status = status.getValor();
		this.path = classe;
		this.exception = ex.getClass().getName();
		this.error = ex.toString();
		this.message = ex.getMessage();
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
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
				INSTANCE.criateMap();
			}
			return INSTANCE;
		}
	}

	public enum ErroEnum {
		GET(200), POST(201), DELETE(204), GET_VAZIO(404), INVALIDO(422);

		private int valor;

		ErroEnum(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return this.valor;
		}
	}
}
