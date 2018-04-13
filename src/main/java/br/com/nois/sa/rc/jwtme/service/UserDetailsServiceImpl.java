package br.com.nois.sa.rc.jwtme.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nois.sa.rc.model.to.UsuarioTO;
import br.com.nois.sa.rc.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UsuarioRepository usuarioRepository;
	private String senha;

	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final UsuarioTO usuarioTO = usuarioRepository.findBySenhaAndNomeDeUsuarioAndAtivo(this.senha, username, true);

		if (usuarioTO == null)
			throw new UsernameNotFoundException("User '" + username + "' not found");

		String role = "ADMIN";
		if (!usuarioTO.isAdmin()) {
			role = usuarioTO.getPerfilId();
		}

		return org.springframework.security.core.userdetails.User//
				.withUsername(username)//
				.password(this.senha)//
				.authorities(role)//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();
	}

}