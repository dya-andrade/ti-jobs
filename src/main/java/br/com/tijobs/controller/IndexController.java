package br.com.tijobs.controller;

import java.io.IOException;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Usuario;
import br.com.tijobs.repository.CandidatoRepository;
import br.com.tijobs.repository.EmpresaRepository;
import br.com.tijobs.security.SecurityService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class IndexController {

	private String tijobsTexto1 = "< TI JOBS";

	private String tijobsTexto2 = "/>";

	private String fotoStr;

	private Usuario usuario;

	@Autowired
	private UtilService utilService;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private CandidatoRepository candidatoRepository;

	@PostConstruct
	public void init() {
		usuario = utilService.usuarioLogado();
		atualizarFoto();
	}

	public void atualizarFoto() {
		
		if(usuario != null) {
			int id = usuario.getPerfil().getId();

			if (id == 1) {
				
				Candidato candidato = candidatoRepository.findByUsuario(usuario);

				if (candidato != null && candidato.getFoto() != null) {

					this.fotoStr = new String(Base64.getEncoder().encode(candidato.getFoto()));

				} else {
					try {
						this.fotoStr = new String(Base64.getEncoder().encode(
								IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("profile.jpg"))));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else if (id == 2) {

				Empresa empresa = empresaRepository.findByUsuario(usuario);

				if (empresa != null && empresa.getLogotipo() != null) {

					this.fotoStr = new String(Base64.getEncoder().encode(empresa.getLogotipo()));

				} else {
					try {
						this.fotoStr = new String(Base64.getEncoder().encode(
								IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("profile.jpg"))));
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	public String getFotoStr() {
		return fotoStr;
	}

	public String getTijobsTexto1() {
		return tijobsTexto1;
	}

	public String getTijobsTexto2() {
		return tijobsTexto2;
	}

}
