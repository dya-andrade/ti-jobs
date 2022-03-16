package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {

}
