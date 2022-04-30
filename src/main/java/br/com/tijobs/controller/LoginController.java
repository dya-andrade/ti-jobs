package br.com.tijobs.controller;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import br.com.tijobs.model.Usuario;
import br.com.tijobs.security.SecurityService;

@Named
@SessionScope
public class LoginController {

	@Autowired
	private SecurityService security;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		this.usuario = security.getLogado();
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
