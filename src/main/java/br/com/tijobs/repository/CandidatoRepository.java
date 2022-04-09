package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

}
