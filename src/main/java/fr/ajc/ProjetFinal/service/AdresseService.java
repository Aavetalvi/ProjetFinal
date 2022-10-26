package fr.ajc.ProjetFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.model.Adresse;
import fr.ajc.ProjetFinal.repository.AdresseRepository;

@Service
public class AdresseService {

	@Autowired
	AdresseRepository ar;

	public Adresse creerAdresse(Adresse a) {

		return ar.save(a);

	}

}
