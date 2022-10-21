package fr.ajc.ProjetFinal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "produit_gen", sequenceName = "produit_seq", initialValue = 1, allocationSize = 1)
public class Produit {

	@Id
	@GeneratedValue(generator = "produit_gen")
	private Long id;

	private String reference;
	private Double prix;
	@Column(name = "description_courte")
	private String descriptionCourte;
	@Column(name = "description_longue")
	private String descriptionLongue;
	private String marque;
	private Categorie categorie;

	@ManyToMany(mappedBy = "produits")
	@JsonIgnoreProperties("produits")
	private List<Commande> commandes;

	@OneToMany(mappedBy = "produit")
	@JsonIgnoreProperties("produit")
	private List<Taille> tailles;

	@OneToMany(mappedBy = "produit")
	@JsonIgnoreProperties("produit")
	private List<ImageProduit> images;

}
