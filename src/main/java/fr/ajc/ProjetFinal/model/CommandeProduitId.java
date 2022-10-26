package fr.ajc.ProjetFinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CommandeProduitId implements Serializable {

	private static final long serialVersionUID = -2123079660570870012L;

	@Column(name = "id_commande")
	private Long idCommande;

	@Column(name = "id_produit")
	private Long idProduit;

}
