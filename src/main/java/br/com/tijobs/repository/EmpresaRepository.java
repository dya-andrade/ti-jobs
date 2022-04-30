package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Usuario;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
	
	public Empresa findByUsuario(Usuario usuario);
}
