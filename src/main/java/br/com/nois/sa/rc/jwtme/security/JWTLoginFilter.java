package br.com.nois.sa.rc.jwtme.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nois.sa.rc.model.Usuario;
import br.com.nois.sa.rc.repository.UsuarioRepository;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	UsuarioRepository usuarioRepository;

	// protected JWTLoginFilter(String url, AuthenticationManager authManager) {
	protected JWTLoginFilter(String url, AuthenticationManager authManager, UsuarioRepository usuarioRepository) {
		super(new AntPathRequestMatcher(url));
		System.out.println("JWTLoginFilter");
		this.usuarioRepository = usuarioRepository;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		System.out.println("attemptAuthentication");
		Usuario usuarioMongo = usuarioRepository.findByNomeDeUsuario(usuario.getNomeDeUsuario());
		// usuario = usuarioRepository.login(usuario.getNomeDeUsuario(),
		// usuario.getSenha());

		if (usuarioMongo != null) {
			System.out.println("IF 01: " + usuarioMongo.getNomeDeUsuario() + " - " + usuarioMongo.getSenha());
			try {
				Authentication aline = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
						usuarioMongo.getNomeDeUsuario(), usuarioMongo.getSenha(), Collections.emptyList()));
				return aline;
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} else {
			System.out.println("ELSE 01: ");
		}

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				usuario.getNomeDeUsuario(), usuario.getSenha(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {
		System.out.println("successfulAuthentication");

		TokenAuthenticationService.addAuthentication(response, auth.getName());
		System.out.println("FIIIIMM\n");
	}

}