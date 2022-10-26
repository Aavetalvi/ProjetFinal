package fr.ajc.ProjetFinal.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.exception.IdNotFound;
import fr.ajc.ProjetFinal.model.Categorie;
import fr.ajc.ProjetFinal.model.ImageProduit;
import fr.ajc.ProjetFinal.model.Produit;
import fr.ajc.ProjetFinal.model.Taille;
import fr.ajc.ProjetFinal.repository.ProduitRepository;

@Service
public class ProduitService {

	@Autowired
	ProduitRepository pr;

	@Autowired
	TailleService ts;

	@Autowired
	ImageProduitService ips;

	public List<Produit> findAll() {
		return pr.findAll();
	}

	public Optional<Produit> findById(Long id) {
		return pr.findById(id);
	}

	public List<Categorie> getCategories() {
		return pr.findCategorie();

	}

	public Produit create(Produit produit) {
		// on créer le produit pour avoir l'ID
		Produit p = pr.save(produit);

		// on créer les tailles et images en BDD avec le nouveau produit
		setTailleEtImage(p);
		return p;
	}

	public Produit modifyProduit(Produit p) throws IdNotFound {
		if (Objects.isNull(p.getId()) || !(this.findById(p.getId()).isPresent())) {
			throw new IdNotFound("le body du produit ne contient pas d'id ou un id qui n'existe pas.");
		} else {

			Produit x = this.findById(p.getId()).get();
			// On supprime les images précédentes
			ips.deleteAllImage(x);

			// on créer les tailles et images en BDD avec le nouveau produit
			setTailleEtImage(p);

			Produit produit = pr.save(p);
		}

		return findById(p.getId()).get();
	}

	public void deleteProduit(Long id) throws IdNotFound {
		Produit p = pr.findById(id).orElseThrow(() -> new IdNotFound("Le produit à l'id [" + id + "] n'existe pas."));
		for (Taille taille : p.getTailles()) {
			ts.deleteTaille(p, taille.getTaille());
		}
		for (ImageProduit i : p.getImages()) {
			ips.deleteImage(i);
		}
		pr.deleteById(id);
	}

	public void setTailleEtImage(Produit p) {
		// on créer les tailles en BDD avec le nouveau produit (pour avoir son id) (si
		// des tailles sont renseignées)
		if (p.getTailles() != null && p.getTailles().size() > 0)
			ts.create(p);

		// on créer les images en BDD avec le nouveau produit (pour avoir son id) (si
		// des images sont renseignées)
		if (p.getImages() != null && p.getImages().size() > 0)
			ips.create(p);
	}

}
