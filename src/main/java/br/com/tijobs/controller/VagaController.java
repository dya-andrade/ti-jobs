package br.com.tijobs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Habilidade;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.HabilidadeRepository;
import br.com.tijobs.repository.VagaRepository;

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

	@PostConstruct
	public void init() {

		vagas = vagaRepository.findAll();
		
		habilidades = habilidadeRepository.buscaTodasHabilidades();
		
		buscaDistritosSP();
	}
	
	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void buscaDistritosSP() {

		String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/SP/distritos";
		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		JSONArray array = new JSONArray(response.getBody());

		distritos = new ArrayList<>();

		distritos.add("São Paulo - Central");

		for (Object objeto : array) {
			JSONObject json = (JSONObject) objeto;
			distritos.add(json.getString("nome"));
		}
	}

	public List<String> getDistritos() {
		return distritos;
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

	public Vaga getVagaSelecionada() {
		return vagaSelecionada;
	}

	public void setVagaSelecionada(Vaga vagaSelecionada) {
		this.vagaSelecionada = vagaSelecionada;
	}
}
