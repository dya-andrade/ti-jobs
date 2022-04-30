package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tijobs.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM usuario u WHERE u.email = :email")
	public Usuario findByEmail(String email);
}
