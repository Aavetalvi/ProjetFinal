package fr.ajc.ProjetFinal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.model.ImageProduit;
import fr.ajc.ProjetFinal.model.Produit;
import fr.ajc.ProjetFinal.repository.ImageProduitRepository;

@Service
public class ImageProduitService {

	@Autowired
	ImageProduitRepository ipr;

	public List<ImageProduit> create(Produit p) {
		List<ImageProduit> images = new ArrayList<>();

		for (ImageProduit image : p.getImages()) {

			// on doit créer une nouvelle imageproduit pour l'inserer en bdd
			ImageProduit i = new ImageProduit();

			// on lui affecte l'id du produit
			i.setProduit(p);
			i.setNom(image.getNom());

			// on l'insère en bdd
			images.add(ipr.save(i));
		}
		return images;

	}

	public void deleteImage(ImageProduit i) {
		ipr.delete(i);
	}

	public void deleteAllImage(Produit p) {
		for (ImageProduit i : p.getImages()) {
			deleteImage(i);
		}
	}
}
