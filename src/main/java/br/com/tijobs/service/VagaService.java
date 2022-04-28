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

	public List<Vaga> buscaVagasPeloTamanhoEmpresa(String tamanho) {
		return vagaRepository.findByEmpresaTamanho(tamanho);
	}

	public List<Vaga> buscaVagasPeloTamanhoEmpresa(String tamanho1, String tamanho2) {
		return vagaRepository.findByEmpresaTamanhoOrEmpresaTamanho(tamanho1, tamanho2);
	}

	public List<Vaga> buscaVagasPelaLocalidade(String distrito) {
		return vagaRepository.findByEmpresaLocalidade(distrito);
	}

	public List<Vaga> buscaVagasPeloTipoTrabalho(String tipoTrabalho) {
		return vagaRepository.findByTipoTrabalho(tipoTrabalho);
	}

	public List<Vaga> buscaVagasPeloAceitaCandidatoFora(String aceitaCandidatoFora) {
		return vagaRepository.findByAceitaCandidatoFora(aceitaCandidatoFora);
	}
	
}
