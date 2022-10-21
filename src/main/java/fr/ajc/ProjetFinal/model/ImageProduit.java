package fr.ajc.ProjetFinal.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image_produit")
@Entity
public class ImageProduit {

	@Id
	private String nom;

	@ManyToOne
	@JoinColumn(name = "id_produit")
	@JsonIgnoreProperties("images")
	private Produit produit;

}
