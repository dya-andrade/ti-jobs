package br.com.tijobs.util;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.tijobs.controller.LoginController;
import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Usuario;
import br.com.tijobs.repository.CandidatoRepository;
import br.com.tijobs.repository.EmpresaRepository;
import br.com.tijobs.security.SecurityService;

@Service
public class UtilService {

	//@Autowired
	//private LoginController loginController;
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	private CandidatoRepository candidatoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	public Usuario usuarioLogado() {
		//return loginController.getUsuario();
		return securityService.getLogado();
	}

	public Empresa perfilEmpresa() {

		Usuario usuario = usuarioLogado();

		if (usuario != null && usuario.getPerfil() != null) {

			if (usuario.getPerfil().getId() == 2) {
				return empresaRepository.findByUsuario(usuario);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Candidato perfilCandidato() {

		Usuario usuario = usuarioLogado();

		if (usuario != null && usuario.getPerfil() != null) {

			if (usuario.getPerfil().getId() == 1) {
				return candidatoRepository.findByUsuario(usuario);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public List<String> buscaDistritosSP() {

		String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/SP/distritos";
		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		JSONArray array = new JSONArray(response.getBody());

		List<String> distritos = new ArrayList<String>();

		distritos.add("São Paulo - Central");

		for (Object objeto : array) {
			JSONObject json = (JSONObject) objeto;
			distritos.add(json.getString("nome"));
		}

		return distritos;
	}
}

