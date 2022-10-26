package fr.ajc.ProjetFinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import fr.ajc.ProjetFinal.exception.RelationException;
import fr.ajc.ProjetFinal.model.Client;
import fr.ajc.ProjetFinal.service.ClientService;

@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService cs;

	// POST
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public Client createClient(@RequestBody Client c) {
		return cs.creerClient(c);
	}

	@GetMapping
	public List<Client> findAll() {
		List<Client> listeClient = new ArrayList<>();
		for (Client c : cs.getAllClient()) {
			listeClient.add(c);
		}
		return listeClient;
	}

	@GetMapping("/{id}")
	public Client getById(@PathVariable Long id) {

		return cs.getClient(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client creeClient(@RequestBody Client c) {
		if (Objects.nonNull(c.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id est null");
		}
		return cs.creerClient(c);

	}

	@PutMapping("")
	public Client modifyClient(@RequestBody Client c) throws IllegalArgumentException, EmptyIdException {
		try {
			return cs.updateClient(c);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (EmptyIdException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteClient(@PathVariable Long id) throws RelationException {

		if (!cs.getClient(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'id : " + id + " n'existe pas ");
		}
		try {
			cs.deleteClientById(id);
		} catch (RelationException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

}
