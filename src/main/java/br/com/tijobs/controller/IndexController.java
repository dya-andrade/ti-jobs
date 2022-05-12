package br.com.tijobs.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.PerfilAcesso;
import br.com.tijobs.model.Usuario;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.service.VagaService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class IndexController {

	private String tijobsTexto = "<TI/>JOBS";

	@Autowired
	private UtilService utilService;
	
	@Autowired
	private VagaService vagaService;
	
	private Usuario usuario;
	
	private List<Vaga> vagas;
	

	@PostConstruct
	public void init() {
		usuario = utilService.usuarioLogado();
		
		vagas = vagaService.buscaTodasVagas();	
	}
	
	public void verVagas() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/vagas.xhtml");
	}
	
	public void cadastraSe() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/cadastre.xhtml");
	}
	
	public void dashboard() throws IOException {
		
		PerfilAcesso perfil = usuario.getPerfil();
		
		if(perfil.getId() == 1) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/candidato.xhtml");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/empresa.xhtml");
		}
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public String getFotoStr() {
		return utilService.fotoUsuarioLogado();
	}

	public String getTijobsTexto() {
		return tijobsTexto;
	}
}
