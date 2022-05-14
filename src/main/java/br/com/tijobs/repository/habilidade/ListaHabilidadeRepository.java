package br.com.tijobs.repository.habilidade;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.habilidade.ListaHabilidade;

public interface ListaHabilidadeRepository extends JpaRepository<ListaHabilidade, Integer> {
	
}
