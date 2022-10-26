package fr.ajc.ProjetFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.exception.EmptyIdException;
import fr.ajc.ProjetFinal.model.Commande;
import fr.ajc.ProjetFinal.model.CommandeProduit;
import fr.ajc.ProjetFinal.model.CommandeProduitId;
import fr.ajc.ProjetFinal.model.Produit;
import fr.ajc.ProjetFinal.repository.CommandeProduitRepository;
import fr.ajc.ProjetFinal.to.CommandeTo;
import fr.ajc.ProjetFinal.to.ProduitTo;

@Service
public class CommandeProduitService {

	@Autowired
	CommandeProduitRepository cpr;

	public void createCommandeProduit(Commande c, CommandeTo cTo) throws EmptyIdException {
		for (ProduitTo produitTo : cTo.getProduits()) {
			checkIfIdIsNull(produitTo.getProduit());

			CommandeProduit cp = new CommandeProduit();
			cp.setCommande(c);
			cp.setProduit(produitTo.getProduit());
			cp.setQuantite(produitTo.getQuantite());
			cp.setTaille(produitTo.getTaille());

			CommandeProduitId cpi = new CommandeProduitId();
			cpi.setIdCommande(c.getId());
			cpi.setIdProduit(produitTo.getProduit().getId());

			cp.setId(cpi);
			cpr.save(cp);
		}
	}

	private void checkIfIdIsNull(Produit produit) throws EmptyIdException {
		if (produit.getId() == null)
			throw new EmptyIdException("Le produit doit avoir un id renseigné");
	}

}
