package br.com.nois.sa.rc.controller;

import br.com.nois.sa.rc.model.json.ErroJSON;

public class Response<T> {

	private T data;
	private ErroJSON error;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ErroJSON getErrors() {
		return error;
	}

	public void setError(ErroJSON error) {
		this.error = error;
	}

	public Response() {
	}
}
