package br.com.tijobs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Habilidade;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer> {

	@Query("select h from habilidade h where h.candidato = :candidato")
	List<Habilidade> findByCandidato(Candidato candidato);
	
}
