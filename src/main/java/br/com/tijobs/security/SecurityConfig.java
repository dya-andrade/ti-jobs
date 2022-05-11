package br.com.tijobs.security;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.tijobs.model.PerfilAcesso;
import br.com.tijobs.repository.PerfilAcessoRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Autowired
	private PerfilAcessoRepository perfilAcessoRepository;

	private static PerfilAcessoRepository perfilAcessoRepositoryStc;

	@PostConstruct
	public void init() {
		SecurityConfig.perfilAcessoRepositoryStc = perfilAcessoRepository;
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {

			List<PerfilAcesso> acessos = perfilAcessoRepositoryStc.findAll();

			for (PerfilAcesso acesso : acessos) {
				if (acesso.getPath() != null && !acesso.getPath().equals("")) {
					http.authorizeRequests().antMatchers(acesso.getPath()).hasAnyRole(acesso.getRole());
				}
			}

			http.authorizeRequests()
					.antMatchers("/", "/login.jsf", "/recover.xhtml", "/javax.faces.resource/**", "/resources/**",
							"/index.jsf", "/cadastre.jsf", "/vagas.jsf")
					.permitAll().antMatchers("/cadastro/**", "/perfil/**", "/visualizar/**", "/roadmap/**").fullyAuthenticated().and().formLogin()
					.loginPage("/login.jsf").defaultSuccessUrl("/index.xhtml").failureUrl("/login.jsf?authfailed=true")
					.permitAll().and().logout().logoutSuccessUrl("/login.jsf").logoutUrl("/logout").and().csrf()
					.disable();

		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// form login
		http.authorizeRequests().antMatchers("/", "/login.jsf", "/javax.faces.resource/**").permitAll()
				.antMatchers("/cadastro/**", "/perfil/**", "/visualizar/**", "/roadmap/**").fullyAuthenticated().and().formLogin().loginPage("/login.jsf")
				.defaultSuccessUrl("/index.xhtml").failureUrl("/login.jsf?authfailed=true").permitAll().and().logout()
				.logoutSuccessUrl("/login.jsf").logoutUrl("/j_spring_security_logout").and().csrf().disable();

		http.headers().frameOptions().sameOrigin();
	}

}
