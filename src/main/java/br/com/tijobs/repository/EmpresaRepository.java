package br.com.tijobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tijobs.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
