package br.com.tijobs.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Usuario;
import br.com.tijobs.security.SecurityService;

@Named
@ViewScoped
public class CadastreController {

	private Integer index = 0;

	private Usuario usuarioCandidato;

	private Usuario usuarioEmpresa;

	private boolean avisoCandidato = false;

	private boolean avisoEmpresa = false;

	@Autowired
	private SecurityService securityService;


	@PostConstruct
	public void init() {

		if (usuarioCandidato == null && usuarioEmpresa == null) {
			usuarioCandidato = new Usuario();
			usuarioEmpresa = new Usuario();
		}
	}

	public void senhasIguaisCandidato() {
		this.avisoCandidato = securityService.senhasIguais(usuarioCandidato);
	}

	public void senhasIguaisEmpresa() {
		this.avisoEmpresa = securityService.senhasIguais(usuarioEmpresa);
	}

	public void criarCadastroUsuario() throws IOException {
		securityService.criaCadastro(usuarioCandidato, 1);
	}

	public void criarCadastroEmpresa() throws IOException {
		securityService.criaCadastro(usuarioEmpresa, 2);
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Usuario getUsuarioCandidato() {
		return usuarioCandidato;
	}

	public void setUsuarioCandidato(Usuario usuarioCandidato) {
		this.usuarioCandidato = usuarioCandidato;
	}

	public Usuario getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	public void setUsuarioEmpresa(Usuario usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	public boolean isAvisoCandidato() {
		return avisoCandidato;
	}

	public boolean isAvisoEmpresa() {
		return avisoEmpresa;
	}
}
