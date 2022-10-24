package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.commandeProduit.model.Commande;
import fr.ajc.commandeProduit.model.CommandeProduit;
import fr.ajc.commandeProduit.model.embeddedId.CommandeProduitId;

public interface CommandeProduitRepository extends JpaRepository <CommandeProduit,CommandeProduitId>{

	

}
