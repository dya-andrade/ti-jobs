package br.com.tijobs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Habilidade;
import br.com.tijobs.repository.HabilidadeRepository;

@Named
@ViewScoped
public class CadastroCandidatoController {
	
	private List<String> distritos;
	
	private int numero;
	
	private List<Habilidade> habilidades;
	
	@Autowired
	private HabilidadeRepository habilidadeRepository;
	
	private Candidato candidato;

	@PostConstruct
	public void init() {
		buscaDistritosSP();
		
		habilidades = habilidadeRepository.buscaTodasHabilidades();
		
		if(candidato == null) {
			candidato = new Candidato();
		}
		
	}
	
	public void salvar() {
		System.out.println("chamou salvar");
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

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public List<String> getDistritos() {
		return distritos;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
