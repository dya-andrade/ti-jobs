package br.com.tijobs.controller;

import static br.com.tijobs.util.Message.addDetailMessage;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Habilidade;
import br.com.tijobs.model.Usuario;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.HabilidadeRepository;
import br.com.tijobs.repository.VagaRepository;
import br.com.tijobs.util.UtilService;
import br.com.tijobs.service.VagaService;

@Named
@ViewScoped
public class VagaController {

	private List<Vaga> vagas;

	private Vaga vagaSelecionada;

	private List<String> distritos;

	private List<Habilidade> habilidades;

	private Usuario usuarioLogado;

	private Candidato candidatoLogado;

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private HabilidadeRepository habilidadeRepository;

	@Autowired
	private UtilService utilService;
	
	@Autowired
	private VagaService vagaService;

	@PostConstruct
	public void init() {

		if(vagas == null) {
			vagas = vagaRepository.findAll();
		}

		habilidades = habilidadeRepository.buscaTodasHabilidades();

		distritos = utilService.buscaDistritosSP();

		usuarioLogado = utilService.usuarioLogado();

		candidatoLogado = utilService.perfilCandidato();
	}
	
	
	// ------------ FILTROS ------------------
	
	public void filtroTodas() {
		vagas = vagaRepository.findAll();
	}
	
	public void filtroCLT() {
		vagas = vagaService.buscaVagasPeloTipoContrato("CLT");
	}
	
	public void filtroPJ() {
		vagas = vagaService.buscaVagasPeloTipoContrato("PJ");
	}
	
	public void filtroEstagio() {
		vagas = vagaService.buscaVagasPeloTipoContrato("Estágio");
	}
	
	public void filtroJunior() {
		vagas = vagaService.buscaVagasPeloNivelExperiencia("Júnior");
	}
	
	public void filtroPleno() {
		vagas = vagaService.buscaVagasPeloNivelExperiencia("Pleno");
	}

	public void filtroSenior() {
		vagas = vagaService.buscaVagasPeloNivelExperiencia("Sênior");
	}

	public boolean getVerificaCandidatura() {
		if (vagaSelecionada != null) {

			Vaga candidatura = vagaRepository.buscaVagaPorIdECandidato(vagaSelecionada.getId(),
					candidatoLogado.getId());

			if (candidatura != null) {
				return true;
			}
		}

		return false;
	}

	public void candidatar() {

		List<Candidato> candidatos = vagaSelecionada.getCandidados();

		if (!getVerificaCandidatura()) {

			candidatos.add(candidatoLogado);
			vagaSelecionada.setCandidados(candidatos);

			vagaRepository.save(vagaSelecionada);
			
			addDetailMessage("Candidatura realizada.", FacesMessage.SEVERITY_INFO);
		}

	}

	public void cancelarCandidatura() {

		List<Candidato> candidatos = vagaSelecionada.getCandidados();

		List<Candidato> candidadosForeach = candidatos;

		for (Candidato candidato : candidadosForeach) {
			if (candidato.getId() == candidatoLogado.getId()) {
				candidatos.remove(candidato);
				break;
			}
		}

		vagaSelecionada.setCandidados(candidatos);

		vagaRepository.save(vagaSelecionada);

		addDetailMessage("Candidatura cancelada.", FacesMessage.SEVERITY_INFO);
	}

	public void cadastrar() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/cadastre.xhtml");
	}

	public String fotoStr(Empresa empresa) throws IOException {

		if (empresa.getLogotipo() != null) {
			return new String(Base64.getEncoder().encode(empresa.getLogotipo()));
		} else {

			return new String(Base64.getEncoder()
					.encode(IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("profile.jpg"))));
		}
	}

	public String fotoEmpresa() throws IOException {
		if (vagaSelecionada != null) {
			if (vagaSelecionada.getEmpresa().getLogotipo() != null) {
				return new String(Base64.getEncoder().encode(vagaSelecionada.getEmpresa().getLogotipo()));
			} else {

				return new String(Base64.getEncoder()
						.encode(IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("profile.jpg"))));
			}
		}
		return null;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public List<String> getDistritos() {
		return distritos;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public Vaga getVagaSelecionada() {
		return vagaSelecionada;
	}

	public void setVagaSelecionada(Vaga vagaSelecionada) {
		Vaga vaga = vagaRepository.getOne(vagaSelecionada.getId());
		this.vagaSelecionada = vaga;
		if(vaga == null) {
			PrimeFaces current = PrimeFaces.current();
			
			current.executeScript("PF('vagaDialogo').hide();");
			
			addDetailMessage("Vaga desativada.", FacesMessage.SEVERITY_WARN);
			current.ajax().update("growl");
		}
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public Candidato getCandidatoLogado() {
		return candidatoLogado;
	}
}