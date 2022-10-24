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
public class TailleId implements Serializable {

	private static final long serialVersionUID = 3294331460264403185L;

	@Column(name = "taille")
	private String taille;

	@Column(name = "id_produit")
	private Long idProduit;

}
