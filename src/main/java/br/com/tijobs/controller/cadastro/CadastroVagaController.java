package br.com.tijobs.controller.cadastro;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.ListaHabilidade;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.EmpresaRepository;
import br.com.tijobs.repository.ListaHabilidadeRepository;
import br.com.tijobs.repository.VagaRepository;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class CadastroVagaController {

	private Vaga vaga;
	
	private Integer idVaga;

	private List<String> benefícios;

	private List<String> beneficiosSelecionados;

	private List<ListaHabilidade> habilidades;

	private List<ListaHabilidade> tecnologiasSelecionadas;

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private ListaHabilidadeRepository habilidadeRepository;

	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {
		
		if (has(idVaga)) {
			vaga = vagaRepository.findById(idVaga).get();
		}else if (vaga == null) {
			vaga = new Vaga();
		}

		habilidades = habilidadeRepository.findAll();

		adicionaBeneficios();
	}
	
	public Integer getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(Integer idVaga) {
		this.idVaga = idVaga;
	}

	private void adicionaBeneficios() {
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
		Empresa empresa = empresaRepository.findByUsuario(utilService.usuarioLogado());

		vaga.setEmpresa(empresa);
		
		vaga.setDataCriacao(LocalDateTime.now());

		vagaRepository.save(vaga);

		FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/empresa.xhtml");
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

		String beneficios = null;

		for (String beneficio : beneficiosSelecionados) {
			beneficios = beneficios + "," + beneficio;
		}
		
		vaga.setBeneficios(beneficios);
	}

	public List<String> getBenefícios() {
		return benefícios;
	}

	public List<ListaHabilidade> getHabilidades() {
		return habilidades;
	}

	public List<ListaHabilidade> getTecnologiasSelecionadas() {
		return tecnologiasSelecionadas;
	}

	public void setTecnologiasSelecionadas(List<ListaHabilidade> tecnologiasSelecionadas) {
		this.tecnologiasSelecionadas = tecnologiasSelecionadas;

		String habilidades = null;

		for (ListaHabilidade habilidade : tecnologiasSelecionadas) {
			habilidades = habilidades + "," + habilidade.getNome();
		}

		vaga.setPrincipaisTecnologias(habilidades);
	}

}
