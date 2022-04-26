package br.com.tijobs.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Habilidade;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.HabilidadeRepository;
import br.com.tijobs.repository.VagaRepository;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class VagaController {

	private List<Vaga> vagas;

	private Vaga vagaSelecionada;
	
	private List<String> distritos;
	
	private List<Habilidade> habilidades;

	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private HabilidadeRepository habilidadeRepository;
	
	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {

		vagas = vagaRepository.findAll();
		
		habilidades = habilidadeRepository.buscaTodasHabilidades();
		
		distritos = utilService.buscaDistritosSP();
	}

	public String fotoStr(Empresa empresa) throws IOException {

		if (empresa.getLogotipo() != null) {
			return new String(Base64.getEncoder().encode(empresa.getLogotipo()));
		} else {

			return new String(Base64.getEncoder()
					.encode(IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("profile.jpg"))));
		}
	}
	
	public String fotoEmpresa( ) throws IOException {		
		if(vagaSelecionada != null) {
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
		this.vagaSelecionada = vagaSelecionada;
	}
}
