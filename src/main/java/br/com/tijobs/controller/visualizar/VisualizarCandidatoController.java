package br.com.tijobs.controller.visualizar;

import static com.github.adminfaces.template.util.Assert.has;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.repository.CandidatoRepository;

@Named
@ViewScoped
public class VisualizarCandidatoController {

	private Candidato candidato;
	
	private Integer idCandidato;
	
	@Autowired
	private CandidatoRepository candidatoRepository;

	@PostConstruct
	public void init() {

		if(has(idCandidato)) {
			candidato = candidatoRepository.findById(idCandidato).get();
		}else {
			candidato = new Candidato();
		}
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Integer getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}
}
