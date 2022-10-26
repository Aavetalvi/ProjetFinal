package fr.ajc.ProjetFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.exception.ClientNotFoundException;
import fr.ajc.ProjetFinal.exception.IdNotFound;
import fr.ajc.ProjetFinal.exception.MauvaisMdpException;
import fr.ajc.ProjetFinal.model.Client;
import fr.ajc.ProjetFinal.model.Utilisateur;
import fr.ajc.ProjetFinal.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	UtilisateurRepository ur;

	@Autowired
	ClientService cs;

	public Utilisateur createUtilisateur(Utilisateur u) {
		return ur.save(u);
	}

	public Client findById(Utilisateur u) throws IdNotFound, MauvaisMdpException, ClientNotFoundException {
		// on cherche si l'email correspond a un utilisateur en bdd
		Utilisateur utilisateur = ur.findById(u.getEmail()).orElseThrow(() -> new IdNotFound("L'email n'existe pas."));

		// on vérifie si les mdp sont identiques
		if (!utilisateur.getMdp().equals(u.getMdp()))
			throw new MauvaisMdpException("Le mot de passe est invalide.");

		return cs.getClientByMail(utilisateur.getEmail()).orElseThrow(() -> new ClientNotFoundException(
				"Il n'y a pas de client enregistré avec l'email [" + utilisateur.getEmail() + "]."));

	}
}
