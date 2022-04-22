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
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.VagaRepository;

@Named
@ViewScoped
public class VagaController {

	private List<Vaga> vagas;

	private List<Vaga> vagasSelecionadas;

	@Autowired
	private VagaRepository vagaRepository;

	@PostConstruct
	public void init() {

		vagas = vagaRepository.findAll();
	}

	public String fotoStr(Empresa empresa) throws IOException {

		if (empresa.getLogotipo() != null) {
			return new String(Base64.getEncoder().encode(empresa.getLogotipo()));
		} else {

			return new String(Base64.getEncoder()
					.encode(IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("profile.jpg"))));
		}
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public List<Vaga> getVagasSelecionadas() {
		return vagasSelecionadas;
	}

	public void setVagasSelecionadas(List<Vaga> vagasSelecionadas) {
		this.vagasSelecionadas = vagasSelecionadas;
	}
}
