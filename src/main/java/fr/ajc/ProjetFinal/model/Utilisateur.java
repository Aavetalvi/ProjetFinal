package fr.ajc.ProjetFinal.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = "utilisateur_gen", sequenceName = "utilisateur_seq", initialValue = 1, allocationSize = 1)
public class Utilisateur {

	@Id
	@GeneratedValue(generator = "utilisateur_gen")
	private Long id;
	private String identifiant;
	private String mdp;

	@OneToOne(mappedBy = "utilisateur")
	@JsonIgnoreProperties("utilisateur")
	private Client client;

	public Utilisateur(String identifiant, String mdp) {
		this.identifiant = identifiant;
		this.mdp = mdp;
	}

	public UserDetails toUserDetails() {
		return new User(identifiant, mdp, new ArrayList<>());
	}

	public Utilisateur() {
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
