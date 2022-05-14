package br.com.tijobs.controller;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.io.IOException;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Usuario;
import br.com.tijobs.security.SecurityService;
import br.com.tijobs.service.UsuarioService;
import br.com.tijobs.util.EmailUtil;

@Named
@ViewScoped
public class RecoverController {

	private String emailUsuario;

	private String codigoUsuario;

	private String codigo;

	private Usuario usuario;

	private boolean aviso = false;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmailUtil email;

	@Autowired
	private SecurityService securityService;

	@PostConstruct
	public void init() {

	}

	public void enviarCodigo() {
		if (emailUsuario != null) {

			Random random = new Random();

			this.codigo = "";

			for (int i = 0; i < 6; i++) {
				int numero = random.nextInt(9);
				this.codigo += numero;
			}
			try {
				usuario = usuarioService.buscaUsuarioPorEmail(emailUsuario);
				if (usuario != null) {
					email.enviar(emailUsuario, "TI Jobs - Recuperação de Senha", "Código: " + codigo);
				} else {
					addDetailMessage("Nenhuma conta foi localizada com este email", FacesMessage.SEVERITY_WARN);
				}
			} catch (Exception e) {
				addDetailMessage("Erro ao tentar enviar o código", FacesMessage.SEVERITY_ERROR);
				e.printStackTrace();
			}
		} else {
			addDetailMessage("Necessário informar o e-mail", FacesMessage.SEVERITY_WARN);
		}
	}

	public void confirmarCodigo() {
		if (codigoUsuario != null) {
			if (codigoUsuario.equals(codigo)) {
				System.out.println("São iguais");
			} else {
				System.out.println("São diferentes");
			}

		} else {
			addDetailMessage("Necessário informar o código", FacesMessage.SEVERITY_WARN);
		}
	}

	public void confirmarSenhaNova() {
		try {
			securityService.resetarSenha(usuario);
		} catch (IOException e) {
			addDetailMessage("Erro ao tentar resetar senha", FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
	}

	public void senhasIguais() {
		this.aviso = securityService.senhasIguais(usuario);
	}

	public boolean isAviso() {
		return aviso;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
