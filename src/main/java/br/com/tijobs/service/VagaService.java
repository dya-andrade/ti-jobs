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

	public List<Vaga> buscaVagasPeloTipoContrato(String tipoContrato) {
		return vagaRepository.findByTipoContrato(tipoContrato);
	}

	public List<Vaga> buscaVagasPeloNivelExperiencia(String nivelExperiencia) {
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

	public String iconeDoBenefício(String beneficio) {
		
		String icon = null;

		switch (beneficio) {
		
		case "Vale Transporte":
			
			icon = "fa fa-bus";
			break;
		
		case "Vale Alimentação":
		case "Vale Refeição":
			
			icon = "fa-cutlery";	
			break;

		case "Plano de Saúde":
		case "Auxílio Psicólogo":
		case "Plano Odontológico":
		case "Seguro de Vida":

			icon = "fa fa-ambulance";
			break;

		case "Auxílio Home Office":
			
			icon = "fa fa-laptop";	
			break;
			
		case "Auxílio Creche":
			
			icon = "fa fa-child";
			break;

		case "Academia/Gympass":
			
			icon = "fa fa-bicycle";
			break;
			
		case "Aulas de Inglês":
			
			icon = "fa fa-mortar-board";
			break;
			
		case "Sala de Jogos":
			
			icon = "fa fa-gamepad";
			
		case "Day off no aniversário":
			
			icon = "fa fa-birthday-cake";
			break;
			
		case "No dress code":

			icon = "fa fa-female";
			break;

		case "Participação nos Lucros e Resultados":
		case "Bônus":

			icon = "fa fa-money";		
			break;

		default:
			
			icon = "fa fa-star";
		}
		
		return icon;
	}
}
