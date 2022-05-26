package br.com.tijobs.controller.vaga;

import static br.com.tijobs.util.Message.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Vaga;
import br.com.tijobs.model.habilidade.ListaHabilidade;
import br.com.tijobs.repository.habilidade.ListaHabilidadeRepository;
import br.com.tijobs.service.VagaService;
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
	private ListaHabilidadeRepository habilidadeRepository;

	@Autowired
	private UtilService utilService;

	@Autowired
	private VagaService vagaService;

	@PostConstruct
	public void init() {

		if (has(idVaga)) {
			
			vaga = vagaService.buscaVagaPorId(idVaga);

			if (vaga.getBeneficios() != null) {
				setBeneficiosSelecionados(Arrays.stream(vaga.getBeneficios().split(",")).map(String::valueOf)
						.collect(Collectors.toList()));
			}

			if (vaga.getPrincipaisTecnologias() != null) {
				List<String> habilidadesNomes = Arrays.stream(vaga.getPrincipaisTecnologias().split(","))
						.map(String::valueOf).collect(Collectors.toList());
				setTecnologiasSelecionadas(habilidadeRepository.findByNomeIn(habilidadesNomes));
			}

		} else if (vaga == null) {
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

	public void cadastrar() {
		try {
			vagaService.cadastrarVaga(vaga, utilService.usuarioLogado());
		} catch (IOException e) {
			addDetailMessage("Vaga cadastrada", FacesMessage.SEVERITY_INFO);
			e.printStackTrace();
		}

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

		if (beneficiosSelecionados != null && !beneficiosSelecionados.isEmpty()) {
			vaga.setBeneficios(beneficiosSelecionados.stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
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

		List<String> habilidades = new ArrayList<String>();

		for (ListaHabilidade habilidade : tecnologiasSelecionadas) {
			habilidades.add(habilidade.getNome());
		}

		if (habilidades != null && !habilidades.isEmpty()) {
			vaga.setPrincipaisTecnologias(habilidades.stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}

}
