package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.ProjetFinal.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	// @Query("SELECT * FROM commande WHERE id_client = :'id'")
	// List<Commande> FindCommandeByClient(Long id);

}
