package fr.ajc.ProjetFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ajc.ProjetFinal.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	@Query(value = "SELECT * FROM commande WHERE id_client = :id", nativeQuery = true)
	List<Commande> FindCommandeByClient(Long id);

}
