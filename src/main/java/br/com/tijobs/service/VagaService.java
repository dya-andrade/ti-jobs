package br.com.tijobs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.VagaRepository;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vagaRepository;
	
	
	public List<Vaga> buscaVagasPeloTipoContrato(String tipoContrato){
		return vagaRepository.findByTipoContrato(tipoContrato);
	}
	
	public List<Vaga> buscaVagasPeloNivelExperiencia(String nivelExperiencia){
		return vagaRepository.findByNivelExperiencia(nivelExperiencia);
	}
	
}
