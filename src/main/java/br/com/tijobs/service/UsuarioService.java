package br.com.tijobs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tijobs.model.Usuario;
import br.com.tijobs.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscaUsuarioPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
