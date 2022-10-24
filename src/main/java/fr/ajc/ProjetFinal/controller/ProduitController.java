package fr.ajc.ProjetFinal.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.ajc.ProjetFinal.repository.ProduitRepository;
import fr.ajc.ProjetFinal.service.ProduitService;
import fr.ajc.ProjetFinal.model.Produit;



@RestController
@RequestMapping("/produits")
public class ProduitController {

	@Autowired
	ProduitRepository pr;

	@Autowired
	ProduitService ps;

	@GetMapping
	public List<Produit> getProduits() {
		return pr.findAll();
	}

	@GetMapping("/{id}")
	public Produit getById(@PathVariable Long id) {
		return pr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produit createProduit(@RequestBody Produit produit) {
		if (Objects.nonNull(produit.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'id est nulle");

		return ps.create(produit);
	}

	@PutMapping
	public Produit updateProduit(@RequestBody Produit p) {
		try {
			return ps.modifyProduit(p);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"le body du produit ne contient pas d'id ou un id qui n'existe pas.");
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteProduit(@PathVariable Long id) {
		ps.deleteProduit(id);
	}



}
