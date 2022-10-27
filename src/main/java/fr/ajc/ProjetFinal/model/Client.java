package fr.ajc.ProjetFinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "client_gen", sequenceName = "client_seq", initialValue = 2, allocationSize = 1)
public class Client {

	@Id
	@GeneratedValue(generator = "client_gen")
	private Long id;

	private String nom;
	private String prenom;

	@Column(name = "date_creation")
	private LocalDate dateCreation;
	private String telephone;

	@OneToOne
	@JoinColumn(name = "email_utilisateur", referencedColumnName = "email")
	@JsonIgnoreProperties(value = { "client" }, allowSetters = true)
	private Utilisateur utilisateur;

	@OneToOne
	@JoinColumn(name = "id_adresse", referencedColumnName = "id")
	@JsonIgnoreProperties("client")
	private Adresse adresse;

	@OneToMany(mappedBy = "client")
	@JsonIgnoreProperties({ "client", "produits" })
	@JsonIgnore
	private List<Commande> commandes;
}
