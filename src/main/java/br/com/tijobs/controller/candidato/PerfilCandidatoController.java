package br.com.tijobs.controller.candidato;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.service.CandidatoService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class PerfilCandidatoController {

	private Candidato candidato;

	private Integer idCandidato;

	@Autowired
	private CandidatoService candidatoService;

	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {

		if (has(idCandidato)) {
			candidato = candidatoService.buscarCadidatoPorId(idCandidato);
		} else {

			candidato = utilService.perfilCandidato();

			if (candidato == null) {
				candidato = new Candidato();
			}
		}
	}

	public StreamedContent downloadCV() {
		if (candidato.getCurriculo() != null) {

			List<String> infoCurriculo = Arrays.stream(candidato.getContenttypeFilenameCv().split(",")).map(String::valueOf)
					.collect(Collectors.toList());

			return DefaultStreamedContent.builder().name(infoCurriculo.get(1)).contentType(infoCurriculo.get(0))
					.stream(() -> new ByteArrayInputStream(candidato.getCurriculo())).build();
		}
		return null;
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
