package fr.ajc.ProjetFinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "adresse_gen", sequenceName = "adresse_seq", initialValue = 2, allocationSize = 1)
public class Adresse {

	@Id
	@GeneratedValue(generator = "adresse_gen")
	private Long id;
	private String num_rue;
	private String rue;
	private String cp;
	private String ville;

	@OneToOne(mappedBy = "adresse")
	@JsonIgnoreProperties("adresse")
	private Client client;

}
