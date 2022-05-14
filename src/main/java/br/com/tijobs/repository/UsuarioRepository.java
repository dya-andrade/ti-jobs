package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);
}
