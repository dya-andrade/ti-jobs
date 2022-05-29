package br.com.tijobs.controller.empresa;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.service.EmpresaService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class CadastroEmpresaController {

	@Autowired
	private EmpresaService empresaService;

	private Empresa empresa;

	private List<SelectItem> tiposGrupo;

	private List<String> ramos;

	private List<String> distritos;

	private UploadedFile file;

	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {

		if (empresa == null) {
			
			empresa = utilService.perfilEmpresa();
			
			if (empresa == null) {
				empresa = new Empresa();
			}
		}

		adicionaTiposGrupo();

		adicionaRamos();

		distritos = utilService.buscaDistritosSP();
	}

	private void adicionaTiposGrupo() {
		tiposGrupo = new ArrayList<>();

		SelectItemGroup tiposEmpresa = new SelectItemGroup("Empresa");
		tiposEmpresa.setSelectItems(new SelectItem[] { new SelectItem("Nacional", "Nacional"),
				new SelectItem("Multinacional", "Multinacional"), new SelectItem("Internacional", "Internacional") });

		tiposGrupo.add(tiposEmpresa);
	}

	private void adicionaRamos() {
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
	}

	public void salvar() {

		empresa.setUsuario(utilService.usuarioLogado());

		try {
			empresaService.cadastrarEmpresa(empresa);
		} catch (IOException e) {
			addDetailMessage("Cadastro salvo", FacesMessage.SEVERITY_INFO);
			e.printStackTrace();
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
		if (file.getFileName() != null) {
			empresa.setLogotipo(file.getContent());
		}
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
}
