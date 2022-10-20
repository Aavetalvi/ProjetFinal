package fr.ajc.ProjetFinal.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Taille {

	@Id
	private String taille;
	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "id_produit")
	@JsonIgnoreProperties("produit")
	private Produit produit;

}
