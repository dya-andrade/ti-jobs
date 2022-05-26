package br.com.tijobs.repository.habilidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.habilidade.ListaHabilidade;

public interface ListaHabilidadeRepository extends JpaRepository<ListaHabilidade, Integer> {

	public List<ListaHabilidade> findByNomeIn(List<String> nomes);
	
}
