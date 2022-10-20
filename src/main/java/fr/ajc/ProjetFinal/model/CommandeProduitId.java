package fr.ajc.ProjetFinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommandeProduitId implements Serializable {

	private static final long serialVersionUID = -2123079660570870012L;

	@Column(name = "id_commande")
	private Long idCommande;

	@Column(name = "id_produit")
	private Long idProduit;

}
