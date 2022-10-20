package fr.ajc.ProjetFinal.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilisateur {

	@Id
	private String email;
	private String mdp;

	@OneToOne(mappedBy = "utilisateur")
	@JsonIgnoreProperties("utilisateur")
	private Client client;

	@CollectionTable(name = "utilisateur_role", joinColumns = @JoinColumn(name = "email_utilisateur", nullable = false))
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private List<Role> roles;

}
