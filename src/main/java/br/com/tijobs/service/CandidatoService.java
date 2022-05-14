package br.com.tijobs.service;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Experiencia;
import br.com.tijobs.model.habilidade.Habilidade;
import br.com.tijobs.repository.CandidatoRepository;

@Service
public class CandidatoService {
	
	@Autowired
	private CandidatoRepository candidatoRepository;

	public void cadastrarCandidato(Candidato candidato) throws IOException {
		if (candidato.getHabilidades().get(0).getCandidato() == null) {
			for (Habilidade habilidade : candidato.getHabilidades()) {
				habilidade.setCandidato(candidato);
			}
		}

		if (candidato.getExperiencias().get(0).getCandidato() == null) {
			for (Experiencia experiencia : candidato.getExperiencias()) {
				experiencia.setCandidato(candidato);

				for (Habilidade habilidade : experiencia.getHabilidades()) {
					habilidade.setExperiencia(experiencia);
				}
			}
		}

		candidatoRepository.save(candidato);

		FacesContext.getCurrentInstance().getExternalContext().redirect("/perfil/candidato.xhtml");
	}

	public Candidato buscarCadidatoPorId(Integer idCandidato) {
		return candidatoRepository.findById(idCandidato).get()	;
	}

}
