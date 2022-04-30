package br.com.tijobs.security;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tijobs.model.Usuario;
import br.com.tijobs.repository.UsuarioRepository;


@Service
public class SecurityService {

	@Autowired
	private UsuarioRepository repository;

	private Usuario logado;

	private Logger logger;

	private static String email;

	private static String senha;

	@PostConstruct
	public void init() {
		logger = LoggerFactory.getLogger(SecurityService.class);
	}

	public Usuario login(String email, String senha) {

		if (SecurityService.email != null && SecurityService.senha != null) {
			email = SecurityService.email;
			senha = SecurityService.senha;
			SecurityService.email = null;
			SecurityService.senha = null;
		}

		Usuario user = repository.findByEmail(email);

		if (user != null && (passwordEncoder().matches(senha, user.getSenha()))) { 
			this.logado = user;
			repository.save(user);
			logger.info("LOGIN BEM SUCEDIDO: " + email);
			return user;
		} else {
			logger.info("LOGIN NEGADO: " + email);
			throw new AuthenticationCredentialsNotFoundException("Usuário e/ou senha incorretos.");
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		SecurityService.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		SecurityService.senha = senha;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

}
