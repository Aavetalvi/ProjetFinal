package fr.ajc.ProjetFinal.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "commande_produit")
public class CommandeProduit {

	@EmbeddedId
	CommandeProduitId id;

	@ManyToOne
	@MapsId("idCommande")
	@JoinColumn(name = "id_commande")
	private Commande commande;

	@ManyToOne
	@MapsId("idProduit")
	@JoinColumn(name = "id_produit")
	private Produit produit;
	private String taille;
	private Integer quantite;
}
