package fr.ajc.ProjetFinal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.model.Produit;
import fr.ajc.ProjetFinal.model.Taille;
import fr.ajc.ProjetFinal.model.TailleId;
import fr.ajc.ProjetFinal.repository.TailleRepository;

@Service
public class TailleService {

	@Autowired
	TailleRepository tr;

	public Integer findStockByTaille(String taille, Long id) {
		Integer i = Math.toIntExact(id);
		return tr.findStockByTaille(i, taille);

	}

	public List<Taille> create(Produit p) {
		List<Taille> tailles = new ArrayList<>();

		for (Taille taille : p.getTailles()) {

			// on doit créer une nouvelle taille pour l'inserer en bdd
			Taille t = new Taille();

			// on lui affecte l'id du produit
			t.setProduit(p);
			t.setTaille(taille.getTaille());
			t.setStock(taille.getStock());

			// on créer la clé composite avec l'id de produit et de taille
			TailleId tailleId = new TailleId();
			tailleId.setTaille(taille.getTaille());
			tailleId.setIdProduit(p.getId());

			// on affecte la clé composite à notre objet taille
			t.setTailleId(tailleId);

			// on insere la taille en bdd
			tailles.add(tr.save(t));
		}
		return tailles;
	}

	public void deleteTaille(Produit p, String taille) {
		Taille t = new Taille();

		TailleId tailleId = new TailleId();
		tailleId.setTaille(taille);
		tailleId.setIdProduit(p.getId());

		t.setTailleId(tailleId);

		tr.delete(t);
	}

}
