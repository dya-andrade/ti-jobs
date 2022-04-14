package br.com.tijobs.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.PerfilAcesso;
import br.com.tijobs.model.Usuario;
import br.com.tijobs.repository.PerfilAcessoRepository;
import br.com.tijobs.repository.UsuarioRepository;
import br.com.tijobs.security.SecurityService;

@Named
@ViewScoped
public class CadastreUsuarioController {
	
	private Usuario usuario;
	
	@Autowired
	SecurityService service;
	
	@Autowired
	PerfilAcessoRepository perfilAcessoRepository;
	
	@Autowired
	UsuarioRepository repository;
	
	@PostConstruct
	public void init() {
		
		if(usuario == null) {
			usuario = new Usuario();
		}
		

		
	}
	
	public void cadastre() {
		usuario.setSenha(service.passwordEncoder().encode(usuario.getSenha()));
		Optional<PerfilAcesso> perfilCandidato = perfilAcessoRepository.findById(1);
		usuario.setPerfil(perfilCandidato.get());
		
		repository.save(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
