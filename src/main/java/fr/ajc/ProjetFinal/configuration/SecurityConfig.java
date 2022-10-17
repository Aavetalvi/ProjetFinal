package fr.ajc.ProjetFinal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.ajc.ProjetFinal.service.AuthService;

@Configuration
public class SecurityConfig {

	@Autowired
	AuthService authService;

	// acces endpoints

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authenticationProvider(authenticationProvider()).authorizeRequests()

				.antMatchers("/register/**").permitAll().anyRequest().authenticated()

				.and().httpBasic();

		return http.build();
	}

	// utilisateur bdd

	@Bean
	public UserDetailsService userDetailsService() {
		return authService;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	// cryptage mdp

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
