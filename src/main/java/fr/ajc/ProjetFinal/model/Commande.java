package fr.ajc.ProjetFinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = "commande_gen", sequenceName = "commande_seq", initialValue = 1, allocationSize = 1)
public class Commande {

	@Id
	@GeneratedValue(generator = "commande_gen")
	private Long id;
	private boolean confirme;
	@Column(name = "date_creation")
	private LocalDate dateCreation;

	@ManyToOne
	@JoinColumn(name = "id_client")
	@JsonIgnoreProperties("commandes")
	private Client client;

	@ManyToMany
	@JoinTable(name = "commande_produit", joinColumns = @JoinColumn(name = "id_commande"), inverseJoinColumns = @JoinColumn(name = "id_produit"))
	@JsonIgnoreProperties("commandes")
	private List<Produit> produits;
}
