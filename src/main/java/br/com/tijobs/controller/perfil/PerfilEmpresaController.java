package br.com.tijobs.controller.perfil;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class PerfilEmpresaController {
	
	private Empresa empresa;
	
	@Autowired
	private UtilService utilService;

	
	@PostConstruct
	public void init() {
		
		empresa = utilService.perfilEmpresa();
		
	}
	
	public String getFotoStr() {
		return utilService.fotoUsuarioLogado();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

}
