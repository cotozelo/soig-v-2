package br.com.nois.sa.rc.jwtme.security;

public class AccountCredentials {

	private String username;
	private String password;

	public String getUsername() {
		System.out.println("\n getUsername");
		return username;
	}

	public void setUsername(String username) {
		System.out.println("\n setUsername");
		this.username = username;
	}

	public String getPassword() {
		System.out.println("\n getPassword");
		return password;
	}

	public void setPassword(String password) {
		System.out.println("\n setPassword");
		this.password = password;
	}
}