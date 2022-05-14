package br.com.tijobs.controller;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.usuario.Usuario;
import br.com.tijobs.usuario.UsuarioRepository;
import br.com.tijobs.util.EmailUtil;

@Named
@ViewScoped
public class RecoverController {

	private String emailUsuario;

	private String codigoUsuario;

	private String codigo;
	
	private Usuario usuario;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EmailUtil email;

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
				email.enviar(emailUsuario, "TI Jobs - Recuperação de Senha", "Código: " + codigo);
				usuario = usuarioRepository.findByEmail(emailUsuario);
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
	
	public void senhasIguais() {
		
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
