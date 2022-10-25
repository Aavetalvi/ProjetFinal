package fr.ajc.ProjetFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.ajc.ProjetFinal.exception.ClientNotFoundException;
import fr.ajc.ProjetFinal.exception.IdNotFound;
import fr.ajc.ProjetFinal.exception.MauvaisMdpException;
import fr.ajc.ProjetFinal.model.Client;
import fr.ajc.ProjetFinal.model.Utilisateur;
import fr.ajc.ProjetFinal.service.UtilisateurService;

@RestController
@CrossOrigin
@RequestMapping("/utilisateur")
public class UtilisateurController {

	@Autowired
	UtilisateurService us;

	// POST
	@PostMapping("/connexion")
	public Client connexion(@RequestBody Utilisateur u) {
		try {
			return us.findById(u);
		} catch (IdNotFound | MauvaisMdpException | ClientNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
