package br.com.tijobs.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.tijobs.model.PerfilAcesso;
import br.com.tijobs.model.Usuario;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	SecurityService security;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Usuario usuario = security.login(user, password);
		
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		
		if(usuario != null) {
			PerfilAcesso perfilAcesso = usuario.getPerfil();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_".concat(perfilAcesso.getRole())));
			
		}
		
				
		if (grantedAuths.size() > 0) {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
			return auth;
		}
			
		return null;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {

		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

	}
	
}
