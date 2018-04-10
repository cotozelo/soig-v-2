package br.com.nois.sa.rc.jwtme.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.repository.UsuarioRepository;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	UsuarioRepository usuarioRepository;

	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		System.out.println("\n JWTLoginFilter");
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		System.out.println("\n attemptAuthentication");

		AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(),
				AccountCredentials.class);

		System.out.println("\n ainda attemptAuthentication");
		return getAuthenticationManager(credentials).authenticate(new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), credentials.getPassword(), Collections.emptyList()));
	}

	protected AuthenticationManager getAuthenticationManager(AccountCredentials credentials) {
		System.out.println("\n ALINE: " + credentials.getUsername());
		// Usuario account =
		// accountRepository.findByNomeDeUsuario(credentials.getUsername());
		Usuario account = usuarioRepository.findByNome("Usuario 01");
		System.out.println("\n FIM: " + credentials.getUsername());
		if (account != null) {
			return (AuthenticationManager) new User(account.getNomeDeUsuario(), account.getSenha(), true, true, true,
					true, AuthorityUtils.createAuthorityList("USER"));
		} else {
			throw new UsernameNotFoundException("could not find the user '" + credentials.getUsername() + "'");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {
		System.out.println("\n successfulAuthentication");

		TokenAuthenticationService.addAuthentication(response, auth.getName());
	}

}