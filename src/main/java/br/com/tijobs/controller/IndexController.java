package br.com.tijobs.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class IndexController {

	private String tijobsTexto1 = "< TI JOBS";

	private String tijobsTexto2 = "/>";

	@Autowired
	private UtilService utilService;


	@PostConstruct
	public void init() {
		
	}

	public String getFotoStr() {
		return utilService.fotoUsuarioLogado();
	}

	public String getTijobsTexto1() {
		return tijobsTexto1;
	}

	public String getTijobsTexto2() {
		return tijobsTexto2;
	}
}
