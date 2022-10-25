package fr.ajc.ProjetFinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ajc.ProjetFinal.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("SELECT c from Client c WHERE email_utilisateur = :email")
	public Optional<Client> FindByEmail(String email);

}
