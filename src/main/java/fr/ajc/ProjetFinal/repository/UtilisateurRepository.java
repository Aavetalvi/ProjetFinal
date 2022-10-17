package fr.ajc.ProjetFinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.spring.web.projetjpaspringmvc.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Optional<Utilisateur> findByIdentifiant(String identifiant);

}
