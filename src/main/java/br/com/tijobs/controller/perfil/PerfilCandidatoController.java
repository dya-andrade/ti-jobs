package br.com.tijobs.controller.perfil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

	
	@PostConstruct
	public void init() {
		
		candidato = utilService.perfilCandidato();
		
		if(candidato != null) {
			vagas = vagaService.buscaVagasPeloIdCandidato(candidato.getId());
		}else {
			vagas = new ArrayList<Vaga>();
		}
		
	}
	
	public String getFotoStr() {
		return utilService.fotoUsuarioLogado();
	}

	public List<Vaga> getVagas() {
		Collections.sort(vagas);

		vagas = vagas.stream().sorted(Comparator.comparing(Vaga::getDesativada)).collect(Collectors.toList());
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
