package br.com.tijobs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.EmpresaRepository;
import br.com.tijobs.repository.VagaRepository;
import br.com.tijobs.security.SecurityService;

@Named
@ViewScoped
public class CadastroVagaController {

	private Vaga vaga;

	private List<String> tecnologias;

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SecurityService securityService;

	@PostConstruct
	public void init() {

		if (vaga == null) {
			vaga = new Vaga();
		}

		tecnologias = new ArrayList<>();
		tecnologias.add("Linguagem Python");
		tecnologias.add("Linguagem JavaScript");
		tecnologias.add("Linguagem Java");
		tecnologias.add("Linguagem Kotlin");
		tecnologias.add("Unity");
		tecnologias.add("Exilir");
		tecnologias.add("Node.js");
		tecnologias.add(".NET");
		tecnologias.add("Clojure");
		tecnologias.add("WordPress");
		tecnologias.add("Angular");
		tecnologias.add("Vue.js");
		tecnologias.add("React.js");
		tecnologias.add("SQL");
		tecnologias.add("BI");
		tecnologias.add("AWS");
		tecnologias.add("DevOps");
		tecnologias.add("Linux");
		tecnologias.add("Docker");
		tecnologias.add("Android");
		tecnologias.add("IOS");
		tecnologias.add("Flutter");
		tecnologias.add("Linguagem C");
		tecnologias.add("Linguagem C#");
		tecnologias.add("Linguagem C++");
		tecnologias.add("Linguagem PHP");
		tecnologias.add("Linguagem Ruby");
		tecnologias.add("Linguagem Go");
		tecnologias.add("Linguagem TypeScript");

	}

	public void cadastrar() throws IOException {
		Empresa empresa = empresaRepository.findByUsuario(securityService.getLogado());

		vaga.setEmpresa(empresa);

		vagaRepository.save(vaga);

		FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public List<String> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<String> tecnologias) {
		this.tecnologias = tecnologias;
	}
}
