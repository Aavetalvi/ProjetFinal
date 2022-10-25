package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.ProjetFinal.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

}
