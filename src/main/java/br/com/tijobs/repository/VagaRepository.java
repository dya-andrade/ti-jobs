package br.com.tijobs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tijobs.model.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {
	
	@Query(value = "select * from vaga v join vaga_candidato vc on v.id = vc.id_vaga where vc.id_candidato = :idCandidato and vc.id_vaga = :idVaga", nativeQuery = true)
	Vaga buscaVagaPorIdECandidato(int idVaga, int idCandidato);

	List<Vaga> findByNivelExperiencia(String nivelExperiencia);

	List<Vaga> findByTipoContrato(String tipoContrato);

	List<Vaga> findByEmpresaTamanho(String tamanho);

	List<Vaga> findByEmpresaTamanhoOrEmpresaTamanho(String tamanho1, String tamanho2);

	List<Vaga> findByEmpresaLocalidade(String localidade);

	List<Vaga> findByTipoTrabalho(String tipoTrabalho);

	List<Vaga> findByAceitaCandidatoFora(String aceitaCandidatoFora);
	
	//---------- ------ -----------------------------------------

	List<Vaga> findByPrincipaisTecnologiasLike(String habilidade);

	List<Vaga> findByAceitaCandidatoForaAndPrincipaisTecnologiasLike(String aceitaCandidatoFora, String habilidade);

	List<Vaga> findByTipoTrabalhoAndPrincipaisTecnologiasLike(String tipoTrabalho, String habilidade);

	List<Vaga> findByEmpresaLocalidadeAndPrincipaisTecnologiasLike(String distrito, String habilidade);

	List<Vaga> findByEmpresaTamanhoOrEmpresaTamanhoAndPrincipaisTecnologiasLike(String tamanho1, String tamanho2,
			String habilidade);

	List<Vaga> findByEmpresaTamanhoAndPrincipaisTecnologiasLike(String tamanho, String habilidade);

	List<Vaga> findByNivelExperienciaAndPrincipaisTecnologiasLike(String nivelExperiencia, String habilidade);

	List<Vaga> findByTipoContratoAndPrincipaisTecnologiasLike(String tipoContrato, String habilidade);
}