package fr.ajc.ProjetFinal.to;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ajc.ProjetFinal.model.Client;
import fr.ajc.ProjetFinal.model.Commande;
import fr.ajc.ProjetFinal.model.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeTo {

	private Long id;
	private List<ProduitTo> produits;
	private Client client;
	private Double total;

	public Commande toCommande() {
		List<Produit> produits = new ArrayList<>();
		Commande c = new Commande();

		c.setClient(client);
		c.setDateCreation(LocalDate.now());
		c.setTotal(total);

		for (ProduitTo pTo : this.produits) {
			produits.add(pTo.getProduit());
		}

		c.setProduits(produits);

		return c;
	}

}
