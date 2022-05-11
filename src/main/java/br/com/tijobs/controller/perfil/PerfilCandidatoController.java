package br.com.tijobs.controller.perfil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.service.VagaService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class PerfilCandidatoController {

	private List<Vaga> vagas;

	private Vaga vagaSelecionada;

	private Candidato candidato;

	@Autowired
	private VagaService vagaService;

	@Autowired
	private UtilService utilService;

	private List<String> colors;

	private List<String> backgroundColors;

	@PostConstruct
	public void init() {

		candidato = utilService.perfilCandidato();

		if (candidato != null) {
			vagas = vagaService.buscaVagasPeloIdCandidato(candidato.getId());
		} else {
			vagas = new ArrayList<Vaga>();
		}

		colors = new ArrayList<String>();
		backgroundColors = new ArrayList<String>();

		colors.add(0, "#eb60c");
		backgroundColors.add(0, "#fddbff");

		colors.add(1, "#3ad738");
		backgroundColors.add(1, "#d9f7de");

		colors.add(2, "#3477a7");
		backgroundColors.add(2, "#cfe6f9");

		colors.add(3, "#d1df5c");
		backgroundColors.add(2, "#f6ffc5");

		colors.add(4, "#e53d3d");
		backgroundColors.add(4, "#ffd1db");

	}

	public String tecnologiasCores() {

		Random random = new Random();

		int numero = random.nextInt(5);

		return "border-radius: 16px; background-color: " + backgroundColors.get(numero)
				+ "; padding: 0 0.5rem; font-size: 15px; color: " + colors.get(numero) + "; font-weight: 600;";
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
