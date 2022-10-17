package fr.ajc.ProjetFinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.model.Utilisateur;
import fr.ajc.ProjetFinal.repository.UtilisateurRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	UtilisateurRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Récupérer l'utilisateur de la bdd avec :username
		Optional<Utilisateur> optU = repo.findByIdentifiant(username);

		if (optU.isEmpty())
			throw new UsernameNotFoundException("arg");

		return optU.get().toUserDetails();
	}

}
