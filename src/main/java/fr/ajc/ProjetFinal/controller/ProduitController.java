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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.ajc.ProjetFinal.exception.IdNotFound;
import fr.ajc.ProjetFinal.model.Produit;
import fr.ajc.ProjetFinal.repository.ProduitRepository;
import fr.ajc.ProjetFinal.service.ProduitService;

@RestController
@RequestMapping("/produits")
public class ProduitController {

	@Autowired
	ProduitRepository pr;

	@Autowired
	ProduitService ps;

	@GetMapping
	public List<Produit> getProduits() {
		return ps.findAll();
	}

	@GetMapping("/{id}")
	public Produit getById(@PathVariable Long id) {
		return pr.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produit createProduit(@RequestBody Produit produit) {
		if (Objects.nonNull(produit.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'id est non nul");

		return ps.create(produit);
	}

	@PutMapping
	public Produit updateProduit(@RequestBody Produit p) {

		try {
			return ps.modifyProduit(p);
		} catch (IdNotFound e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteProduit(@PathVariable Long id) {
		try {
			ps.deleteProduit(id);
		} catch (IdNotFound e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
