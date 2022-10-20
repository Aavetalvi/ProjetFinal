package fr.ajc.ProjetFinal.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Taille {

	@EmbeddedId
	private TailleId tailleId;

	@Column(insertable = false, updatable = false)
	private String taille;
	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "id_produit")
	@MapsId("idProduit")
	@JsonIgnoreProperties("produit")
	private Produit produit;

}
