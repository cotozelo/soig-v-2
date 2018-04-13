package br.com.nois.sa.rc.jwtme.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nois.sa.rc.jwtme.service.TokenAuthenticationService;
import br.com.nois.sa.rc.jwtme.service.UserDetailsServiceImpl;
import br.com.nois.sa.rc.repository.UsuarioRepository;

//@Controller
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private UsuarioRepository usuarioRepository;

	public JWTLoginFilter(String url, AuthenticationManager authManager, UsuarioRepository usuarioRepository) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {

		UserCredentials creds = new ObjectMapper().readValue(req.getInputStream(), UserCredentials.class);

		/*
		 * UsernamePasswordAuthenticationToken userPAT = new
		 * UsernamePasswordAuthenticationToken(creds.getUsername(),
		 * creds.getPassword(), Collections.<GrantedAuthority>emptyList());
		 * AuthenticationManager am = getAuthenticationManager(); Authentication
		 * auth = am.authenticate(userPAT); return auth;
		 */

		UserDetailsServiceImpl myUserDetails = new UserDetailsServiceImpl(this.usuarioRepository);
		myUserDetails.setSenha(creds.getPassword());

		UserDetails userDetails = myUserDetails.loadUserByUsername(creds.getUsername());

		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(res, auth.getName());
	}
}