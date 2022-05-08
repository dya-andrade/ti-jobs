package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.ListaHabilidade;

public interface ListaHabilidadeRepository extends JpaRepository<ListaHabilidade, Integer> {
	
}
