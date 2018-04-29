package br.com.nois.sa.rc.jwtme.service;

import org.springframework.security.core.AuthenticationException;

public class PerfilNotExistsException extends AuthenticationException {

	public PerfilNotExistsException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public PerfilNotExistsException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}