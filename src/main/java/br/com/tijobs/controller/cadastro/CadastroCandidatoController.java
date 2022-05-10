package br.com.tijobs.controller.cadastro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Experiencia;
import br.com.tijobs.model.Habilidade;
import br.com.tijobs.repository.CandidatoRepository;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class CadastroCandidatoController {

	private List<String> distritos;

	private Candidato candidato;

	@Autowired
	private CandidatoRepository candidatoRepository;

	private UploadedFile foto;

	private UploadedFile curriculo;

	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {

		if (candidato == null) {

			Candidato candidatoLogado = utilService.perfilCandidato();

			if (candidatoLogado != null) {
				candidato = candidatoLogado;
			} else {
				candidato = new Candidato();
			}

			if (candidato.getExperiencias() == null) {
				candidato.setExperiencias(new ArrayList<Experiencia>());
				candidato.getExperiencias().add(new Experiencia());
				candidato.getExperiencias().add(new Experiencia());
				candidato.getExperiencias().add(new Experiencia());
			}

			if (candidato.getExperiencias() == null) {

				for (Experiencia experiencia : candidato.getExperiencias()) {

					experiencia.setHabilidades(new ArrayList<Habilidade>());

					experiencia.getHabilidades().add(new Habilidade());
					experiencia.getHabilidades().add(new Habilidade());
					experiencia.getHabilidades().add(new Habilidade());
					experiencia.getHabilidades().add(new Habilidade());
				}
			}

			if (candidato.getHabilidades() == null) {

				candidato.setHabilidades(new ArrayList<Habilidade>());

				candidato.getHabilidades().add(new Habilidade());
				candidato.getHabilidades().add(new Habilidade());
				candidato.getHabilidades().add(new Habilidade());
				candidato.getHabilidades().add(new Habilidade());
				candidato.getHabilidades().add(new Habilidade());
				candidato.getHabilidades().add(new Habilidade());

			}
		}

		distritos = utilService.buscaDistritosSP();
	}

	public void salvar() {

		for (Habilidade habilidade : candidato.getHabilidades()) {
			habilidade.setCandidato(candidato);
		}

		for (Experiencia experiencia : candidato.getExperiencias()) {
			experiencia.setCandidato(candidato);

			for (Habilidade habilidade : experiencia.getHabilidades()) {
				habilidade.setExperiencia(experiencia);
			}
		}

		candidatoRepository.save(candidato);
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile file) {
		this.foto = file;
		if (file != null) {
			candidato.setFoto(file.getContent());
		}
	}

	public UploadedFile getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(UploadedFile file) {
		this.curriculo = file;
		if (file != null) {
			candidato.setCurriculo(file.getContent());
		}
	}

	public List<String> getTamanhoEmpresa() {

		String tamanhoEmpresa = candidato.getTamanhoEmpresa();

		if (tamanhoEmpresa != null) {
			return Arrays.stream(tamanhoEmpresa.split(",")).map(String::valueOf).collect(Collectors.toList());
		}

		return null;
	}

	public void setTamanhoEmpresa(List<String> tamanhoEmpresa) {
		if (tamanhoEmpresa != null && !tamanhoEmpresa.isEmpty()) {
			candidato.setTamanhoEmpresa(tamanhoEmpresa.stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}

	public List<String> getTipoContrato() {
		String tipoContrato = candidato.getTipoContrato();

		if (tipoContrato != null) {
			return Arrays.stream(tipoContrato.split(",")).map(String::valueOf).collect(Collectors.toList());
		}

		return null;
	}

	public void setTipoContrato(List<String> tipoContrato) {
		if (tipoContrato != null && !tipoContrato.isEmpty()) {
			candidato.setTipoContrato(tipoContrato.stream().map(String::valueOf).collect(Collectors.joining(",")));
		}
	}

	public List<String> getDistritos() {
		return distritos;
	}

	public List<String> getHabilidades() {
		return utilService.habilidades();
	}
}