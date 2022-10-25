package fr.ajc.ProjetFinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.ajc.ProjetFinal.exception.EmptyIdException;
import fr.ajc.ProjetFinal.exception.IdNotFound;
import fr.ajc.ProjetFinal.exception.RelationException;
import fr.ajc.ProjetFinal.exception.StockInsuffisantException;
import fr.ajc.ProjetFinal.model.Commande;
import fr.ajc.ProjetFinal.repository.CommandeProduitRepository;
import fr.ajc.ProjetFinal.repository.CommandeRepository;
import fr.ajc.ProjetFinal.repository.ProduitRepository;
import fr.ajc.ProjetFinal.service.CommandeService;
import fr.ajc.ProjetFinal.to.CommandeTo;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

	@Autowired
	CommandeService service;

	@Autowired
	CommandeRepository comRepo;
	@Autowired
	CommandeProduitRepository cpr;
	@Autowired
	ProduitRepository pr;

	@GetMapping
	public List<Commande> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Commande getById(@PathVariable Long id) {
		return service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public void create(@RequestBody CommandeTo commandeTo) {
		if (commandeTo.getId() != null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id doit etre nul");

		try {
			service.create(commandeTo);
		} catch (RelationException | EmptyIdException | StockInsuffisantException | IdNotFound e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@PutMapping
	public Commande update(@RequestBody CommandeTo commandeTo) {
		try {
			return service.modifyCommande(commandeTo);
		} catch (EmptyIdException | RelationException | StockInsuffisantException | IdNotFound e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteCommande(@PathVariable Long id) {
		service.deleteCommande(id);
	}

}
