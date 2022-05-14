package br.com.tijobs.security;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tijobs.model.Usuario;
import br.com.tijobs.repository.PerfilAcessoRepository;
import br.com.tijobs.repository.UsuarioRepository;

@Service
public class SecurityService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PerfilAcessoRepository perfilAcessoRepository;

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

	public void criaCadastro(Usuario usuario, int idPerfil) throws IOException {
		if (usuario.getEmail() != null && usuario.getConfirmeSenha() != null && usuario.getSenha() != null) {
			Usuario jaExisteUsuario = repository.findByEmail(usuario.getEmail());

			if (jaExisteUsuario == null) {
				usuario.setPerfil(perfilAcessoRepository.findById(idPerfil).get());

				if (usuario.getSenha().equals(usuario.getConfirmeSenha())) {
					usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));
					repository.save(usuario);

					HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
							.getSession(true);
					session.setAttribute("usuario", usuario);

					FacesContext.getCurrentInstance().getExternalContext().redirect("/login.jsf");

				} else {
					addDetailMessage("As senhas não são iguais", FacesMessage.SEVERITY_WARN);
				}
			} else {
				addDetailMessage("Já existe usuário cadastrado com este e-mail", FacesMessage.SEVERITY_WARN);
			}
		} else {
			addDetailMessage("Todos os campos são obrigatório", FacesMessage.SEVERITY_WARN);
		}
	}

	public void resetarSenha(Usuario usuario) throws IOException {
		if (usuario.getConfirmeSenha() != null && usuario.getSenha() != null) {

			if (usuario.getSenha().equals(usuario.getConfirmeSenha())) {
				usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));
				repository.save(usuario);

				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(true);
				session.setAttribute("usuario", usuario);

				FacesContext.getCurrentInstance().getExternalContext().redirect("/login.jsf");

			} else {
				addDetailMessage("As senhas não são iguais", FacesMessage.SEVERITY_WARN);
			}
		} else {
			addDetailMessage("Todos os campos são obrigatório", FacesMessage.SEVERITY_WARN);
		}

	}

	public boolean senhasIguais(Usuario usuario) {

		String senha = usuario.getSenha();

		String confirmeSenha = usuario.getConfirmeSenha();

		if (!senha.equals(confirmeSenha)) {
			return true;
		}

		return false;
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
