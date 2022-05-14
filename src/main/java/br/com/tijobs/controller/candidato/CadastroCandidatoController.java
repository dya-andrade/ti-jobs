package br.com.tijobs.controller.candidato;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SlideEndEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Experiencia;
import br.com.tijobs.model.habilidade.Habilidade;
import br.com.tijobs.service.CandidatoService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class CadastroCandidatoController {

	private List<String> distritos;

	private Candidato candidato;

	private CandidatoService candidatoService;

	private UploadedFile foto;

	private UploadedFile curriculo;

	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {

		instanciaCandidato();
		distritos = utilService.buscaDistritosSP();
	}

	public void salvar() {
		try {
			candidatoService.cadastrarCandidato(candidato);
		} catch (IOException e) {
			addDetailMessage("Cadastro salvo", FacesMessage.SEVERITY_INFO);
			e.printStackTrace();
		}

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
		if (file.getFileName() != null) {
			candidato.setFoto(file.getContent());
		}
	}

	public UploadedFile getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(UploadedFile file) {
		this.curriculo = file;

		if (file.getFileName() != null) {

			String contenttypeFilenameCv = file.getContentType() + "," + file.getFileName();
			candidato.setContenttypeFilenameCv(contenttypeFilenameCv);

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

	public void endEventHabilidade1(SlideEndEvent event) {
		candidato.getHabilidades().get(0).setAno((int) event.getValue());
	}

	public void endEventHabilidade2(SlideEndEvent event) {
		candidato.getHabilidades().get(1).setAno((int) event.getValue());
	}

	public void endEventHabilidade3(SlideEndEvent event) {
		candidato.getHabilidades().get(2).setAno((int) event.getValue());
	}

	public void endEventHabilidade4(SlideEndEvent event) {
		candidato.getHabilidades().get(3).setAno((int) event.getValue());
	}

	public void endEventHabilidade5(SlideEndEvent event) {
		candidato.getHabilidades().get(4).setAno((int) event.getValue());
	}

	public void instanciaCandidato() {
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
	}
}