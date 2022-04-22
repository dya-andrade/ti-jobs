package br.com.tijobs.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Usuario;
import br.com.tijobs.repository.PerfilAcessoRepository;
import br.com.tijobs.repository.UsuarioRepository;
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
	private PerfilAcessoRepository perfilAcessoRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostConstruct
	public void init() {

		if (usuarioCandidato == null && usuarioEmpresa == null) {
			usuarioCandidato = new Usuario();
			usuarioEmpresa = new Usuario();
		}

	}

	public void senhasIguaisCandidato() {
		
		String senha = usuarioCandidato.getSenha();
		
		String confirmeSenha = usuarioCandidato.getConfirmeSenha();
		
		if (senha.equals(confirmeSenha)) {
			avisoCandidato = false;
		} else {
			avisoCandidato = true;
		}
	}
	
	public void senhasIguaisEmpresa() {
		
		String senha = usuarioEmpresa.getSenha();
		
		String confirmeSenha = usuarioEmpresa.getConfirmeSenha();
		
		if (senha.equals(confirmeSenha)) {
			avisoEmpresa = false;
		} else {
			avisoEmpresa = true;
		}
	}

	public void criarCadastroUsuario() throws IOException {

		usuarioCandidato.setPerfil(perfilAcessoRepository.findById(1).get());

		if (usuarioCandidato.getSenha().equals(usuarioCandidato.getConfirmeSenha())) {
			usuarioCandidato.setSenha(securityService.passwordEncoder().encode(usuarioCandidato.getSenha()));
			usuarioRepository.save(usuarioCandidato);

			FacesContext.getCurrentInstance().getExternalContext().redirect("/cadastro/candidato.xhtml");

		}
	}

	public void criarCadastroEmpresa() throws IOException {
		
		usuarioEmpresa.setPerfil(perfilAcessoRepository.findById(2).get());
		
		if (usuarioEmpresa.getSenha().equals(usuarioEmpresa.getConfirmeSenha())) {
			usuarioEmpresa.setSenha(securityService.passwordEncoder().encode(usuarioEmpresa.getSenha()));
			usuarioRepository.save(usuarioEmpresa);

			FacesContext.getCurrentInstance().getExternalContext().redirect("/cadastro/empresa.xhtml");

		}
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
