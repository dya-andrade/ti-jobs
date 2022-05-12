package br.com.tijobs.controller.dashboard;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.VagaRepository;
import br.com.tijobs.service.VagaService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class DashboardCandidatoController {

	private List<Vaga> vagas;

	private Vaga vagaSelecionada;

	private Candidato candidato;

	@Autowired
	private VagaService vagaService;

	@Autowired
	private UtilService utilService;
	
	@Autowired
	private VagaRepository vagaRepository;


	@PostConstruct
	public void init() {

		candidato = utilService.perfilCandidato();

		if (candidato != null) {
			vagas = vagaService.buscaVagasPeloIdCandidato(candidato.getId());
		} else {
			vagas = new ArrayList<Vaga>();
		}

	}

	public String tecnologiasCores() {
		
		List<String> backgroundColors = utilService.getBackgroundColors();
		
		List<String> colors = utilService.getColors();

		Random random = new Random();

		int numero = random.nextInt(5);

		return "border-radius: 16px; background-color: " + backgroundColors.get(numero)
				+ "; padding: 0 0.5rem; font-size: 15px; color: " + colors.get(numero) + "; font-weight: 600;";
	}
	
	public void cancelarCandidatura(Vaga vaga) {
		List<Candidato> candidatos = vaga.getCandidatos();
		
		if(candidatos.contains(candidato)) {
			candidatos.remove(candidato);
		}else {
			
			for (Candidato candidato : candidatos) {
				if(candidato.getId() == this.candidato.getId()) {
					candidatos.remove(candidato);
					break;
				}
			}
		}
		
		vaga.setCandidatos(candidatos);
		vagaRepository.save(vaga);
		
		vagas = vagaService.buscaVagasPeloIdCandidato(candidato.getId());
		
		addDetailMessage("Candidatura cancelada", FacesMessage.SEVERITY_INFO);
	}

	public String getFotoStr() {
		return utilService.fotoUsuarioLogado();
	}

	public Integer quantidadeDeEmpresas() {
		if (candidato != null) {
			List<Integer> quantidade = vagaService.buscaQuantidadeDeEmpresasPorCandidato(candidato.getId());
			return quantidade.size();
		} else {
			return 0;
		}
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public Vaga getVagaSelecionada() {
		return vagaSelecionada;
	}

	public void setVagaSelecionada(Vaga vagaSelecionada) {
		this.vagaSelecionada = vagaSelecionada;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public String iconBeneficio(String beneficio) {
		return vagaService.iconeDoBenefício(beneficio);
	}
}
