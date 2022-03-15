package br.com.tijobs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.repository.EmpresaRepository;

@Named
@ViewScoped
public class CadastroEmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private Empresa empresa;

	private List<SelectItem> tiposGrupo;

	private List<String> ramos;

	private List<String> distritos;

	private UploadedFile file;

	@PostConstruct
	public void init() {
		
		if(empresa == null) {
			empresa = new Empresa();
		}
		
		tiposGrupo = new ArrayList<>();

		SelectItemGroup tiposStartUp = new SelectItemGroup("Start Up");
		tiposStartUp.setSelectItems(new SelectItem[] { new SelectItem("Nacional", "Nacional"),
				new SelectItem("Multinacional", "Multinacional"), new SelectItem("Internacional", "Internacional") });

		SelectItemGroup tiposEmpresa = new SelectItemGroup("Empresa");
		tiposEmpresa.setSelectItems(new SelectItem[] { new SelectItem("Nacional", "Nacional"),
				new SelectItem("Multinacional", "Multinacional"), new SelectItem("Internacional", "Internacional") });

		tiposGrupo.add(tiposStartUp);
		tiposGrupo.add(tiposEmpresa);

		ramos = new ArrayList<>();

		ramos.add("Industrial");
		ramos.add("Comércio");
		ramos.add("Tecnologia");
		ramos.add("Governo");
		ramos.add("Financeiro");
		ramos.add("Industrial");
		ramos.add("Hospitalar");
		ramos.add("Educacional");
		ramos.add("Transporte");
		ramos.add("Infra-Estrutura");
		ramos.add("Clínica");
		ramos.add("Imobiliário");
		ramos.add("Comunicação");
		ramos.add("Transporte");
		ramos.add("Juridiário");
		ramos.add("Eletro/Eletronicos");
		ramos.add("Segurança");
		ramos.add("Têxtil");
		ramos.add("Administração");
		ramos.add("Outros");

		buscaDistritosSP();
	}

	public void buscaDistritosSP() {

		String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/SP/distritos";
		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		JSONArray array = new JSONArray(response.getBody());

		distritos = new ArrayList<>();

		distritos.add("São Paulo - Central");

		for (Object objeto : array) {
			JSONObject json = (JSONObject) objeto;
			distritos.add(json.getString("nome"));
		}
	}

	public void salvar() throws IOException {
		Empresa empresaSalva = empresaRepository.save(empresa);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("idEmpresa", empresaSalva.getId());
		FacesContext.getCurrentInstance().getExternalContext().redirect("");;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<SelectItem> getTiposGrupo() {
		return tiposGrupo;
	}

	public List<String> getRamos() {
		return ramos;
	}

	public List<String> getDistritos() {
		return distritos;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
		if(file != null) {
			empresa.setLogotipo(file.getContent());
		}
	}
}
