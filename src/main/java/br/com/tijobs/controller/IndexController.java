package br.com.tijobs.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexController {
	
	private String tijobsTexto1 = "< TI Jobs";
	
	private String tijobsTexto2 = "/>";

	
	@PostConstruct
	public void init() {
		
		
	}


	public String getTijobsTexto1() {
		return tijobsTexto1;
	}


	public String getTijobsTexto2() {
		return tijobsTexto2;
	}
	
}
