package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.ProjetFinal.model.CommandeProduit;
import fr.ajc.ProjetFinal.model.CommandeProduitId;

public interface CommandeProduitRepository extends JpaRepository<CommandeProduit, CommandeProduitId> {

}
