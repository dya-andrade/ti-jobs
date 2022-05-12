package br.com.tijobs.controller;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.springframework.web.context.annotation.SessionScope;

import br.com.tijobs.model.Usuario;

@Named
@SessionScope
public class LoginController {

	@PostConstruct
	public void init() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario != null) {
			session.removeAttribute("usuario");

			PrimeFaces current = PrimeFaces.current();

			addDetailMessage("Faça login", FacesMessage.SEVERITY_INFO);
			current.ajax().update("growl");
		}
	}
	
	public void login() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/login.jsf");
	}
}
