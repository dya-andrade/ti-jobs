package br.com.tijobs.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class IndexController {

	private String tijobsTexto = "<TI JOBS/>";

	@Autowired
	private UtilService utilService;


	@PostConstruct
	public void init() {
		
	}

	public String getFotoStr() {
		return utilService.fotoUsuarioLogado();
	}

	public String getTijobsTexto() {
		return tijobsTexto;
	}
}
