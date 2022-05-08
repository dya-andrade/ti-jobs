package br.com.tijobs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.repository.VagaRepository;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vagaRepository;
	
	
	public List<Vaga> buscaTodasVagas() {
		return vagaRepository.findAll();
	}
	
	public List<Vaga> buscaVagasPeloIdCandidato(Integer id) {
		return vagaRepository.buscaVagasPorIdCandidato(id);
	}
	
	public List<Vaga> buscaVagasPeloIdEmpresa(Empresa empresa) {
		return vagaRepository.findByEmpresa(empresa);
	}

	public List<Vaga> buscaVagasPeloTipoContrato(String tipoContrato, String habilidade) {
		if (habilidade != null) {
			return vagaRepository.findByTipoContrato(tipoContrato);
		} else {
			return vagaRepository.findByTipoContratoAndPrincipaisTecnologiasLike(tipoContrato, habilidade);
		}
	}

	public List<Vaga> buscaVagasPeloNivelExperiencia(String nivelExperiencia, String habilidade) {
		if (habilidade != null) {
			return vagaRepository.findByNivelExperiencia(nivelExperiencia);
		} else {
			return vagaRepository.findByNivelExperienciaAndPrincipaisTecnologiasLike(nivelExperiencia, habilidade);
		}
	}

	public List<Vaga> buscaVagasPeloTamanhoEmpresa(String tamanho, String habilidade) {
		if (habilidade != null) {
			return vagaRepository.findByEmpresaTamanho(tamanho);
		} else {
			return vagaRepository.findByEmpresaTamanhoAndPrincipaisTecnologiasLike(tamanho, habilidade);
		}
	}

	public List<Vaga> buscaVagasPeloTamanhoEmpresa(String tamanho1, String tamanho2, String habilidade) {
		if (habilidade != null) {
			return vagaRepository.findByEmpresaTamanhoOrEmpresaTamanho(tamanho1, tamanho2);
		} else {
			return vagaRepository.findByEmpresaTamanhoOrEmpresaTamanhoAndPrincipaisTecnologiasLike(tamanho1, tamanho2,
					habilidade);
		}
	}

	public List<Vaga> buscaVagasPelaLocalidade(String distrito, String habilidade) {
		if (habilidade != null) {
			return vagaRepository.findByEmpresaLocalidade(distrito);
		} else {
			return vagaRepository.findByEmpresaLocalidadeAndPrincipaisTecnologiasLike(distrito, habilidade);
		}
	}

	public List<Vaga> buscaVagasPeloTipoTrabalho(String tipoTrabalho, String habilidade) {
		if (habilidade != null) {
			return vagaRepository.findByTipoTrabalho(tipoTrabalho);
		} else {
			return vagaRepository.findByTipoTrabalhoAndPrincipaisTecnologiasLike(tipoTrabalho, habilidade);
		}
	}

	public List<Vaga> buscaVagasPeloAceitaCandidatoFora(String aceitaCandidatoFora, String habilidade) {
		if (habilidade != null) {
			return vagaRepository.findByAceitaCandidatoFora(aceitaCandidatoFora);
		} else {
			return vagaRepository.findByAceitaCandidatoForaAndPrincipaisTecnologiasLike(aceitaCandidatoFora,
					habilidade);
		}
	}

	public List<Vaga> buscaVagasPelaHabilidade(String habilidade) {
		return vagaRepository.findByPrincipaisTecnologiasLike(habilidade);
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
