package fr.ajc.ProjetFinal.to;

import fr.ajc.ProjetFinal.model.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitTo {
	private Produit produit;
	private Integer quantite;
	private String taille;

}
