package fr.ajc.ProjetFinal.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

	@Column(insertable = false, updatable = false)
	private String taille;
	private Integer quantite;
}
