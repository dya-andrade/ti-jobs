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
import br.com.tijobs.model.Habilidade;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.EmpresaRepository;
import br.com.tijobs.repository.HabilidadeRepository;
import br.com.tijobs.repository.VagaRepository;
import br.com.tijobs.security.SecurityService;

@Named
@ViewScoped
public class CadastroVagaController {

	private Vaga vaga;

	private List<String> benefícios;
	
	private List<String> beneficiosSelecionados;
	
	private List<Habilidade> habilidades;
	
	private List<Habilidade> tecnologiasSelecionadas;

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private HabilidadeRepository habilidadeRepository;

	@Autowired
	private SecurityService securityService;

	@PostConstruct
	public void init() {

		if (vaga == null) {
			vaga = new Vaga();
		}
		
		habilidades = habilidadeRepository.buscaTodasHabilidades();

		benefícios = new ArrayList<>();
		benefícios.add("Vale Alimentação");
		benefícios.add("Vale Refeição");
		benefícios.add("Plano de Saúde");
		benefícios.add("Plano Odontológico");
		benefícios.add("No dress code");
		benefícios.add("Day off no aniversário");
		benefícios.add("Vale Transporte");
		benefícios.add("Sala de Jogos");
		benefícios.add("Aulas de Inglês");
		benefícios.add("Academia/Gympass");
		benefícios.add("Auxílio Psicólogo");
		benefícios.add("Auxílio Home Office");
		benefícios.add("Bônus");
		benefícios.add("Seguro de Vida");
		benefícios.add("Participação nos Lucros e Resultados");
		benefícios.add("Auxílio Creche");
	
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

	public List<String> getBeneficiosSelecionados() {
		return beneficiosSelecionados;
	}

	public void setBeneficiosSelecionados(List<String> beneficiosSelecionados) {
		this.beneficiosSelecionados = beneficiosSelecionados;
		for (String beneficio : beneficiosSelecionados) {
			String beneficios = null;
			
			for (Habilidade habilidade : tecnologiasSelecionadas) {
				beneficios = beneficios + "," + beneficio;
			}
			
			vaga.setBeneficios(beneficios);
		}
	}

	public List<String> getBenefícios() {
		return benefícios;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public List<Habilidade> getTecnologiasSelecionadas() {
		return tecnologiasSelecionadas;
	}

	public void setTecnologiasSelecionadas(List<Habilidade> tecnologiasSelecionadas) {
		this.tecnologiasSelecionadas = tecnologiasSelecionadas;
		
		String habilidades = null;
		
		for (Habilidade habilidade : tecnologiasSelecionadas) {
			habilidades = habilidades + "," + habilidade.getNome();
		}
		
		vaga.setPrincipaisTecnologias(habilidades);
	}

}

