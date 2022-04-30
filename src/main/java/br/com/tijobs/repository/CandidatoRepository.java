package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Usuario;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
	
	public Candidato findByUsuario(Usuario usuario);

}
